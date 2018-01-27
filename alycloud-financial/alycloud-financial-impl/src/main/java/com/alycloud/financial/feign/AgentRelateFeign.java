package com.alycloud.financial.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.AgentRelate;
import com.alycloud.modules.search.AgentRelate4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月27日
 */
@FeignClient(name = "alycloud-accounts")
public interface AgentRelateFeign {
	
	@RequestMapping("/agentRelate/listByPage")
	public ResultBean<List<AgentRelate>> listByPage(@RequestBody RequestBean<AgentRelate4Search> reqBody);

}
