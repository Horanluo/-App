package com.alycloud.account.service.upgrade;

import java.util.ArrayList;
import java.util.List;

import org.mortbay.log.Log;

import com.alycloud.account.api.IGradeFeeService;
import com.alycloud.account.feign.AgentInfoFeign;
import com.alycloud.account.feign.MerchInfoFeign;
import com.alycloud.account.feign.QrcodeAgentFeeFeign;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.GradeFee;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.entity.QrcodeAgentFee;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.search.AgentInfo4Search;
import com.alycloud.modules.search.GradeFee4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月29日
 */
public class CreateQrcodeAgentFee extends Decorator {

	private MerchInfoFeign merchInfoFeign;
	private AgentInfoFeign agentInfoFeign;
	private QrcodeAgentFeeFeign qrcodeAgentFeeFeign;
	private IGradeFeeService gradeFeeService;
	public CreateQrcodeAgentFee(Upgrader userUpgrader) {
		super(userUpgrader);
		merchInfoFeign = SpringContextUtils.getBean(MerchInfoFeign.class);
		agentInfoFeign = SpringContextUtils.getBean(AgentInfoFeign.class);
		qrcodeAgentFeeFeign = SpringContextUtils.getBean(QrcodeAgentFeeFeign.class);
		gradeFeeService = SpringContextUtils.getBean(IGradeFeeService.class);
	}

	@Override
	public MerchUser upgrade() throws Exception {
		MerchUser user = super.upgrade();
		createAgentFee(user);
		return user;
	}
	
	private void createAgentFee(MerchUser merchUser) throws Exception {
		AgentInfo newAgent = getNewAgent(merchUser);
		
		List<QrcodeAgentFee> newFeeList = // buildAgentFee(merchUser.getUserRank(), newAgent);// 新的未实施
											 buildAgentFee2(merchUser.getUserRank(), newAgent);// 旧的在用
		
		RequestBean<String> delData = new RequestBean<String>();
		delData.setData(newAgent.getAgentno());
		qrcodeAgentFeeFeign.delByAgentno(delData);
		
		RequestBean<List<QrcodeAgentFee>> addData = new RequestBean<List<QrcodeAgentFee>>();
		addData.setData(newFeeList);
		qrcodeAgentFeeFeign.batchAdd(addData);
	}

	private AgentInfo getNewAgent(MerchUser merchUser) throws Exception {
		RequestBean<Integer> merchFeignData = new RequestBean<Integer>();
		merchFeignData.setData(merchUser.getMerchId());
		MerchInfo merch = merchInfoFeign.getById(merchFeignData).getData();
		
		AgentInfo4Search agent4s = new AgentInfo4Search();
		agent4s.setMobile(merch.getMobile());
		RequestBean<AgentInfo4Search> agentFeignData = new RequestBean<AgentInfo4Search>();
		agentFeignData.setData(agent4s);
		List<AgentInfo> agentList = agentInfoFeign.listByPage(agentFeignData).getData();
		if (agentList.size() != 1) {
			Log.debug("代理商[{}]信息有误: size->{}", merch.getMobile(), agentList.size());
			throw new Exception("商户信息有误");
		}
		AgentInfo newAgent = agentList.get(0);
		return newAgent;
	}

	private List<QrcodeAgentFee> buildAgentFee(Integer gradeType, AgentInfo newAgent) {
		List<QrcodeAgentFee> newFeeList = new ArrayList<QrcodeAgentFee>();
		GradeFee4Search rule4s = new GradeFee4Search();
		rule4s.setGradeType(gradeType);
		List<GradeFee> ruleList = gradeFeeService.list(rule4s);
		for (GradeFee rule: ruleList) {
			QrcodeAgentFee fee = new QrcodeAgentFee();
			fee.setAgentno(newAgent.getAgentno());
			fee.setBranchno(newAgent.getBranchno());
			fee.setPayType(rule.getPayType());
			fee.setRate(rule.getRate());
			fee.setScanType(rule.getScanType());
			fee.setSettleType(rule.getSettleType());
			newFeeList.add(fee);
		}
		return newFeeList;
	}
	
	private List<QrcodeAgentFee> buildAgentFee2(Integer gradeType, AgentInfo newAgent) {
		List<QrcodeAgentFee> newFeeList = new ArrayList<QrcodeAgentFee>();
		
		GradeFee4Search rule4s = new GradeFee4Search();
		rule4s.setGradeType(gradeType);
		List<GradeFee> ruleList = gradeFeeService.list(rule4s);
		
		QrcodePayType[] payTypes = QrcodePayType.values();
		
		AgentInfo agent4Mod = new AgentInfo();
		agent4Mod.setId(newAgent.getId());
		
		boolean isAgentMod = false;
		
		for (GradeFee rule: ruleList) {
			for (QrcodePayType payType: payTypes) {
				if ((rule.getPayType()&(1<<payType.ordinal())) == (1<<payType.ordinal())) {
					if (payType == QrcodePayType.FAST) {
						if ((rule.getSettleType()&(1<<SysSettleType.T0.ordinal())) == (1<<SysSettleType.T0.ordinal())) {
							agent4Mod.setFastpayRateT0(rule.getRate());
							isAgentMod = true;
						}
						if ((rule.getSettleType()&(1<<SysSettleType.T1.ordinal())) == (1<<SysSettleType.T1.ordinal())) {
							agent4Mod.setFastpayRateT1(rule.getRate());
							isAgentMod = true;
						}
						continue;
					}
					QrcodeAgentFee fee = buildAgentFeeItem(newAgent, rule, payType);
					newFeeList.add(fee);
				}
			}
			
		}
		if (isAgentMod) {
			RequestBean<AgentInfo> agentData = new RequestBean<AgentInfo>();
			agentData.setData(agent4Mod);
			agentInfoFeign.mod(agentData);
		}
		return newFeeList;
	}


	private QrcodeAgentFee buildAgentFeeItem(AgentInfo agent, GradeFee rule, QrcodePayType payType) {
		QrcodeAgentFee fee = new QrcodeAgentFee();
		fee.setAgentno(agent.getAgentno());
		fee.setBranchno(agent.getBranchno());
		fee.setPayType(payType.ordinal());
		fee.setRate(rule.getRate());
		fee.setScanType(rule.getScanType());
		fee.setSettleType(rule.getSettleType());
		return fee;
	}
}
