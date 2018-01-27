package com.alycloud.pay.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.AgentInfo;

/**
 * 
 * @author Moyq5
 * @date 2017年10月17日
 */
@FeignClient(name = "alycloud-accounts")
public interface AgentInfoFeign {
	
	@RequestMapping("/agentInfo/getByAgentno")
	public ResultBean<AgentInfo> getByAgentno(RequestBean<String> requestData);

}
