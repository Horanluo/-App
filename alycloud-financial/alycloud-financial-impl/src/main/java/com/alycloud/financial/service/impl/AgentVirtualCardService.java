package com.alycloud.financial.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.core.utils.CommonUtil;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.core.utils.MD5;
import com.alycloud.financial.api.IAgentTransService;
import com.alycloud.financial.api.IAgentVirtualCardService;
import com.alycloud.financial.api.IAgentVirtualTransService;
import com.alycloud.financial.api.IPaymentTransService;
import com.alycloud.financial.feign.AgentInfoFeign;
import com.alycloud.financial.feign.BranchInfoFeign;
import com.alycloud.financial.feign.ProxyFeign;
import com.alycloud.financial.mapper.AgentVirtualCardMapper;
import com.alycloud.financial.mapper.AgentVirtualRechargeMapper;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentTrans;
import com.alycloud.modules.entity.AgentVirtualCard;
import com.alycloud.modules.entity.AgentVirtualRecharge;
import com.alycloud.modules.entity.AgentVirtualTrans;
import com.alycloud.modules.entity.AgentWithdraw;
import com.alycloud.modules.entity.BranchInfo;
import com.alycloud.modules.entity.PaymentTrans;
import com.alycloud.modules.enums.AgentVirtualCardStatus;
import com.alycloud.modules.enums.AgentVirtualRechargeStatus;
import com.alycloud.modules.enums.AgentVirtualRechargeType;
import com.alycloud.modules.enums.PaymentTransBizType;
import com.alycloud.modules.enums.PaymentTransPayStatus;
import com.alycloud.modules.search.AgentTrans4Search;
import com.alycloud.modules.search.AgentVirtualRecharge4Search;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理商虚拟账户Service实现类
 * @author Moyq5
 * @date 2017年10月24日
 */
@Service
@Slf4j
public class AgentVirtualCardService implements IAgentVirtualCardService {

	@Autowired
	private AgentVirtualCardMapper agentVirtualCardMapper;
	@Autowired
	private AgentVirtualRechargeMapper agentVirtualRechargeMapper;
	@Autowired
	private IAgentTransService agentTransService;
	@Autowired
	private AgentInfoFeign agentInfoFeign;
	@Autowired
	private BranchInfoFeign branchInfoFeign;
	@Autowired
	private IAgentVirtualTransService agentVirtualTransService;
	@Autowired
	private IPaymentTransService paymentTransService;
	@Autowired
	private ProxyFeign proxyFeign;
	@Value("${proxy_minAmount}")
    private String proxyMinAmount;
	@Value("${proxy_maxAmount}")
    private String proxyMaxAmount;
	@Value("${withdrawFee}")
    private String withdrawFee;
	
	@Override
	public AgentVirtualCard add4Agent(String agentno) throws Exception {
		AgentVirtualCard card = agentVirtualCardMapper.getByAgentno(agentno);
		if (null != card) {
			throw new Exception("代理商["+ agentno +"]账户已经存在不能重复创建");
		}
		RequestBean<String> agentData = new RequestBean<String>();
		agentData.setData(agentno);
		AgentInfo agent = agentInfoFeign.getByAgentno(agentData).getData();
		AgentVirtualCard newCard = new AgentVirtualCard();
		newCard.setAccountName(agent.getAccountName());
		newCard.setAccountno(agent.getAccountno());
		newCard.setAddTime(DateFormat.DATE_TIME.format());
		newCard.setAgentno(agent.getAgentno());
		newCard.setAvailAmount(BigDecimal.ZERO);
		newCard.setBankName(agent.getBankName());
		newCard.setBankno(agent.getBankno());
		//newCard.setBizType(4);
		newCard.setBranchno(agent.getBranchno());
		newCard.setCardno(createCardno(agent.getBranchno()));
		//newCard.setFrozenTime(frozenTime);
		newCard.setPasswd(MD5.getMD5(CommonUtil.mkRandomStr(5), "UTF-8"));
		newCard.setPayKey(CommonUtil.mkRandomStr(32));
		//newCard.setPayType(1);
		//newCard.setRateCode("1001");
		newCard.setStatus(AgentVirtualCardStatus.NORMAL.ordinal());
		newCard.setTransitAmount(BigDecimal.ZERO);
		newCard.setValidDate("2099-12-31");
		agentVirtualCardMapper.add(newCard);
		return newCard;
	}
	
