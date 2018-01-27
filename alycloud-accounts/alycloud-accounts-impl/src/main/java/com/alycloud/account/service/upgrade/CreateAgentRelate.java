package com.alycloud.account.service.upgrade;

import java.util.ArrayList;
import java.util.List;

import org.mortbay.log.Log;

import com.alycloud.account.feign.AgentInfoFeign;
import com.alycloud.account.feign.AgentRelateFeign;
import com.alycloud.account.feign.MerchInfoFeign;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentRelate;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.search.AgentInfo4Search;
import com.alycloud.modules.search.AgentRelate4Search;

/**
 * 创建代理商层级关系
 * @author Moyq5
 * @date 2017年10月27日
 */
public class CreateAgentRelate extends Decorator {

	private MerchInfoFeign merchInfoFeign;
	private AgentInfoFeign agentInfoFeign;
	private AgentRelateFeign agentRelateFeign;
	
	public CreateAgentRelate(Upgrader userUpgrader) {
		super(userUpgrader);
		merchInfoFeign = SpringContextUtils.getBean(MerchInfoFeign.class);
		agentInfoFeign = SpringContextUtils.getBean(AgentInfoFeign.class);
		agentRelateFeign = SpringContextUtils.getBean(AgentRelateFeign.class);
	}

	@Override
	public MerchUser upgrade() throws Exception {
		MerchUser user = super.upgrade();
		createAgentRelate(user);
		return user;
	}
	
	private void createAgentRelate(MerchUser merchUser) throws Exception {
		RequestBean<Integer> merchFeignData = new RequestBean<Integer>();
		merchFeignData.setData(merchUser.getMerchId());
		MerchInfo merch = merchInfoFeign.getById(merchFeignData).getData();
		
		RequestBean<String> agentFeignData = new RequestBean<String>();
		agentFeignData.setData(merch.getSuperAgent());
		AgentInfo superAgent = agentInfoFeign.getByAgentno(agentFeignData).getData();
		
		AgentInfo4Search agent4s = new AgentInfo4Search();
		agent4s.setMobile(merch.getMobile());
		RequestBean<AgentInfo4Search> agentFeignData4s = new RequestBean<AgentInfo4Search>();
		agentFeignData4s.setData(agent4s);
		List<AgentInfo> agentList = agentInfoFeign.listByPage(agentFeignData4s).getData();
		if (agentList.size() != 1) {
			Log.debug("代理商[{}]信息有误: size->{}", merch.getMobile(), agentList.size());
			throw new Exception("商户信息有误");
		}
		AgentInfo newAgent = agentList.get(0);
		
		AgentRelate4Search agentRelate4s = new AgentRelate4Search();
		agentRelate4s.setAgentno(newAgent.getSuperAgent());
		RequestBean<AgentRelate4Search> relateFeignData = new RequestBean<AgentRelate4Search>();
		relateFeignData.setData(agentRelate4s);
		List<AgentRelate> parentRelateList = agentRelateFeign.listByPage(relateFeignData).getData();
		
		List<AgentRelate> newRelateList = new ArrayList<AgentRelate>();
		for (AgentRelate parentRelate : parentRelateList) {
			if (!parentRelate.getSuperAgentno().equals(newAgent.getSuperAgent())) {// -1,上级代理关系中排除上层本身
				AgentRelate newRelate = parentRelate;
				newRelate.setAgentno(newAgent.getAgentno());
				newRelateList.add(newRelate);
			}
		}
		AgentRelate selfRelate = new AgentRelate();
		selfRelate.setAgentno(newAgent.getAgentno());
		selfRelate.setSuperAgentno(null==superAgent?merch.getSuperAgent():superAgent.getAgentno());
		selfRelate.setSuperAgentName(null==superAgent?"易商云电子商务有限公司":superAgent.getAgentName());
		selfRelate.setAgentLevel(parentRelateList.size());// +1
		newRelateList.add(selfRelate);
		AgentRelate agentRelate = new AgentRelate();
		agentRelate.setAgentno(newAgent.getAgentno());
		agentRelate.setSuperAgentno(newAgent.getAgentno());
		agentRelate.setSuperAgentName(newAgent.getAgentName());
		agentRelate.setAgentLevel(0);
		newRelateList.add(agentRelate);
		
		RequestBean<List<AgentRelate>> relatesFeignData = new RequestBean<List<AgentRelate>>();
		relatesFeignData.setData(newRelateList);
		agentRelateFeign.batchAdd(relatesFeignData);
	}

}
