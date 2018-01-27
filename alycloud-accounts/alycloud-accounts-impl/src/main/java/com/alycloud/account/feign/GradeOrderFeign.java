package com.alycloud.account.feign;

import java.math.BigDecimal;
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
 * @date 2017年10月30日
 */
@FeignClient(name = "alycloud-pay")
public interface GradeOrderFeign {
	
	@RequestMapping("/gradeOrder/add")
	public ResultBean<GradeOrder> add(RequestBean<GradeOrder> reqData);
	
	@RequestMapping("/gradeOrder/mod")
	public ResultBean<Object> mod(RequestBean<GradeOrder> reqData);
	
	@RequestMapping("/gradeOrder/listByPage")
	public ResultBean<List<GradeOrder>> listByPage(RequestBean<GradeOrder4Search> reqBody);
	
	@RequestMapping("/gradeOrder/sumAmount")
	public ResultBean<BigDecimal> sumAmount(RequestBean<GradeOrder4Search> reqBody);
	
	@RequestMapping("/gradeOrder/getById")
	public ResultBean<GradeOrder> getById(RequestBean<Integer> reqBody);
}
