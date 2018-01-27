package com.alycloud.account.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.AgentRoler;
import com.alycloud.modules.search.AgentRoler4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月27日
 */
@FeignClient(name = "alycloud-accounts")
public interface AgentRolerFeign {
	
	@RequestMapping("/agentRoler/listByPage")
	public ResultBean<List<AgentRoler>> listByPage(@RequestBody RequestBean<AgentRoler4Search> reqBody);

}
