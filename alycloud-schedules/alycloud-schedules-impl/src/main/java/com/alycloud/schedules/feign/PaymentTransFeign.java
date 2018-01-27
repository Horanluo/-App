package com.alycloud.schedules.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.modules.entity.AgentVirtualTrans;
import com.alycloud.modules.entity.MerchVirtualTrans;

@FeignClient(name = "alycloud-financial")
public interface PaymentTransFeign {

	@RequestMapping("/paymentTrans/modifyRecord")
	public Integer modifyRecord(AgentVirtualTrans agentVirtualTrans);
	
	@RequestMapping("/paymentTrans/modifyMerchRecord")
	public Integer modifyMerchRecord(MerchVirtualTrans merchVirtualTrans);
}
