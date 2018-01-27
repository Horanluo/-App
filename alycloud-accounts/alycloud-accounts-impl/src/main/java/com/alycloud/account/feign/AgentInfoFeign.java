package com.alycloud.account.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.search.AgentInfo4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月23日
 */
@FeignClient(name = "alycloud-accounts")
public interface AgentInfoFeign {
	
	@RequestMapping("/agentInfo/mod")
	public ResultBean<Object> mod(RequestBean<AgentInfo> reqData);
	
	@RequestMapping("/agentInfo/getMaxAgentnoByAgentno")
	public ResultBean<String> getMaxAgentnoByAgentno(RequestBean<String> agentnoData);

	@RequestMapping("/agentInfo/add")
	public ResultBean<AgentInfo> add(RequestBean<AgentInfo> agentAddFeignData);
	
	@RequestMapping("/agentInfo/listByPage")
	public ResultBean<List<AgentInfo>> listByPage(RequestBean<AgentInfo4Search> reqBody);

	@RequestMapping("/agentInfo/getByAgentno")
	public ResultBean<AgentInfo> getByAgentno(RequestBean<String> agentnoData);

	
}
