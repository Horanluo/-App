package com.alycloud.pay.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.FastOrder;

/**
 * 
 * @author Moyq5
 * @date 2017年10月19日
 */
@FeignClient(name = "alycloud-pay")
public interface FastOrderFeign {
	
	@RequestMapping("/fastOrder/add")
	public ResultBean<FastOrder> add(RequestBean<FastOrder> requestData);

}
