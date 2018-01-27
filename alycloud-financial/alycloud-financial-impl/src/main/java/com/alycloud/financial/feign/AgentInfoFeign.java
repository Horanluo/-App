package com.alycloud.financial.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping("/agentInfo/listByPage")
	public ResultBean<List<AgentInfo>> listByPage(@RequestBody RequestBean<AgentInfo4Search> reqBody);
	
	@RequestMapping("/agentInfo/getByAgentno")
	public ResultBean<AgentInfo> getByAgentno(RequestBean<String> agentnoData);
}
