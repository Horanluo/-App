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
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.BranchInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.QrcodeMerch;
import com.alycloud.modules.entity.QrcodeMerchFee;
import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.enums.QrcodeOrderStatus;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.QrcodeScanType;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysLiquidateType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.enums.SysT0RateType;
import com.alycloud.modules.enums.SysTransType;
import com.alycloud.modules.enums.SystemParamCode;
import com.alycloud.modules.search.AgentTrans4Search;
import com.alycloud.modules.search.QrcodeMerch4Search4Pay;
import com.alycloud.modules.search.QrcodeMerchFee4Search;
import com.alycloud.modules.search.QrcodeOrder4Search;
import com.alycloud.modules.vo.QrcodePayDataVO;
import com.alycloud.pay.api.IGradeOrderService;
import com.alycloud.pay.api.IQrcodeOrderService;
import com.alycloud.pay.api.ISystemParamService;
import com.alycloud.pay.feign.AgentInfoFeign;
import com.alycloud.pay.feign.AgentTransFeign;
import com.alycloud.pay.feign.AgentVirtualCardFeign;
import com.alycloud.pay.feign.BranchInfoFeign;
import com.alycloud.pay.feign.MerchInfoFeign;
import com.alycloud.pay.feign.QrcodeTransFeign;
import com.alycloud.pay.mapper.QrcodeMerchFeeMapper;
import com.alycloud.pay.mapper.QrcodeMerchMapper;
import com.alycloud.pay.mapper.QrcodeOrderMapper;
import com.alycloud.pay.support.channel.api.ChannelApi;
import com.alycloud.pay.support.channel.api.ChannelOrderStatus;
import com.alycloud.pay.support.channel.api.ChannelOrderStatusResult;
import com.alycloud.pay.support.channel.api.impl.PinganApi;
import com.alycloud.pay.support.channel.api.impl.SaoBeiQrcodeApi;
import com.alycloud.pay.support.channel.api.impl.YufuQrcodeApi;

import lombok.extern.slf4j.Slf4j;

/**
 * 二维码交易订单
 * @author Moyq5
 * @date 2017年10月17日
 */
@Slf4j
@Service
public class QrcodeOrderService implements IQrcodeOrderService {

	@Autowired
	public QrcodeOrderMapper qrcodeOrderMapper;
	@Autowired
	private QrcodeMerchMapper qrcodeMerchMapper;
	@Autowired
	private ISystemParamService systemParamService;
	@Autowired
	public MerchInfoFeign merchInfoFeign;
	@Autowired
	public AgentInfoFeign agentInfoFeign;
	@Autowired
	public BranchInfoFeign branchInfoFeign;
	@Autowired
	private QrcodeFeeService feeService;
	@Autowired
	private QrcodeTransFeign qrcodeTransFeign;
	@Autowired
	private AgentTransFeign agentTransFeign;
	@Autowired
	private AgentVirtualCardFeign agentVirtualCardFeign;
	@Autowired
	private IGradeOrderService gradeOrderService;

	@Value("${order.check.in.minutes}")
	private Integer orderCheckInMinutes;
	@Autowired
	private QrcodeMerchFeeMapper qrcodeMerchFeeMapper;
	@Autowired
	private YufuQrcodeApi yufuQucodeApi;
	@Autowired
	private PinganApi pinganApi;
	@Autowired
	private SaoBeiQrcodeApi saoBeiQrcodeApi;
	