	@Override
	@ServiceLogAnnotation(moduleName="代理商分润提现")
	public synchronized SingleResult<String> draw(AgentInfo agent, String amount) throws Exception {
		SingleResult<String> singleResult = new SingleResult<String>();
		AgentVirtualCard card = getByAgentno(agent.getAgentno());
		
		BigDecimal availAmount = card.getAvailAmount();
		log.info("可提现金额(元):{}", availAmount);
		
		BigDecimal transAmount = new BigDecimal(amount);
		log.info("目标提现金额(元):{}", transAmount);
		
		if (availAmount.compareTo(transAmount) == -1) {
			singleResult.setSuccess(false);
			singleResult.setResult("可提现金额不够");
			return singleResult;
		}
		
		if (transAmount.compareTo(new BigDecimal(proxyMinAmount)) == -1) {
			throw new Exception("代付金额不能小于: " + proxyMinAmount + "元");
		}
		
		if (transAmount.compareTo(new BigDecimal(proxyMaxAmount)) == 1) {
			log.info("目标提现金额[{}]大于单笔最大金额[{}],将分笔提现", transAmount, proxyMaxAmount);
			transAmount = new BigDecimal(proxyMaxAmount);
			log.info("本次实际提现金额(元):{}", transAmount);
		}
		//提现流水号
		String withdrawTraceNo = "ysf-trans-"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		AgentWithdraw agentWithdraw = new AgentWithdraw();
		agentWithdraw.setWithdrawAmt(transAmount.subtract(new BigDecimal(withdrawFee)));
		agentWithdraw.setAgentVirtualCard(card);
		agentWithdraw.setTraceno(withdrawTraceNo);
		
		String proxyResult = proxyFeign.singleProxy(agentWithdraw);
		log.info("代付响应结果:{},代付订单号:{}",proxyResult,withdrawTraceNo);
		
		JSONObject proxyJsonResult = JSONObject.parseObject(proxyResult);
		if("success".equals(proxyJsonResult.getString("result"))){
			AgentVirtualTrans trans = addAgentVirtualTrans(card, agent, transAmount,withdrawTraceNo);
			
			AgentVirtualCard card4AvailAmount = new AgentVirtualCard();
			card4AvailAmount.setId(card.getId());
			card4AvailAmount.setAvailAmount(transAmount.multiply(new BigDecimal("-1")));
			agentVirtualCardMapper.addAvailAmount(card4AvailAmount);
			
			addPaymentTrans(trans, agent);
			singleResult.setSuccess(true);
			singleResult.setResult("提现成功，正在出款");
			return singleResult;
		}
		singleResult.setSuccess(false);
		singleResult.setResult("提现失败");
		return singleResult;
	}

	/**
	 * 创建提现流水
	 * @author Horanluo
	 * @date 2018年1月19日
	 * @param card 提现虚拟账户
	 * @param transAmount 提现金额
	 * @param transFee 提现费
	 * @param settleType 结算类型
	 * @return
	 * @throws Exception 
	 */
	@ServiceLogAnnotation(moduleName="创建提现流水")
	private AgentVirtualTrans addAgentVirtualTrans(AgentVirtualCard card, AgentInfo agent, BigDecimal transAmount,
			String withdrawTraceNo) throws Exception {
		final BigDecimal transFee = new BigDecimal(withdrawFee);
		BigDecimal payAmount = transAmount.subtract(transFee);
		log.info("最终代付金额(元):{}", payAmount);
		
		RequestBean<String> branchFeignData = new RequestBean<String>();
		branchFeignData.setData(agent.getBranchno());
		BranchInfo branch = branchInfoFeign.getByBranchno(branchFeignData).getData();
		
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
		trans.setPayMsg(PaymentTransPayStatus.PAYING.getText());
		trans.setPayStatus(PaymentTransPayStatus.PAYING.ordinal());
		trans.setSettleType(0);
		trans.setStatus(1);
		trans.setTraceno(withdrawTraceNo);
		trans.setTransAmount(transAmount);
		trans.setTransDate(DateFormat.DATE.format());
		trans.setTransFee(transFee);
		trans.setTransTime(DateFormat.TIME.format());
		trans = agentVirtualTransService.add(trans);
		
		return trans;
	}

