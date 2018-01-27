package com.alycloud.financial.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.AgentVirtualTrans;

/**
 * 
 * @author Moyq5
 * @date 2017年10月17日
 */
@FeignClient(name = "alycloud-financial")
public interface AgentVirtualTransFeign {
	
	@RequestMapping("/agentVirtualTrans/genRefno")
	public ResultBean<String> genRefno();

	@RequestMapping("/agentVirtualTrans/add")
	public ResultBean<AgentVirtualTrans> add(RequestBean<AgentVirtualTrans> reqData);

}
