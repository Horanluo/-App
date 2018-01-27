package com.alycloud.financial.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.AgentVirtualCard;
import com.alycloud.modules.search.AgentTrans4Search;

@FeignClient(name = "alycloud-financial")
public interface AgentVirtualCardFeign {

	@RequestMapping("/agentVirtualCard/getByAgentno")
	public ResultBean<AgentVirtualCard> getByAgentno(RequestBean<String> reqData);
	
	@RequestMapping("/agentVirtualCard/rechargeByAgentTrans")
	public ResultBean<Object> rechargeByAgentTrans(RequestBean<AgentTrans4Search> reqData);
}
