package com.alycloud.financial.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.BranchInfo;

/**
 * 
 * @author Moyq5
 * @date 2017年10月17日
 */
@FeignClient(name = "alycloud-accounts")
public interface BranchInfoFeign {
	
	@RequestMapping("/branchInfo/getByBranchno")
	public ResultBean<BranchInfo> getByBranchno(RequestBean<String> requestData);

}
