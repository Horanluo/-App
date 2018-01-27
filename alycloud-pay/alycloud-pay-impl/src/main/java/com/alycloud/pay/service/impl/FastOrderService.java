package com.alycloud.pay.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alycloud.channel.support.config.Config;
import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.BranchInfo;
import com.alycloud.modules.entity.Channel;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.enums.FastOrderStatus;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.enums.SysT0RateType;
import com.alycloud.modules.enums.SysTransType;
import com.alycloud.modules.search.AgentTrans4Search;
import com.alycloud.modules.search.Channel4Search;
import com.alycloud.modules.search.ChannelSubmerchInfo4Search;
import com.alycloud.modules.search.FastChannelInfo;
import com.alycloud.modules.search.FastOrder4Search;
import com.alycloud.modules.vo.FastPayDataVO;
import com.alycloud.pay.api.IFastOrderService;
import com.alycloud.pay.feign.AgentInfoFeign;
import com.alycloud.pay.feign.AgentTransFeign;
import com.alycloud.pay.feign.AgentVirtualCardFeign;
import com.alycloud.pay.feign.BranchInfoFeign;
import com.alycloud.pay.feign.ChannelSubmerchInfoFeign;
import com.alycloud.pay.feign.FastTransFeign;
import com.alycloud.pay.feign.MerchInfoFeign;
import com.alycloud.pay.mapper.ChannelMapper;
import com.alycloud.pay.mapper.FastOrderMapper;
import com.alycloud.pay.support.channel.api.ChannelApi;
import com.alycloud.pay.support.channel.api.ChannelApiFactory;
import com.alycloud.pay.support.channel.api.ChannelApiNotFoundException;
import com.alycloud.pay.support.channel.api.ChannelOrderStatus;
import com.alycloud.pay.support.channel.api.ChannelOrderStatusResult;
import com.alycloud.pay.support.channel.info.FastChannelInfoFactory;

import lombok.extern.slf4j.Slf4j;


/**
 * 快捷支付订单
 * @author Moyq5
 * @date 2017年10月19日
 */
@Service
@Slf4j
public class FastOrderService implements IFastOrderService {

	@Autowired
	private FastOrderMapper fastOrderMapper;
	@Autowired
	private ChannelMapper channelMapper;
	@Autowired
	private BranchInfoFeign branchInfoFeign;
	@Autowired
	private AgentInfoFeign agentInfoFeign;
	@Autowired
	private MerchInfoFeign merchInfoFeign;
	@Autowired
	private ChannelSubmerchInfoFeign channelSubmerchInfoFeign;
	@Autowired
	private FastTransFeign fastTransFeign;
	@Autowired
	private AgentTransFeign agentTransFeign;
	@Autowired
	private AgentVirtualCardFeign agentVirtualCardFeign;
	
	@Value("${order.check.in.minutes}")
	private Integer orderCheckInMinutes;
	@Value("${hxtc.backNotifyUrl}")
    private String backNotifyUrl;
	@Value("${hxtc.frontNotifyUrl}")
    private String frontNotifyUrl;
	
