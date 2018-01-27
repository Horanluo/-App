package com.alycloud.financial.service.impl;

import java.math.BigDecimal;
import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.financial.api.IMerchVirtualCardService;
import com.alycloud.financial.api.IMerchVirtualTransService;
import com.alycloud.financial.api.IPaymentTransService;
import com.alycloud.financial.feign.AgentInfoFeign;
import com.alycloud.financial.feign.BranchInfoFeign;
import com.alycloud.financial.support.payment.Payment;
import com.alycloud.financial.support.payment.PaymentInfo;
import com.alycloud.financial.support.payment.PaymentNotFoundException;
import com.alycloud.financial.support.payment.impl.PaymentFactory;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.BranchInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.modules.entity.PaymentTrans;
import com.alycloud.modules.enums.PaymentTransBizType;
import com.alycloud.modules.enums.PaymentTransPayStatus;
import com.alycloud.modules.search.MerchVirtualCard4Search;
import lombok.extern.slf4j.Slf4j;

/**
 * 商户虚拟账户提现
 * @author Moyq5
 * @date 2017年11月24日
 */
@Slf4j
public class MerchVirtualCardDraw {

	private IMerchVirtualCardService merchVirtualCardService;
	private IMerchVirtualTransService merchVirtualTransService;
	private IPaymentTransService paymentTransService;
	private BranchInfoFeign branchInfoFeign;
	private AgentInfoFeign agentInfoFeign;
	//private GradeFeeFeign gradeFeeFeign;
	
	private MerchInfo merch;
	private String amount;
	public MerchVirtualCardDraw(MerchInfo merch, String amount) {
		this.merch = merch;
		this.amount = amount;
		this.merchVirtualCardService = SpringContextUtils.getBean(IMerchVirtualCardService.class);
		this.merchVirtualTransService = SpringContextUtils.getBean(IMerchVirtualTransService.class);
		this.paymentTransService = SpringContextUtils.getBean(IPaymentTransService.class);
		this.branchInfoFeign = SpringContextUtils.getBean(BranchInfoFeign.class);
		this.agentInfoFeign = SpringContextUtils.getBean(AgentInfoFeign.class);
		//this.gradeFeeFeign = SpringContextUtils.getBean(GradeFeeFeign.class);
	}
	
	public MerchVirtualTrans draw() throws Exception {
		MerchVirtualCard4Search card4s = new MerchVirtualCard4Search();
		card4s.setMerchno(merch.getMerchno());
		card4s.setChannelType(-1);
		MerchVirtualCard card = merchVirtualCardService.listByPage(card4s).get(0);
		
		BigDecimal availAmount = card.getAvailAmount();
		log.info("可提现金额(元):{}", availAmount);
		
		BigDecimal transAmount = new BigDecimal(amount);
		log.info("目标提现金额(元):{}", transAmount);
		
		if (availAmount.compareTo(transAmount) == -1) {
			throw new ApiException(ApiCode.C0011);
		}
		
		Payment payment;
		try {
			payment = PaymentFactory.getPayment();
		} catch (PaymentNotFoundException e) {
			throw new Exception("提现失败,找不到相关代付渠道");
		}
		
		PaymentInfo paymentInfo = payment.getPaymentInfo();
		Boolean isPaymentTime = paymentInfo.isPaymentTime();
		BigDecimal minAmount = paymentInfo.getMinAmount();
		BigDecimal maxAmount = paymentInfo.getMaxAmount();
		
		if (!isPaymentTime) {
			throw new ApiException(ApiCode.C0012);
		}
		
		if (transAmount.compareTo(minAmount) == -1) {
			throw new Exception("代付金额不能小于: " + minAmount + "元");
		}
		
		if (transAmount.compareTo(maxAmount) == 1) {
			log.info("目标提现金额[{}]大于单笔最大金额[{}],将分笔提现", transAmount, maxAmount);
			transAmount = maxAmount.add(BigDecimal.ZERO);
			log.info("本次实际提现金额(元):{}", transAmount);
		}
		
		MerchVirtualTrans trans = addMerchVirtualTrans(card, paymentInfo, transAmount);
		
		MerchVirtualCard card4AvailAmount = new MerchVirtualCard();
		card4AvailAmount.setId(card.getId());
		card4AvailAmount.setAvailAmount(transAmount.multiply(new BigDecimal("-1")));
		merchVirtualCardService.addAvailAmount(card4AvailAmount);
		
		addPaymentTrans(trans, paymentInfo);
		
		return trans;
	}

