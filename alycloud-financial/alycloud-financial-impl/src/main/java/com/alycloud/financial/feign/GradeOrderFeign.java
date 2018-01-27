package com.alycloud.financial.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.entity.GradeOrder;
import com.alycloud.modules.search.GradeOrder4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年11月5日
 */
@FeignClient(name = "alycloud-pay")
public interface GradeOrderFeign {
	
	@RequestMapping("/gradeOrder/getById")
	public ResultBean<GradeOrder> getById(RequestBean<Integer> reqData);
	
	@RequestMapping("/gradeOrder/listByPage")
	public ResultBean<List<GradeOrder>> listByPage(RequestBean<GradeOrder4Search> reqBody);
	
}
