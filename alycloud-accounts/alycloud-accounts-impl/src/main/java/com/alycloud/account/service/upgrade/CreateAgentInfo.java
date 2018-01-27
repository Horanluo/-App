package com.alycloud.account.service.upgrade;

import java.math.BigDecimal;

import com.alycloud.account.feign.AgentInfoFeign;
import com.alycloud.account.feign.MerchInfoFeign;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.enums.AgentAgentType;
import com.alycloud.modules.enums.AgentAuditStatus;
import com.alycloud.modules.enums.AgentStatus;

/**
 * 创建代理商信息
 * @author Moyq5
 * @date 2017年10月27日
 */
public class CreateAgentInfo extends Decorator {

	private MerchInfoFeign merchInfoFeign;
	private AgentInfoFeign agentInfoFeign;
	
	public CreateAgentInfo(Upgrader userUpgrader) {
		super(userUpgrader);
		merchInfoFeign = SpringContextUtils.getBean(MerchInfoFeign.class);
		agentInfoFeign = SpringContextUtils.getBean(AgentInfoFeign.class);
	}

	@Override
	public MerchUser upgrade() throws Exception {
		MerchUser user = super.upgrade();
		createAgentInfo(user);
		return user;
	}
	
	private AgentInfo createAgentInfo(MerchUser merchUser) {
		RequestBean<Integer> merchFeignData = new RequestBean<Integer>();
		merchFeignData.setData(merchUser.getMerchId());
		MerchInfo merch = merchInfoFeign.getById(merchFeignData).getData();
		
		RequestBean<String> agentFeignData = new RequestBean<String>();
		agentFeignData.setData(merch.getSuperAgent());
		AgentInfo superAgent = agentInfoFeign.getByAgentno(agentFeignData).getData();
		
		final String maxAgentno = agentInfoFeign.getMaxAgentnoByAgentno(agentFeignData).getData();
		final String newAgentno = Long.parseLong(maxAgentno) + 1 + "";
		AgentInfo newAgent = new AgentInfo();
		newAgent.setAccountName(merch.getAccountName());
		
		newAgent.setAccountno(merch.getAccountno());
		newAgent.setAccountType(merch.getAccountType());
		newAgent.setAddress(merch.getAddress());
		newAgent.setAddTime(DateFormat.DATE_TIME.format());
		newAgent.setAgentLevel(null==superAgent?1:superAgent.getAgentLevel() + 1);
		newAgent.setAgentName(merch.getMerchName());
		newAgent.setAgentno(newAgentno);
		
//		newAgent.setAgentnoFirst(null==superAgent?merch.getSuperAgent():superAgent.getAgentnoFirst());
		
		//新注册的代理商，如果上级代理商不存在，则商户的上级代理商为新代理商的一级代理商
		newAgent.setAgentnoFirst(null==superAgent?merch.getSuperAgent():superAgent.getSuperAgent());
		
		newAgent.setAgentType(AgentAgentType.COMMON_AGENT.ordinal());
		newAgent.setAppCreditRate(new BigDecimal("0.0065"));
		newAgent.setAppDebitFixed(new BigDecimal("35.00"));
		newAgent.setAppDebitRate(new BigDecimal("0.0065"));
		newAgent.setAppT0Rate(new BigDecimal("0.0010"));
		newAgent.setAreaCode(null==superAgent?merch.getAreaCode():superAgent.getAreaCode());
		newAgent.setAssuranceFee(new BigDecimal("0.00"));
		newAgent.setAuditAgentno("1000000000");
		newAgent.setAuditStatus(AgentAuditStatus.AUDITED.ordinal());
		//newAgent.setBankCardImg(bankCardImg);
		newAgent.setBankName(merch.getBankName());
		newAgent.setBankno(merch.getBankno());
		newAgent.setBizType(55);
		newAgent.setBranchno(merch.getBranchno());
		//newAgent.setContractImg(contractImg);
		newAgent.setDivide(100);
		newAgent.setEmail(merch.getEmail());
		newAgent.setFastpayRateT0(BigDecimal.ZERO);
		newAgent.setFastpayRateT1(BigDecimal.ZERO);
		newAgent.setFullName(merch.getMerchName());
		newAgent.setGatewayFixed(new BigDecimal("0.00"));
		newAgent.setGatewayRateT0(BigDecimal.ZERO);
		//newAgent.setId(id);
		newAgent.setIdentityCard(merch.getIdentityCard());
		//newAgent.setIdentityCardImg(identityCardImg);
		newAgent.setLegalName(merch.getLegalName());
		newAgent.setLinkMan(merch.getLinkMan());
		newAgent.setMemberFee(BigDecimal.ZERO);
		newAgent.setMobile(merch.getMobile());
		newAgent.setOnlineRate(new BigDecimal("0.0035"));
		newAgent.setOpenTime(DateFormat.DATE_TIME.format());
		//newAgent.setOtherFile(otherFile);
		newAgent.setPosCreditRate(new BigDecimal("0.0065"));
		newAgent.setPosDebitFixed(new BigDecimal("25.00"));
		newAgent.setPosDebitRate(new BigDecimal("0.0055"));
		newAgent.setPosT0Rate(new BigDecimal("0.0010"));
		newAgent.setStatus(AgentStatus.OPENED.ordinal());
		newAgent.setSuperAgent(null==superAgent?merch.getSuperAgent():superAgent.getAgentno());
		newAgent.setT0Divide(0);
		newAgent.setTelephone(merch.getTelephone());
		newAgent.setUserId(-1);
		newAgent.setWithdrawalFee(new BigDecimal("0.0000"));
		
		RequestBean<AgentInfo> agentAddFeignData = new RequestBean<AgentInfo>();
		agentAddFeignData.setData(newAgent);
		newAgent = agentInfoFeign.add(agentAddFeignData).getData();
		return newAgent;
	}

}
