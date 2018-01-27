package com.alycloud.account.service.upgrade;

import java.util.List;

import org.mortbay.log.Log;

import com.alycloud.account.feign.AgentInfoFeign;
import com.alycloud.account.feign.AgentVirtualCardFeign;
import com.alycloud.account.feign.MerchInfoFeign;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentVirtualCard;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.search.AgentInfo4Search;

/**
 * 创建代理商虚拟账户
 * @author Moyq5
 * @date 2017年11月5日
 */
public class CreateAgentVirtualCard extends Decorator {

	private MerchInfoFeign merchInfoFeign;
	private AgentInfoFeign agentInfoFeign;
	private AgentVirtualCardFeign agentVirtualCardFeign;
	
	public CreateAgentVirtualCard(Upgrader userUpgrader) {
		super(userUpgrader);
		merchInfoFeign = SpringContextUtils.getBean(MerchInfoFeign.class);
		agentInfoFeign = SpringContextUtils.getBean(AgentInfoFeign.class);
		agentVirtualCardFeign = SpringContextUtils.getBean(AgentVirtualCardFeign.class);
	}

	@Override
	public MerchUser upgrade() throws Exception {
		MerchUser user = super.upgrade();
		createAgentVirtualCard(user);
		return user;
	}
	
	private AgentVirtualCard createAgentVirtualCard(MerchUser merchUser) throws Exception {
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
		
		RequestBean<String> agentnoData = new RequestBean<String>();
		agentnoData.setData(newAgent.getAgentno());
		return agentVirtualCardFeign.add4Agent(agentnoData).getData();
	}

}
