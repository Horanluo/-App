package com.alycloud.account.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.MerchInfo;

/**
 * 
 * @author Moyq5
 * @date 2017年10月17日
 */
@FeignClient(name = "alycloud-accounts")
public interface MerchInfoFeign {
	
	@RequestMapping("/merchInfo/mod")
	public ResultBean<Object> mod(RequestBean<MerchInfo> reqData);
	
	@RequestMapping("/merchInfo/getByMerchno")
	public ResultBean<MerchInfo> getByMerchno(RequestBean<String> feignData);

	@RequestMapping("/merchInfo/getById")
	public RequestBean<MerchInfo> getById(RequestBean<Integer> feignData);

}
