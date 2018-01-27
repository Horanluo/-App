package com.alycloud.schedules.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.modules.entity.AgentVirtualTrans;

@FeignClient(name = "alycloud-financial")
public interface AgentVirtualTransFeign {

	@RequestMapping("/agentVirtualTrans/getAgentVirtualTrans")
	public List<AgentVirtualTrans> getAgentVirtualTrans(AgentVirtualTrans agentVirtualTrans);
	
	@RequestMapping("/agentVirtualTrans/modifyRecord")
	public Integer modifyAgentVirtualTrans(AgentVirtualTrans agentVirtualTrans);
}
