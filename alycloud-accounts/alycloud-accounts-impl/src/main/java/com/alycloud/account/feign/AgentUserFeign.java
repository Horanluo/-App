package com.alycloud.account.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.AgentUser;

/**
 * 代理商操作员
 * @author Moyq5
 * @date 2017年10月27日
 */
@FeignClient(name = "alycloud-accounts")
public interface AgentUserFeign {
	
	@RequestMapping("/agentUser/add")
	public ResultBean<AgentUser> add(RequestBean<AgentUser> userData);
	
}
