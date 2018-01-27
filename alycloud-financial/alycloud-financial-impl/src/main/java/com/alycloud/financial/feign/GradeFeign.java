package com.alycloud.financial.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.Grade;

/**
 * 
 * @author Moyq5
 * @date 2017年10月31日
 */
@FeignClient(name = "alycloud-accounts")
public interface GradeFeign {
	
	@RequestMapping("/grade/getByGradeType")
	public ResultBean<Grade> getByGradeType(RequestBean<Integer> reqBody);

}
