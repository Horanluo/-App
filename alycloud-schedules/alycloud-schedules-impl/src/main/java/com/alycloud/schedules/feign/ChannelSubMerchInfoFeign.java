package com.alycloud.schedules.feign;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.ChannelSubmerchInfo;

/**
 * 
 * @author Horanluo
 * @date 2017年10月23日
 */
@FeignClient(name = "alycloud-accounts")
public interface ChannelSubMerchInfoFeign {
	
	@RequestMapping("/channelSubMerchInfo/list")
	public ResultBean<List<ChannelSubmerchInfo>> list(ChannelSubmerchInfo csmi);
	
	@RequestMapping("/channelSubMerchInfo/updateRecord")
	public Integer updateRecord(ChannelSubmerchInfo csmi);
}
