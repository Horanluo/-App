package com.alycloud.account.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.SystemParam;

/**
 * 
 * @author Moyq5
 * @date 2017年10月30日
 */
@FeignClient(name = "alycloud-pay")
public interface SystemParamFeign {
	
	@RequestMapping("/systemParam/getByCode")
	public ResultBean<SystemParam> getByCode(RequestBean<String> reqBody);

}