	/**
	 * 根据提现流水创建代付流水
	 * @author Horanluo
	 * @date 2018年1月19日
	 * @param trans 提现流水
	 * @return
	 * @throws Exception 
	 */
	@ServiceLogAnnotation(moduleName="创建代付流水")
	private PaymentTrans addPaymentTrans(AgentVirtualTrans trans, AgentInfo agent) throws Exception {
		// 代付流水号
		//String payOrderno = paymentTransService.genRefno();
		
		PaymentTrans payTrans = new PaymentTrans();
		payTrans.setAccountName(trans.getAccountName());
		payTrans.setAccountNo(trans.getAccountno());
		payTrans.setAddTime(DateFormat.DATE_TIME.format());
		payTrans.setBankName(trans.getBankName());
		payTrans.setBankNo(trans.getBankno());
		payTrans.setBizType(PaymentTransBizType.AGENT_DRAW.ordinal());
		payTrans.setIdCardNo(agent.getIdentityCard());
		payTrans.setIsPayAgain(0);
		payTrans.setLiquidateType(0);
		payTrans.setMobile(agent.getMobile());
		payTrans.setPayType(1);
		payTrans.setPayAmount(trans.getAmount());
		payTrans.setPayDescr(PaymentTransPayStatus.PAYING.getText());
		//payTrans.setPayOrderno(payOrderno);
		payTrans.setPayStatus(PaymentTransPayStatus.PAYING.ordinal());
		payTrans.setPayTime(DateFormat.DATE_TIME.format());
		payTrans.setRefno(trans.getTraceno());
		payTrans.setSettleType(0);
		payTrans.setTransAmount(trans.getTransAmount());
		payTrans.setTransFee(trans.getTransFee());
		payTrans.setWithdrawFee(trans.getTransFee());
		payTrans = paymentTransService.add(payTrans);
		
		return payTrans;
	}
	
	@Override
	@ServiceLogAnnotation(moduleName="获取代理商信息")
	public AgentVirtualCard getByAgentno(String agentno) {
		return agentVirtualCardMapper.getByAgentno(agentno);
	}

	@Override
	public synchronized void recharge(AgentTrans4Search trans4s) {
		List<AgentTrans> transList = agentTransService.listByPage(trans4s);
		for (AgentTrans trans: transList) {
			recharge(trans);
		}
		
	}

	private void recharge(AgentTrans trans) {
		if (trans.getAgentno().contentEquals(trans.getBranchno())) {
			log.debug("机构[{}]无虚拟账户，不用充值", trans.getBranchno());
			return;
		}
		AgentVirtualRecharge4Search recharge4s = new AgentVirtualRecharge4Search();
		recharge4s.setType(AgentVirtualRechargeType.AGENT_TRANS.ordinal());
		recharge4s.setRefno(trans.getRefno());
		Integer count = agentVirtualRechargeMapper.count(recharge4s);
		if (count > 0) {
			log.warn("分润[{}]充值记录已经存在", trans.getRefno());
			return;
		}
		AgentVirtualCard card = agentVirtualCardMapper.getByAgentno(trans.getAgentno());
		AgentVirtualRecharge recharge = buildRecharge(trans, card);
		agentVirtualRechargeMapper.add(recharge);
		
		AgentVirtualCard card4Mod = new AgentVirtualCard();
		card4Mod.setId(card.getId());
		card4Mod.setTransitAmount(recharge.getAmount());
		agentVirtualCardMapper.addTransitAmount(card4Mod);
	}

	private AgentVirtualRecharge buildRecharge(AgentTrans trans, AgentVirtualCard card) {
		AgentVirtualRecharge recharge = new AgentVirtualRecharge();
		recharge.setAddTime(DateFormat.DATE_TIME.format());
		recharge.setAgentName(trans.getAgentName());
		recharge.setAgentno(trans.getAgentno());
		recharge.setAmount(trans.getAgentFee());
		recharge.setAmountAfter(card.getAvailAmount().add(card.getTransitAmount()).add(recharge.getAmount()));
		recharge.setAmountBefore(card.getAvailAmount().add(card.getTransitAmount()));
		recharge.setBranchName(trans.getBranchName());
		recharge.setBranchno(trans.getBranchno());
		recharge.setCardno(card.getCardno());
		//recharge.setIp(ip);
		recharge.setLoginName("sys");
		recharge.setRefno(trans.getRefno());
		recharge.setRemark("分润充值");
		recharge.setStatus(AgentVirtualRechargeStatus.SUCCESS.ordinal());
		recharge.setType(AgentVirtualRechargeType.AGENT_TRANS.ordinal());
		return recharge;
	}

	private String createCardno(String branchno) throws Exception {
		StringBuffer cardno = new StringBuffer();
		cardno.append(branchno);
		String str = agentVirtualCardMapper.getMaxCardo();
		if (StringUtils.isEmpty(str)) {
			str = "00000001";
		} else {
			int num = Integer.parseInt(str);
			str = "0000000" + (++num);
			str = str.substring(str.length() - 8);
		}
		cardno.append(str);
		return cardno.toString();
	}

	@Override
	public void addAvailAmount(AgentVirtualCard card4AvailAmount) {
		agentVirtualCardMapper.addAvailAmount(card4AvailAmount);
	}
	
}
