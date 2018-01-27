package com.alycloud.schedules.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.ChangeMerchInfo;
import com.alycloud.modules.entity.MerchInfo;

/**
 * 
 * @author Horanluo
 * @date 2017年10月23日
 */
@FeignClient(name = "alycloud-accounts")
public interface ChangeMerchInfoFeign {
	
	@RequestMapping("/channelMerchInfo/getInfo")
	public ResultBean<ChangeMerchInfo> list(MerchInfo merchInfo);
	@RequestMapping("/channelMerchInfo/update")
	public ResultBean<Integer> update(ChangeMerchInfo cmi);
}