	/**
	 * 创建提现流水
	 * @author Moyq5
	 * @date 2017年11月13日
	 * @param card 提现虚拟账户
	 * @param transAmount 提现金额
	 * @param transFee 提现费
	 * @param settleType 结算类型
	 * @return
	 * @throws Exception 
	 */
	private MerchVirtualTrans addMerchVirtualTrans(MerchVirtualCard card, PaymentInfo paymentInfo, BigDecimal transAmount) throws Exception {
		final BigDecimal transFee = paymentInfo.getFee();
		//final SysSettleType settleType = paymentInfo.getSettleType();
		
		BigDecimal payAmount = transAmount.subtract(transFee);
		log.info("最终代付金额(元):{}", payAmount);
		
		RequestBean<String> branchData = new RequestBean<String>();
		branchData.setData(merch.getBranchno());
		BranchInfo branch = branchInfoFeign.getByBranchno(branchData).getData();
		
		RequestBean<String> agentData = new RequestBean<String>();
		agentData.setData(merch.getSuperAgent());
		AgentInfo agent = agentInfoFeign.getByAgentno(agentData).getData();
		
		// 提现流水号
		String traceno = merchVirtualTransService.genRefno();
		
		MerchVirtualTrans trans = new MerchVirtualTrans();
		trans.setAccountName(merch.getAccountName());
		trans.setAccountno(merch.getAccountno());
		trans.setAddTime(DateFormat.DATE_TIME.format());
		trans.setAgentName(agent.getAgentName());
		trans.setAgentno(agent.getAgentno());
		trans.setAmount(payAmount);
		trans.setAmountAfter(card.getAvailAmount().subtract(transAmount));
		trans.setAmountBefore(card.getAvailAmount());
		trans.setBankName(merch.getBankName());
		trans.setBankno(merch.getBankno());
		trans.setBranchName(branch.getBranchName());
		trans.setBranchno(branch.getBranchno());
		trans.setCardno(card.getCardno());
		trans.setMerchName(merch.getMerchName());
		trans.setMerchno(merch.getMerchno());
		//trans.setPaymentType(paymentType);
		trans.setPayMsg(PaymentTransPayStatus.PAYING.getText());
		trans.setPayStatus(PaymentTransPayStatus.PAYING.ordinal());
		//trans.setProcessTime(processTime);
		//trans.setRemark(remark);
		//trans.setSettleType(settleType.ordinal());
		trans.setStatus(1);
		trans.setTraceno(traceno);
		trans.setTransAmount(transAmount);
		trans.setTransDate(DateFormat.DATE.format());
		trans.setTransFee(transFee);
		trans.setTransRefno(card.getCardno());
		trans.setTransTime(DateFormat.TIME.format());
		//trans.setFee(transFee);
		//trans.setTransType(transType);
		//trans.setTypeCode(typeCode);
		//List<GradeFee> gradeFeeList = gradeFeeFeign.queryGradeInfo(card.getMerchno()).getData();
		//trans.setRate(gradeFeeList.get(0).getRate());
		//trans.setWithdrawFee(gradeFeeList.get(0).getRate().multiply(transAmount));
		
		trans = merchVirtualTransService.add(trans);
		return trans;
	}

	/**
	 * 根据提现流水创建代付流水
	 * @author Moyq5
	 * @date 2017年11月13日
	 * @param trans 提现流水
	 * @return
	 * @throws Exception 
	 */
	private PaymentTrans addPaymentTrans(MerchVirtualTrans trans, PaymentInfo paymentInfo) throws Exception {
		// 代付流水号
		String payOrderno = paymentTransService.genRefno();
		
		PaymentTrans payTrans = new PaymentTrans();
		payTrans.setAccountName(trans.getAccountName());
		payTrans.setAccountNo(trans.getAccountno());
		payTrans.setAddTime(DateFormat.DATE_TIME.format());
		payTrans.setBankName(trans.getBankName());
		payTrans.setBankNo(trans.getBankno());
		//payTrans.setBankType(trans.get);
		payTrans.setBizType(PaymentTransBizType.MERCH_DRAW.ordinal());
		//payTrans.setChannelMerchno(channelMerchno);
		//payTrans.setChannelOrderno(channelOrderno);
		//payTrans.setChannelType(channelType);
		//payTrans.setId(id);
		payTrans.setIdCardNo(merch.getIdentityCard());
		payTrans.setIsPayAgain(0);
		payTrans.setLiquidateType(paymentInfo.getLiquidateType().ordinal());
		payTrans.setMobile(merch.getMobile());
		payTrans.setPayType(paymentInfo.getPayType().ordinal());
		payTrans.setPayAmount(trans.getAmount());
		payTrans.setPayDescr(PaymentTransPayStatus.PAYING.getText());
		payTrans.setPayOrderno(payOrderno);
		payTrans.setPayStatus(PaymentTransPayStatus.PAYING.ordinal());
		payTrans.setPayTime(DateFormat.DATE_TIME.format());
		payTrans.setRefno(trans.getTraceno());
		payTrans.setSettleType(paymentInfo.getSettleType().ordinal());
		//payTrans.setTraceno(traceno);
		payTrans.setTransAmount(trans.getTransAmount());
		payTrans.setTransFee(trans.getTransFee());
		payTrans.setFee(trans.getFee());
		payTrans.setWithdrawFee(trans.getWithdrawFee());
		payTrans.setRate(trans.getRate());
		
		payTrans = paymentTransService.add(payTrans);
		return payTrans;
	}

}
