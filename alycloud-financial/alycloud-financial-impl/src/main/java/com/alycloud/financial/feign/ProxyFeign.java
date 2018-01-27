package com.alycloud.financial.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alycloud.modules.entity.AgentWithdraw;

/**
 * 
 * @author Horanluo
 * 代付服务接口
 */
@FeignClient(name = "alycloud-pay")
public interface ProxyFeign {
	
	@RequestMapping("/proxy/singleProxy")
	public String singleProxy(AgentWithdraw agentWithdraw);
}