	@Override
	public FastOrder buildOrder(String merchno, FastPayDataVO data) throws Exception {
		RequestBean<String> reqData = new RequestBean<String>();
		reqData.setData(merchno);
		MerchInfo merch = merchInfoFeign.getByMerchno(reqData).getData();
		reqData.setData(merch.getSuperAgent());
		AgentInfo agent = agentInfoFeign.getByAgentno(reqData).getData();
		reqData.setData(merch.getBranchno());
		BranchInfo branch = branchInfoFeign.getByBranchno(reqData).getData();
		
		final SysChannelType channelType = SysChannelType.values()[data.getChannelType()];
		final BigDecimal amount = new BigDecimal(data.getAmount());
		
		Channel4Search channel4s = new Channel4Search();
		channel4s.setChannelCode(channelType.getCode());
		List<Channel> channelList = channelMapper.listByPage(channel4s);
		if (channelList.size() == 0) {
			log.error("找不到渠道信息:{}", channelType);
			throw new ApiException(ApiCode.C9999);
		}
		Channel channel = channelList.get(0);
		
		SysSettleType settleType = SysSettleType.T0;
		long curTime = new Date().getTime();
		long startTime = DateFormat.DATE_TIME.parse(DateFormat.DATE.format() + " " + channel.getT0StartTime()).getTime();
		long endTime = DateFormat.DATE_TIME.parse(DateFormat.DATE.format() + " " + channel.getT0EndTime()).getTime();
		if (amount.compareTo(new BigDecimal("100")) == -1 || curTime < startTime || curTime > endTime) {// 转T1
			settleType = SysSettleType.T1;
		}
		
		FastChannelInfo channelInfo = FastChannelInfoFactory.getChannel(channelType);
		
		ChannelSubmerchInfo submerch = getSubmerchInfo(merch.getMerchno(), channelType);
		
		BigDecimal channelFee;
		BigDecimal branchFee;
		BigDecimal merchFee;
		if (settleType == SysSettleType.T0) {
			if (channelInfo.getT0RateType() == SysT0RateType.INCREMENT) {// 增量方式计算
			    
				channelFee = statFeeOnT0AddRate(amount, channelInfo.getT0Rate(), channelInfo.getT1Rate()).setScale(2, BigDecimal.ROUND_HALF_UP);
				branchFee = statFeeOnT0AddRate(amount, branch.getFastPayRateT0(), branch.getFastPayRateT1()).setScale(2, BigDecimal.ROUND_HALF_UP);
				merchFee = statFeeOnT0AddRate(amount, submerch.getD0payRate(), submerch.getT1payRate()).setScale(2, BigDecimal.ROUND_HALF_UP);
				
			} else {// 全量方式计算
				channelFee = amount.multiply(channelInfo.getT0Rate()).setScale(2, BigDecimal.ROUND_HALF_UP);
				branchFee = amount.multiply(branch.getFastPayRateT0()).setScale(2, BigDecimal.ROUND_HALF_UP);
				merchFee = amount.multiply(submerch.getD0payRate()).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			
		} else {
			channelFee = amount.multiply(channelInfo.getT1Rate()).setScale(2, BigDecimal.ROUND_HALF_UP);
			branchFee = amount.multiply(branch.getFastPayRateT1()).setScale(2, BigDecimal.ROUND_HALF_UP);
			merchFee = amount.multiply(submerch.getT1payRate()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		// 最低手续费
		final BigDecimal minFee = channelInfo.getMinFee();
		if (channelFee.compareTo(minFee) == -1) {
			channelFee = minFee;
		}
		if (branchFee.compareTo(minFee) == -1) {
			branchFee = minFee;
		}
		if (merchFee.compareTo(minFee) == -1) {
			merchFee = minFee;
		}
		
		// 代付费
		if (settleType == SysSettleType.T0) {
			channelFee = channelFee.add(submerch.getPayFeeD0());
			branchFee = branchFee.add(submerch.getPayFeeD0());
			merchFee = merchFee.add(submerch.getPayFeeD0());
		} else if (settleType == SysSettleType.T1) {
			channelFee = channelFee.add(submerch.getPayFeeT1());
			branchFee = branchFee.add(submerch.getPayFeeT1());
			merchFee = merchFee.add(submerch.getPayFeeT1());
		}
		
		Date date = new Date();
		FastOrder order = new FastOrder();
		order.setAddTime(DateFormat.DATE_TIME.format(date));
		order.setAgentName(null == agent ? branch.getBranchName() : agent.getAgentName());
		order.setAgentno(null == agent ? branch.getBranchno() : agent.getAgentno());
		order.setAmount(amount);
		order.setPayerRemark(data.getPayerRemark());
		order.setBankCard(data.getAccountNo());
		order.setBankName(data.getBankName());
		order.setBranchFee(branchFee);
		order.setBranchName(branch.getBranchName());
		order.setBranchno(branch.getBranchno());
		order.setChannelFee(channelFee);
		//order.setChannelOrderno(channelOrderno);
		order.setChannelType(channelInfo.getType().ordinal());
		//order.setDescr(descr);
		order.setIdCard(merch.getIdentityCard());
		order.setLiquidateType(channelInfo.getLiquidateType().ordinal());
		order.setNotifyUrl(frontNotifyUrl);
		order.setCallbackUrl(backNotifyUrl);
		order.setMerchFee(merchFee);
		order.setMerchName(merch.getMerchName());
		order.setMerchno(merch.getMerchno());
		order.setMobile(merch.getMobile());
		order.setOrderno(genOrderno(merch.getMerchno()));
		order.setSettleType(settleType.ordinal());
		order.setStatus(FastOrderStatus.NEW.ordinal());
		order.setTrueName(merch.getAccountName());
		order.setFee(0==settleType.ordinal()?submerch.getPayFeeD0():submerch.getPayFeeT1());
		order.setReceiveFee((0==settleType.ordinal())?merchFee.subtract(submerch.getPayFeeD0()):merchFee.subtract(submerch.getPayFeeT1()));
		order.setRate((0==settleType.ordinal())?submerch.getD0payRate():submerch.getT1payRate());
		
		log.info("支付订单信息:"+order);
		fastOrderMapper.add(order);
		
		return order;
	}

	private ChannelSubmerchInfo getSubmerchInfo(String merchno, SysChannelType channelType) throws ApiException {
		ChannelSubmerchInfo4Search merch4s = new ChannelSubmerchInfo4Search();
		merch4s.setMerchno(merchno);
		merch4s.setAduitStatus("1");
		merch4s.setPayType("QUICKPAY");
		merch4s.setPayMethod("QUICKPAY");
		merch4s.setChannelCode(channelType.getCode());
		
		RequestBean<ChannelSubmerchInfo4Search> feignData = new RequestBean<ChannelSubmerchInfo4Search>();
		feignData.setData(merch4s);
		List<ChannelSubmerchInfo> submerchList = channelSubmerchInfoFeign.listByPage(feignData).getData();
		if (submerchList.size() == 0) {
			throw new ApiException(ApiCode.C0009);
		}
		return submerchList.get(0);
	}

	private static BigDecimal statFeeOnT0AddRate(final BigDecimal amount, BigDecimal t0Rate, BigDecimal t1Rate) {
		BigDecimal t0AddRate = t0Rate.subtract(t1Rate);
		BigDecimal t1Fee = amount.multiply(t1Rate);
		BigDecimal t0AddFee = amount.subtract(t1Fee).multiply(t0AddRate);
		return t1Fee.add(t0AddFee);
	}

	private static String genOrderno(String merchno) {
		String system = Config.getString("syetem-remark");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String date = sdf.format(new Date());
		String refno = merchno;
		if (refno.length() > 9) {
			refno = refno.substring(refno.length() - 9);
		}
		String rand0 = Integer.toString((int)(9 * Math.random()));
		String rand1 = Integer.toString((int)(1000 + 8999 * Math.random()));
		return system + rand0 + date + refno + rand1;
	}

	@Override
	public List<FastOrder> listByPage(FastOrder4Search order4s) {
		return fastOrderMapper.listByPage(order4s);
	}

	@Override
	public void add(FastOrder order) {
		fastOrderMapper.add(order);
	}

	@Override
	public synchronized void paySuccess(String orderno) {
		FastOrder4Search order4s = new FastOrder4Search();
		order4s.setOrderno(orderno);
		List<FastOrder> list = fastOrderMapper.listByPage(order4s);
		if (list.size() != 1) {
			log.warn("快捷支付订单[{}]不存在", orderno);
			return;
		}
		FastOrder order = list.get(0);
		if (order.getStatus() == FastOrderStatus.SUCCESS.ordinal()) {
			log.warn("快捷支付订单[{}]已经是成功状态，不能重复执行“支付成功”逻辑", order.getOrderno());
			return;
		}
		
		log.debug("更新快捷支付订单[{}]支付状态为：成功", order.getOrderno());
		FastOrder order4Mod = new FastOrder();
		order4Mod.setId(order.getId());
		order4Mod.setStatus(FastOrderStatus.SUCCESS.ordinal());
		order4Mod.setDescr("支付成功");
		fastOrderMapper.mod(order4Mod);
		
		RequestBean<String> transData = new RequestBean<String>();
		transData.setData(order.getOrderno());
		fastTransFeign.addByOrderno(transData);
		
		RequestBean<String> orderData = new RequestBean<String>();
		orderData.setData(orderno);
		agentTransFeign.addByFastOrderno(orderData);
		
		AgentTrans4Search trans4s = new AgentTrans4Search();
		trans4s.setRefno(orderno);
		trans4s.setTransType(SysTransType.FAST.ordinal());
		RequestBean<AgentTrans4Search> transData4s = new RequestBean<AgentTrans4Search>();
		transData4s.setData(trans4s);
		agentVirtualCardFeign.rechargeByAgentTrans(transData4s);
		
		/*
		// 清算
		liquidate(trans);
		
		// 异步通知接口
		PayNotifyFactory.getPayNotify(order).execute();
				
		// 公众号通知
		OfficalNoticeFactory.notice(trans);
		*/
		
	}

	@Override
	public void mod(FastOrder order) {
		fastOrderMapper.mod(order);
	}
	
	@Override
	public void checkPayStatus(String orderno) throws Exception {
		
		FastOrder4Search order4s = new FastOrder4Search();
		if (!StringUtils.isEmpty(orderno)) {
			log.info("查询快捷订单支付状态：{}", orderno);
			order4s.setOrderno(orderno);
		} else {
			log.info("查询最近{}分钟内快捷下单成功,但是尚未支付，尚未查询的订单信息", orderCheckInMinutes);
			Date date = new Date();
			date.setTime(date.getTime() - orderCheckInMinutes * 60000);
			order4s.setStatus(FastOrderStatus.NEW.ordinal());
			order4s.setBeginAddTime(DateFormat.DATE_TIME.format(date));
		}
		
		List<FastOrder> list = listByPage(order4s);
		int count = list.size();
		if (count == 0) {
			log.info("找不快捷支付订单");
			return;
		}
		log.info("查找到[{}]笔快捷支付订单", count);
		for(int i = 0; i < count; i++){
			FastOrder order = list.get(i);
			log.info("[" + (i + 1) + "/" + count + "]开始处理快捷订单信息:\r\n" + order);
			
			ChannelApi channelApi = null;
			try {
				channelApi = ChannelApiFactory.getChannelApi(order);
			} catch (ChannelApiNotFoundException e) {
				log.warn("{}：快捷订单号->{}" , e.getMessage(), order.getOrderno());
				continue;
			}
			
			ChannelOrderStatusResult statusResult = channelApi.query();
			if (statusResult.getStatus() == ChannelOrderStatus.SUCCESS) {
				paySuccess(order.getOrderno());
			}
		}

	}
	
}
