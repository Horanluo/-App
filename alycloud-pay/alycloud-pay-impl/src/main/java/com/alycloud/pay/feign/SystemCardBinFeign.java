package com.alycloud.pay.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.SystemCardBin;

/**
 * 
 * @author Moyq5
 * @date 2017年10月17日
 */
@FeignClient(name = "alycloud-pay")
public interface SystemCardBinFeign {
	
	@RequestMapping("/systemCardBin/getByCardno")
	public ResultBean<SystemCardBin> getByCardno(RequestBean<String> reqBody);

}
