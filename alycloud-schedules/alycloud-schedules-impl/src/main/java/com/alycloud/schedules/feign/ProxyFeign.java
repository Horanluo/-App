package com.alycloud.schedules.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.alycloud.modules.entity.AgentVirtualTrans;
import com.alycloud.modules.entity.MerchVirtualTrans;

/**
 * 
 * @author Horanluo
 * 代付服务接口
 */
@FeignClient(name = "alycloud-pay")
public interface ProxyFeign {
	
	@RequestMapping("/proxy/agentSingleProxyQuery")
	public JSONObject agentSingleProxyQuery(AgentVirtualTrans agentVirtualTrans);
	
	@RequestMapping("/proxy/merchSingleProxyQuery")
	public JSONObject merchSingleProxyQuery(MerchVirtualTrans merchVirtualTrans);
	
	@RequestMapping("/proxy/merchBatchProxyQuery")
	public JSONObject merchBatchProxyQuery(MerchVirtualTrans merchVirtualTrans);
}
