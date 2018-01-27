package com.alycloud.schedules.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;

/**
 * 
 * @author Moyq5
 * @date 2017年11月12日
 */
@FeignClient(name = "alycloud-pay")
public interface FastOrderFeign {
	
	@RequestMapping("/fastOrder/checkPayStatus")
	public ResultBean<?> checkPayStatus(RequestBean<String> feignData);
}
