package com.alycloud.pay.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;

/**
 * 
 * @author Moyq5
 * @date 2017年11月9日
 */
@FeignClient(name = "alycloud-financial")
public interface QrcodeTransFeign {
	
	@RequestMapping("/qrcodeTrans/addByOrderno")
	public ResultBean<?> addByOrderno(RequestBean<String> reqBody);
}
