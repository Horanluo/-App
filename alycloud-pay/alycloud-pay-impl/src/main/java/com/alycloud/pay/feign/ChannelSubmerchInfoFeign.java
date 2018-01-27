package com.alycloud.pay.feign;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.search.ChannelSubmerchInfo4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月21日
 */
@FeignClient(name = "alycloud-pay")
public interface ChannelSubmerchInfoFeign {
	
	@RequestMapping("/channelSubmerchInfo/listByPage")
	public ResultBean<List<ChannelSubmerchInfo>> listByPage(RequestBean<ChannelSubmerchInfo4Search> feignData);

}
