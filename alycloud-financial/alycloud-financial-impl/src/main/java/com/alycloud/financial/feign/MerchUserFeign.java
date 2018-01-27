package com.alycloud.financial.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.search.MerchUser4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月30日
 */
@FeignClient(name = "alycloud-accounts")
public interface MerchUserFeign {
	
	@RequestMapping("/user/listByPage")
	public ResultBean<List<MerchUser>> listByPage(RequestBean<MerchUser4Search> reqBody);
	
}
