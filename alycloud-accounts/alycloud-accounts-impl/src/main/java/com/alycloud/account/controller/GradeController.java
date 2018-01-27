package com.alycloud.account.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IGradeService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.Grade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商户等级配置
 * @author Moyq5
 * @date 2017年10月31日
 */
@RestController
@RequestMapping("/grade")
@Api(value = "商户等级配置接口")
public class GradeController {
	
	@Autowired
	private IGradeService gradeService;
	
	/**
	 * 获取最接近指定金额的等级信息
	 * @author Moyq5
	 * @date 2017年10月31日
	 */
	@ApiOperation(notes = "调用 /getMaxByLessThanAmount方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取最接近指定金额的等级信息")
	@PostMapping(value = "/getMaxByLessThanAmount")
	@SystemControllerLog(description = "获取最接近指定金额的等级信息")
	public ResultBean<Grade> getMaxByLessThanAmount(@RequestBody RequestBean<BigDecimal> reqBody) {
		Grade grade = gradeService.getMaxByLessThan(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(grade);
	}

	/**
	 * 获取最接近指定推荐人数的等级信息
	 * @author Moyq5
	 * @date 2017年10月31日
	 */
	@ApiOperation(notes = "调用 /getMaxByLessThanQuantity方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取最接近指定推荐人数的等级信息")
	@PostMapping(value = "/getMaxByLessThanQuantity")
	@SystemControllerLog(description = "获取最接近指定推荐人数的等级信息")
	public ResultBean<Grade> getMaxByLessThanQuantity(@RequestBody RequestBean<Integer> reqBody) {
		Grade grade = gradeService.getMaxByLessThan(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(grade);
	}


	/**
	 * 获取等级信息
	 * @author Moyq5
	 * @date 2017年11月4日
	 */
	@ApiOperation(notes = "调用 /getByGradeType方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取等级信息")
	@PostMapping(value = "/getByGradeType")
	@SystemControllerLog(description = "获取等级信息")
	public ResultBean<Grade> getByGradeType(@RequestBody RequestBean<Integer> reqBody) {
		Grade grade = gradeService.getByGradeType(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(grade);
	}

	/**
	 * 获取等级列表
	 * @author Moyq5
	 * @date 2017年11月10日
	 */
	@ApiOperation(notes = "调用 /list方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取等级列表")
	@PostMapping(value = "/list")
	@SystemControllerLog(description = "获取等级列表")
	public ResultBean<List<Grade>> list() {
		List<Grade> list = gradeService.listAll();
		return RestBeanGenerator.genSuccessResult(list);
	}

}
