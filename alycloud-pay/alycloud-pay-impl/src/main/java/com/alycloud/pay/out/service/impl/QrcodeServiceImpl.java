package com.alycloud.pay.out.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.alycloud.modules.enums.SysLiquidateType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.enums.SysT0RateType;
import com.alycloud.modules.search.QrcodeMerch4Search4Pay;
import com.alycloud.modules.search.QrcodeMerchFee4Search;
import com.alycloud.modules.vo.ExternalQrcodeVO;
import com.alycloud.pay.feign.AgentInfoFeign;
import com.alycloud.pay.feign.BranchInfoFeign;
import com.alycloud.pay.feign.MerchInfoFeign;
import com.alycloud.pay.mapper.QrcodeMerchFeeMapper;
import com.alycloud.pay.mapper.QrcodeMerchMapper;
import com.alycloud.pay.mapper.QrcodeOrderMapper;
import com.alycloud.pay.out.service.IQrcodeService;
import com.alycloud.pay.service.impl.QrcodeFeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QrcodeServiceImpl implements IQrcodeService {

	@Autowired
	public MerchInfoFeign merchInfoFeign;
	@Autowired
	public AgentInfoFeign agentInfoFeign;
	@Autowired
	public BranchInfoFeign branchInfoFeign;
	@Autowired
	public QrcodeOrderMapper qrcodeOrderMapper;
	@Autowired
	private QrcodeFeeService feeService;
	@Autowired
	private QrcodeMerchMapper qrcodeMerchMapper;
	@Autowired
	private QrcodeMerchFeeMapper qrcodeMerchFeeMapper;
	
	@Override
	@ServiceLogAnnotation(moduleName="对外接口---扫码支付")
	public QrcodeOrder buildOrder(ExternalQrcodeVO qrcodeVO) throws Exception {
		
		RequestBean<String> reqData = new RequestBean<String>();
		reqData.setData(qrcodeVO.getMerchno());
		MerchInfo merch = merchInfoFeign.getByMerchno(reqData).getData();
		reqData.setData(merch.getSuperAgent());
		AgentInfo agent = agentInfoFeign.getByAgentno(reqData).getData();
		reqData.setData(merch.getBranchno());
		BranchInfo branch = branchInfoFeign.getByBranchno(reqData).getData();
		
		QrcodeOrder order = new QrcodeOrder();
		order.setAgentName(null != agent ? agent.getAgentName():branch.getBranchName());
		order.setAgentno(null != agent ? agent.getAgentno():branch.getBranchno());
		order.setAmount(new BigDecimal(qrcodeVO.getAmount()));
		order.setBranchName(branch.getBranchName());
		order.setBranchno(branch.getBranchno());
		order.setChannelCode("SAO_BEI");
		order.setMerchName(merch.getMerchName());
		order.setMerchno(qrcodeVO.getMerchno());
		order.setCertno(merch.getIdentityCard());
		order.setAccountno(merch.getAccountno());
		order.setAccountName(merch.getAccountName());
		order.setBankno(merch.getBankno());
		order.setBankName(merch.getBankName());
		order.setMobile(merch.getMobile());
		order.setNotifyUrl(qrcodeVO.getNotifyUrl());
		order.setOrderno(qrcodeVO.getTraceno());
		order.setTraceno(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		order.setPaymentFee(new BigDecimal("0"));
		order.setPayType(qrcodeVO.getPayType());
		order.setQueryCount(0);
		order.setScanType(QrcodeScanType.PASSIVE.ordinal());
		order.setStatus(QrcodeOrderStatus.NEW.ordinal());
		order.setStatusDesc("待支付");
		order.setTransDate(DateFormat.DATE.format());
		order.setTransTime(DateFormat.TIME.format());
		
		qrcodeOrderMapper.add(order);
		
		calChannelAndFee(order);
		
		return order;
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
	@ServiceLogAnnotation(moduleName="获取扫码订单信息")
	public QrcodeOrder getOrderInfo(QrcodeOrder order) {
		return qrcodeOrderMapper.getOrderInfo(order);
	}
}
