package com.alycloud.pay.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.ChannelMerchYufu;
import com.alycloud.modules.search.ChannelMerchYufu4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月18日
 */
@FeignClient(name = "alycloud-pay")
public interface ChannelMerchYufuFeign {
	
	@RequestMapping("/channelMerchYufu/listByPage")
	public ResultBean<List<ChannelMerchYufu>> listByPage(RequestBean<ChannelMerchYufu4Search> yufuFeignData);

}
