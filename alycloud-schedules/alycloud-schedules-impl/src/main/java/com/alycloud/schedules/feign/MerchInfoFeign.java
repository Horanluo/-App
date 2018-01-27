package com.alycloud.schedules.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.MerchInfo;

/**
 * 
 * @author Horanluo
 * @date 2017年11月06日
 */
@FeignClient(name = "alycloud-accounts")
public interface MerchInfoFeign {
	
	@RequestMapping("/merchInfo/getByMerchno")
	public ResultBean<MerchInfo> getByMerchno(RequestBean<String> feignData);
	@RequestMapping("/merchInfo/getByMerchInfo")
	public ResultBean<List<MerchInfo>> getByMerchInfo(RequestBean<MerchInfo> feignData);
	@RequestMapping("/merchInfo/mod")
	public ResultBean<?> mod(@RequestBody RequestBean<MerchInfo> reqData);
}
