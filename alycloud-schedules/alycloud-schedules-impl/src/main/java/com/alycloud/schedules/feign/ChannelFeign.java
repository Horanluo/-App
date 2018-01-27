package com.alycloud.schedules.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alycloud.channel.yufu.merch.bean.MerchQueryData;
import com.alycloud.channel.yufu.merch.bean.MerchQueryResult;

/**
 * 
 * @author Moyq5
 * @date 2017年10月18日
 */
@FeignClient(name = "alycloud-pay")
public interface ChannelFeign {
	
	@RequestMapping("/yufuChannel/queryAuditResult")
	public MerchQueryResult queryAuditResult(MerchQueryData data);
}
