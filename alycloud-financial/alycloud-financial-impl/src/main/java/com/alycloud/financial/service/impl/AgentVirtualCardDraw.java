package com.alycloud.financial.service.impl;

import java.math.BigDecimal;

import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.financial.api.IAgentVirtualCardService;
import com.alycloud.financial.api.IAgentVirtualTransService;
import com.alycloud.financial.api.IPaymentTransService;
import com.alycloud.financial.feign.BranchInfoFeign;
import com.alycloud.financial.support.payment.Payment;
import com.alycloud.financial.support.payment.PaymentInfo;
import com.alycloud.financial.support.payment.PaymentNotFoundException;
import com.alycloud.financial.support.payment.impl.PaymentFactory;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentVirtualCard;
import com.alycloud.modules.entity.AgentVirtualTrans;
import com.alycloud.modules.entity.BranchInfo;
import com.alycloud.modules.entity.PaymentTrans;
import com.alycloud.modules.enums.PaymentTransBizType;
import com.alycloud.modules.enums.PaymentTransPayStatus;
import com.alycloud.modules.enums.SysSettleType;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理商虚拟账户提现
 * @author Moyq5
 * @date 2017年10月24日
 */
@Slf4j
public class AgentVirtualCardDraw {

	private IAgentVirtualCardService agentVirtualCardService;
	private IAgentVirtualTransService agentVirtualTransService;
	private IPaymentTransService paymentTransService;
	private BranchInfoFeign branchInfoFeign;
	
	private AgentInfo agent;
	private String amount;
	public AgentVirtualCardDraw(AgentInfo agent, String amount) {
		this.agent = agent;
		this.amount = amount;
		this.agentVirtualCardService = SpringContextUtils.getBean(IAgentVirtualCardService.class);
		this.agentVirtualTransService = SpringContextUtils.getBean(IAgentVirtualTransService.class);
		this.paymentTransService = SpringContextUtils.getBean(IPaymentTransService.class);
		this.branchInfoFeign = SpringContextUtils.getBean(BranchInfoFeign.class);
	}
	
	public AgentVirtualTrans draw() throws Exception {
		AgentVirtualCard card = agentVirtualCardService.getByAgentno(agent.getAgentno());
		
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
			throw new Exception("分润提现失败,找不到相关代付渠道");
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
		
		AgentVirtualTrans trans = addAgentVirtualTrans(card, paymentInfo, transAmount);
		
		AgentVirtualCard card4AvailAmount = new AgentVirtualCard();
		card4AvailAmount.setId(card.getId());
		card4AvailAmount.setAvailAmount(transAmount.multiply(new BigDecimal("-1")));
		agentVirtualCardService.addAvailAmount(card4AvailAmount);
		
		addPaymentTrans(trans, paymentInfo);
		
		return trans;
	}

	/**
	 * 创建提现流水
	 * @author Moyq5
	 * @date 2017年10月24日
	 * @param card 提现虚拟账户
	 * @param transAmount 提现金额
	 * @param transFee 提现费
	 * @param settleType 结算类型
	 * @return
	 * @throws Exception 
	 */
	private AgentVirtualTrans addAgentVirtualTrans(AgentVirtualCard card, PaymentInfo paymentInfo, BigDecimal transAmount) throws Exception {
		final BigDecimal transFee = paymentInfo.getFee();
		final SysSettleType settleType = paymentInfo.getSettleType();
		
		BigDecimal payAmount = transAmount.subtract(transFee);
		log.info("最终代付金额(元):{}", payAmount);
		
		RequestBean<String> branchFeignData = new RequestBean<String>();
		branchFeignData.setData(agent.getBranchno());
		BranchInfo branch = branchInfoFeign.getByBranchno(branchFeignData).getData();
		
		// 提现流水号
		String traceno = agentVirtualTransService.genRefno();
		
		AgentVirtualTrans trans = new AgentVirtualTrans();
		trans.setAccountName(agent.getAccountName());
		trans.setAccountno(agent.getAccountno());
		trans.setAddTime(DateFormat.DATE_TIME.format());
		trans.setAgentName(agent.getAgentName());
		trans.setAgentno(agent.getAgentno());
		trans.setAmount(payAmount);
		trans.setAmountAfter(card.getAvailAmount().subtract(transAmount));
		trans.setAmountBefore(card.getAvailAmount());
		trans.setBankName(agent.getBankName());
		trans.setBankno(agent.getBankno());
		trans.setBranchName(branch.getBranchName());
		trans.setBranchno(branch.getBranchno());
		trans.setCardno(card.getCardno());
		//trans.setPaymentType(paymentType);
		trans.setPayMsg(PaymentTransPayStatus.PAYING.getText());
		trans.setPayStatus(PaymentTransPayStatus.PAYING.ordinal());
		//trans.setProcessTime(processTime);
		//trans.setRemark(remark);
		trans.setSettleType(settleType.ordinal());
		trans.setStatus(1);
		trans.setTraceno(traceno);
		trans.setTransAmount(transAmount);
		trans.setTransDate(DateFormat.DATE.format());
		trans.setTransFee(transFee);
		trans.setTransRefno(card.getCardno());
		trans.setTransTime(DateFormat.TIME.format());
		//trans.setTransType(transType);
		//trans.setTypeCode(typeCode);
		
		trans = agentVirtualTransService.add(trans);
		return trans;
	}

	/**
	 * 根据提现流水创建代付流水
	 * @author Moyq5
	 * @date 2017年10月24日
	 * @param trans 提现流水
	 * @return
	 * @throws Exception 
	 */
	private PaymentTrans addPaymentTrans(AgentVirtualTrans trans, PaymentInfo paymentInfo) throws Exception {
		// 代付流水号
		String payOrderno = paymentTransService.genRefno();
		
		PaymentTrans payTrans = new PaymentTrans();
		payTrans.setAccountName(trans.getAccountName());
		payTrans.setAccountNo(trans.getAccountno());
		payTrans.setAddTime(DateFormat.DATE_TIME.format());
		payTrans.setBankName(trans.getBankName());
		payTrans.setBankNo(trans.getBankno());
		//payTrans.setBankType(trans.get);
		payTrans.setBizType(PaymentTransBizType.AGENT_DRAW.ordinal());
		//payTrans.setChannelMerchno(channelMerchno);
		//payTrans.setChannelOrderno(channelOrderno);
		//payTrans.setChannelType(channelType);
		//payTrans.setId(id);
		payTrans.setIdCardNo(agent.getIdentityCard());
		payTrans.setIsPayAgain(0);
		payTrans.setLiquidateType(paymentInfo.getLiquidateType().ordinal());
		payTrans.setMobile(agent.getMobile());
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
		
		payTrans = paymentTransService.add(payTrans);
		return payTrans;
	}

}
