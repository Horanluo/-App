package com.alycloud.account.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.AgentVirtualCard;

/**
 * 代理商虚拟账户
 * @author Moyq5
 * @date 2017年11月6日
 */
@FeignClient(name = "alycloud-financial")
public interface AgentVirtualCardFeign {
	
	@RequestMapping("/agentVirtualCard/add4Agent")
	public ResultBean<AgentVirtualCard> add4Agent(RequestBean<String> reqData);
	
}
