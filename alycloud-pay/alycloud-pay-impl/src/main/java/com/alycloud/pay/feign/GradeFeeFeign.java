package com.alycloud.pay.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.GradeFee;
import com.alycloud.modules.search.GradeFee4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月17日
 */
@FeignClient(name = "alycloud-accounts")
public interface GradeFeeFeign {
	
	@RequestMapping("/gradeFee/list")
	public ResultBean<List<GradeFee>> list(@RequestBody RequestBean<GradeFee4Search> reqBody);

}