	@Override
	public QrcodeOrder buildOrder(String merchno, QrcodePayDataVO data) throws Exception {
		
		// 生成订单号
		String orderno = genRefno();
		
		RequestBean<String> reqData = new RequestBean<String>();
		reqData.setData(merchno);
		MerchInfo merch = merchInfoFeign.getByMerchno(reqData).getData();
		reqData.setData(merch.getSuperAgent());
		AgentInfo agent = agentInfoFeign.getByAgentno(reqData).getData();
		reqData.setData(merch.getBranchno());
		BranchInfo branch = branchInfoFeign.getByBranchno(reqData).getData();
		
		QrcodeOrder order = new QrcodeOrder();
		order.setAgentName(null != agent ? agent.getAgentName():branch.getBranchName());
		order.setAgentno(null != agent ? agent.getAgentno():branch.getBranchno());
		order.setAmount(new BigDecimal(data.getAmount()));
		order.setBranchName(branch.getBranchName());
		order.setBranchno(branch.getBranchno());
		if (null != data.getChannelType()) {
			order.setChannelCode(SysChannelType.values()[data.getChannelType()].getCode());
		}
		order.setMerchName(merch.getMerchName());
		order.setMerchno(merchno);
		order.setCertno(merch.getIdentityCard());
		order.setAccountno(merch.getAccountno());
		order.setAccountName(merch.getAccountName());
		order.setBankno(merch.getBankno());
		order.setBankName(merch.getBankName());
		order.setMobile(merch.getMobile());
		order.setNotifyUrl(data.getNotifyUrl());
		order.setOrderno(orderno);
		order.setTraceno(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		order.setPaymentFee(new BigDecimal("0"));
		order.setPayType(data.getPayType());
		order.setQueryCount(0);
		order.setPayerBiz(StringUtils.isEmpty(data.getMerchno())?1:0);
		order.setScanType(data.getIsOffical() ? QrcodeScanType.OFFICAL.ordinal():QrcodeScanType.PASSIVE.ordinal());
		order.setStatus(QrcodeOrderStatus.NEW.ordinal());
		order.setStatusDesc("待支付");
		order.setTransDate(DateFormat.DATE.format());
		order.setTransTime(DateFormat.TIME.format());
		
		qrcodeOrderMapper.add(order);
		
		calChannelAndFee(order);
		
		return order;
	}

	public String genRefno() throws Exception {
		String system = Config.getString("syetem-remark");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String date = sdf.format(new Date());
		String refno = qrcodeOrderMapper.genRefno();
		refno = refno.substring(refno.length() - 9);
		String rand = Integer.toString((int)(100 + 900 * Math.random()));
		return system + "2" + date + refno + rand;
	}
	
	/**
	 * 计算交易渠道和手续费
	 * @author Moyq5
	 * @date 2017年6月21日
	 */
	public void calChannelAndFee(QrcodeOrder order) throws Exception {
		
		QrcodeMerch qrcodeMerch = initChannelInfo(order);;
		
		final String channelCode = qrcodeMerch.getChannelCode();
		final String channelMerchCode = qrcodeMerch.getPartnerId();
		final SysT0RateType t0RateType = SysT0RateType.values()[qrcodeMerch.getT0RateType()];
		final BigDecimal t0MinFee = qrcodeMerch.getT0MinFee();
		final BigDecimal dfFee = qrcodeMerch.getDfFee();
		final SysLiquidateType liquidateType = SysLiquidateType.values()[qrcodeMerch.getLiquidateType()];
		
		
		log.info("订单信息[{}]将使用渠道[{}]商户[{}]进行交易", 
				new Object[]{order.getOrderno(), channelCode, channelMerchCode});
		
		final String merchno = order.getMerchno();
		final BigDecimal amount = order.getAmount();
		final QrcodePayType payType = QrcodePayType.values()[order.getPayType()];
		final QrcodeScanType scanType = QrcodeScanType.values()[order.getScanType()];
		final SysSettleType settleType = SysSettleType.values()[order.getSettleType()];
		
		// 渠道信息
		order.setChannelCode(channelCode);
		order.setChannelMerchno(channelMerchCode);
		order.setChannelUrl(null);
		order.setLiquidateType(liquidateType.ordinal());
		order.setPaymentFee(new BigDecimal("0.00"));
		if (settleType == SysSettleType.T0) {
			order.setPaymentFee(dfFee);
		}
		
		// 商户手
		BigDecimal merchFee = feeService.calMerchFee(amount, payType, scanType, settleType, dfFee, t0RateType, t0MinFee, merchno);
        order.setMerchFee(merchFee);
        
        // 机构手续费
        BigDecimal branchFee = feeService.calBranchFee(amount, payType, scanType, settleType, dfFee, t0RateType, t0MinFee, order.getBranchno());
        order.setBranchFee(branchFee);
        
        // 渠道手续费
        BigDecimal channelFee = feeService.calChannelFee(amount, payType, scanType, settleType, dfFee, t0RateType, t0MinFee, channelCode);
        order.setChannelFee(channelFee);
		
        QrcodeMerchFee4Search fee4s = new QrcodeMerchFee4Search();
		fee4s.setMerchno(merchno);
		fee4s.setPayType(payType.ordinal());
		fee4s.setScanType(1 << scanType.ordinal());
		fee4s.setSettleType(1 << settleType.ordinal());
		
		QrcodeMerchFee qrcodeMerchFee = qrcodeMerchFeeMapper.list(fee4s).get(0);
		order.setFee(qrcodeMerchFee.getFee());
		order.setReceiveFee(merchFee.subtract(qrcodeMerchFee.getFee()));
		order.setRate(qrcodeMerchFee.getRate());
		
        qrcodeOrderMapper.mod(order);
	}

	/**
	 * 获取可交易渠道商户
	 * @author Moyq5
	 * @date 2017年10月13日
	 */
	private QrcodeMerch initChannelInfo(QrcodeOrder order) throws Exception {
		QrcodeMerch4Search4Pay qm4s = new QrcodeMerch4Search4Pay();
		if (null != order.getChannelCode()) {
			qm4s.setChannelCode(order.getChannelCode());
		}
		qm4s.setAgentno(order.getAgentno());
		qm4s.setAmount(order.getAmount());
		qm4s.setBranchno(order.getBranchno());
		qm4s.setMerchno(order.getMerchno());
		qm4s.setPayType(1<<order.getPayType());
		qm4s.setScanType(1<<order.getScanType());
		if (null != order.getSettleType()) {
			qm4s.setSettleType(1<<order.getSettleType());
		}
		qm4s.setTime(DateFormat.TIME.format());
		
		List<QrcodeMerch> qrcodeMerchList = qrcodeMerchMapper.list4Pay(qm4s);
		if (qrcodeMerchList.size() == 0) {
			log.info("找不到订单[{}]可交易的渠道商户：payType=[{}],scanType=[{}],settleType=[{}],", 
					new Object[]{order.getOrderno(), order.getPayType(), order.getScanType(), order.getSettleType()});
			throw new ApiException(ApiCode.C0004);
		}
		
		// 目标渠道商户
		QrcodeMerch qrcodeMerch = null;
		// 目标结算方式
		SysSettleType settleType = SysSettleType.T0;
		if (null != order.getSettleType()) {
			settleType = SysSettleType.values()[order.getSettleType()];
		}
		for (QrcodeMerch qrcodeMerchItem: qrcodeMerchList) {
			if ((qrcodeMerchItem.getSettleType()&(1<<settleType.ordinal()))==(1<<settleType.ordinal())) {// 是否支持目标结算方式
				long curTime = new Date().getTime();
				long startTime = DateFormat.DATE_TIME.parse(DateFormat.DATE.format() + " " + qrcodeMerchItem.getT0StartTime()).getTime();
				long endTime = DateFormat.DATE_TIME.parse(DateFormat.DATE.format() + " " + qrcodeMerchItem.getT0EndTime()).getTime();
				if (settleType == SysSettleType.T0 && (curTime < startTime || curTime > endTime)) {// T0，但不在T0时间，转T1
					settleType = SysSettleType.T1;
					if ((qrcodeMerchItem.getSettleType()&(1<<settleType.ordinal()))==(1<<settleType.ordinal())) {// 转T1后也支持
						qrcodeMerch = qrcodeMerchItem;
						break;
					}
				} else {// 目标T0且在T0时间，或者目标直接是T1时
					qrcodeMerch = qrcodeMerchItem;
					break;
				}
			}
		}
		if (null == qrcodeMerch) {
			log.info("找不到订单[{}]可交易的渠道商户：payType=[{}],scanType=[{}],settleType=[{}],", 
					new Object[]{order.getOrderno(), order.getPayType(), order.getScanType(), settleType.ordinal()});
			if (null == order.getSettleType()) {
				order.setSettleType((settleType == SysSettleType.T0 ? SysSettleType.T1: SysSettleType.T0).ordinal());
				return initChannelInfo(order);
			} else {
				throw new ApiException(ApiCode.C0004);
			}
			
		}
		order.setChannelCode(qrcodeMerch.getChannelCode());
		order.setChannelMerchno(qrcodeMerch.getPartnerId());
		order.setSettleType(settleType.ordinal());
		return qrcodeMerch;
	}

	@Override
	public void add(QrcodeOrder order) {
		qrcodeOrderMapper.add(order);
	}

	@Override
	public void mod(QrcodeOrder order) {
		qrcodeOrderMapper.mod(order);
	}

	@Override
	public List<QrcodeOrder> listByPage(QrcodeOrder4Search order4s) {
		return qrcodeOrderMapper.listByPage(order4s);
	}

	@Override
	public synchronized void paySuccess(String orderno) {
		QrcodeOrder4Search order4s = new QrcodeOrder4Search();
		order4s.setOrderno(orderno);
		List<QrcodeOrder> list = qrcodeOrderMapper.listByPage(order4s);
		if (list.size() != 1) {
			log.warn("二维码支付订单[{}]不存在", orderno);
			return;
		}
		QrcodeOrder order = list.get(0);
		if (order.getStatus() == QrcodeOrderStatus.SUCCESS.ordinal()) {
			log.warn("二维码支付订单[{}]已经是成功状态，不能重复执行“支付成功”逻辑", order.getOrderno());
			return;
		}
		
		log.debug("更新二维码支付订单[{}]支付状态为：支付成功", order.getOrderno());
		QrcodeOrder order4Mod = new QrcodeOrder();
		order4Mod.setId(order.getId());
		order4Mod.setStatus(QrcodeOrderStatus.SUCCESS.ordinal());
		order4Mod.setStatusDesc("支付成功");
		qrcodeOrderMapper.mod(order4Mod);
		
		RequestBean<String> transData = new RequestBean<String>();
		transData.setData(order.getOrderno());
		qrcodeTransFeign.addByOrderno(transData);
		
		String payeeMerchno = systemParamService.getByCode(SystemParamCode.SYSTEM_PAYEE_MERCHNO.name()).getValue();
		if (!order.getMerchno().equals(payeeMerchno)) {
			
			RequestBean<String> orderData = new RequestBean<String>();
			orderData.setData(orderno);
			agentTransFeign.addByQrcodeOrderno(orderData);
			
			AgentTrans4Search trans4s = new AgentTrans4Search();
			trans4s.setRefno(orderno);
			trans4s.setTransType(SysTransType.QRCODE.ordinal());
			RequestBean<AgentTrans4Search> transData4s = new RequestBean<AgentTrans4Search>();
			transData4s.setData(trans4s);
			agentVirtualCardFeign.rechargeByAgentTrans(transData4s);
			
			
			// 清算
			//liquidate(trans);
			
			// 公众号通知
			//OfficalNoticeFactory.notice(trans);
		}
		
		String notifyUrl = order.getNotifyUrl();
		if (!StringUtils.isEmpty(notifyUrl) && notifyUrl.indexOf("/gradeOrder/notify") != -1) {
			gradeOrderService.paySuccess(orderno);
		} 
		/*
		else {
			// 异步通知接口
			//PayNotifyFactory.getPayNotify(order).execute();
		}
		*/
		
	}

	@Override
	public void checkPayStatus(String orderno) throws Exception {
		
		QrcodeOrder4Search order4s = new QrcodeOrder4Search();
		if (!StringUtils.isEmpty(orderno)) {
			log.info("查询二维码订单支付状态：{}", orderno);
			order4s.setOrderno(orderno);
		} else {
			log.info("查询最近{}分钟内二维码下单成功,但是尚未支付，尚未查询的订单信息", orderCheckInMinutes);
			Date date = new Date();
			date.setTime(date.getTime() - orderCheckInMinutes * 60000);
			order4s.setStatus(QrcodeOrderStatus.NEW.ordinal());
			order4s.setStartDateTime(DateFormat.DATE_TIME.format(date));
		}
		
		List<QrcodeOrder> list = listByPage(order4s);
		int count = list.size();
		if (count == 0) {
			log.info("找不二维码支付订单");
			return;
		}
		log.info("查找到[{}]笔二维码支付订单", count);
		for(int i = 0; i < count; i++){
			QrcodeOrder order = list.get(i);
			log.info("[" + (i + 1) + "/" + count + "]开始处理二维码订单信息:\r\n" + order);
			
			ChannelApi channelApi = null;
			
			if("YUFU_QRCODE".equals(order.getChannelCode())){
				channelApi = yufuQucodeApi;
			}else if("PINGAN".equals(order.getChannelCode())){
				channelApi = pinganApi;
			}else if("SAO_BEI".equals(order.getChannelCode())){
				channelApi = saoBeiQrcodeApi;
			}else{
				channelApi = saoBeiQrcodeApi;
			}
			ChannelOrderStatusResult statusResult = channelApi.queryQrcodePay(order);
			if (statusResult.getStatus() == ChannelOrderStatus.SUCCESS) {
				order.setChannelOrderno(statusResult.getChannelOrderNo());
				qrcodePaySuccess(order);
			}
		}

	}

	@Override
	@ServiceLogAnnotation(moduleName="扫码支付成功回调处理")
	public void qrcodePaySuccess(QrcodeOrder order) {
		
		String channelOrderNo = order.getChannelOrderno();
		String orderno = order.getOrderno();
		order = qrcodeOrderMapper.getOrderInfo(order);
		
		log.info("更新二维码支付订单[{}]支付状态为：支付成功", orderno);
		order.setStatus(QrcodeOrderStatus.SUCCESS.ordinal());
		order.setStatusDesc(QrcodeOrderStatus.SUCCESS.getText());
		order.setChannelOrderno(channelOrderNo);
		qrcodeOrderMapper.modifyQrcodeOrder(order);
		
		RequestBean<String> transData = new RequestBean<String>();
		transData.setData(order.getOrderno());
		qrcodeTransFeign.addByOrderno(transData);
		
		String payeeMerchno = systemParamService.getByCode(SystemParamCode.SYSTEM_PAYEE_MERCHNO.name()).getValue();
		if (!order.getMerchno().equals(payeeMerchno)) {
			
			RequestBean<String> orderData = new RequestBean<String>();
			orderData.setData(orderno);
			agentTransFeign.addByQrcodeOrderno(orderData);
			
			AgentTrans4Search trans4s = new AgentTrans4Search();
			trans4s.setRefno(orderno);
			trans4s.setTransType(SysTransType.QRCODE.ordinal());
			RequestBean<AgentTrans4Search> transData4s = new RequestBean<AgentTrans4Search>();
			transData4s.setData(trans4s);
			agentVirtualCardFeign.rechargeByAgentTrans(transData4s);
		}else{
			gradeOrderService.paySuccess(orderno);
		}
		
		String notifyUrl = order.getNotifyUrl();
		if (!StringUtils.isEmpty(notifyUrl) && notifyUrl.indexOf("/gradeOrder/notify") != -1) {
			gradeOrderService.paySuccess(orderno);
		} 
	}

	@Override
	@ServiceLogAnnotation(moduleName="查询扫码订单信息")
	public QrcodeOrder getOrderInfo(QrcodeOrder order) {
		return qrcodeOrderMapper.getOrderInfo(order);
	}
}
