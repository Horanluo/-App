package com.alycloud.account.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.MerchVirtualCard;

/**
 * 商户虚拟账户
 * @author Moyq5
 * @date 2017年11月13日
 */
@FeignClient(name = "alycloud-financial")
public interface MerchVirtualCardFeign {
	
	@RequestMapping("/merchVirtualCard/add4Merch")
	public ResultBean<MerchVirtualCard> add4Merch(RequestBean<String> reqData);
	
}
