package com.alycloud.financial.controller;

import java.util.List;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.alycloud.core.modules.PageRequestBean;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.financial.feign.AgentInfoFeign;
import com.alycloud.financial.feign.MerchInfoFeign;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.search.AgentInfo4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月24日
 */
public abstract class AbstractAgentController {

	@Autowired
	private MerchInfoFeign merchInfoFeign;
	@Autowired
	private AgentInfoFeign agentInfoFeign;
	
	protected AgentInfo getAgent(PageRequestBean<?> reqBody) throws Exception {
		final String merchno = reqBody.getMerchno();
		RequestBean<String> merchFeignData = new RequestBean<String>();
		merchFeignData.setData(merchno);
		MerchInfo merch = merchInfoFeign.getByMerchno(merchFeignData).getData();
		String mobile = merch.getMobile();
		
		AgentInfo4Search agent4s = new AgentInfo4Search();
		agent4s.setMobile(merch.getMobile());
		RequestBean<AgentInfo4Search> agentFeignData = new RequestBean<AgentInfo4Search>();
		agentFeignData.setData(agent4s);
		List<AgentInfo> agentList = agentInfoFeign.listByPage(agentFeignData).getData();
		if(agentList == null || agentList.isEmpty() || agentList.size() == 0)
		{
		    return null;
		}
		if (agentList.size() != 1) {
			Log.debug("代理商[{}]信息有误: size->{}", mobile, agentList.size());
			throw new Exception("商户信息有误");
		}
		return agentList.get(0);
	}
	
	protected AgentInfo getAgent(RequestBean<?> reqBody) throws Exception {
		final String merchno = reqBody.getMerchno();
		RequestBean<String> merchFeignData = new RequestBean<String>();
		merchFeignData.setData(merchno);
		MerchInfo merch = merchInfoFeign.getByMerchno(merchFeignData).getData();
		String mobile = merch.getMobile();
		
		AgentInfo4Search agent4s = new AgentInfo4Search();
		agent4s.setMobile(merch.getMobile());
		RequestBean<AgentInfo4Search> agentFeignData = new RequestBean<AgentInfo4Search>();
		agentFeignData.setData(agent4s);
		List<AgentInfo> agentList = agentInfoFeign.listByPage(agentFeignData).getData();
		if(agentList == null || agentList.isEmpty() || agentList.size() == 0)
        {
            return null;
        }
		if (agentList.size() != 1) {
			Log.debug("代理商[{}]信息有误: size->{}", mobile, agentList.size());
			throw new Exception("商户信息有误");
		}
		return agentList.get(0);
	}
}
