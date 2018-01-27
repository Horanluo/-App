package com.alycloud.pay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.SystemParam;
import com.alycloud.pay.api.ISystemParamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统参数
 * @author Moyq5
 * @date 2017年10月30日
 */
@RestController
@RequestMapping("/systemParam")
@Api(value = "系统参数接口")
public class SystemParamController {
	
	@Autowired
	private ISystemParamService systemParamService;
	
	/**
	 * 获取参数
	 * @author Moyq5
	 * @date 2017年10月30日
	 */
	@ApiOperation(notes = "调用 /getByCode方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取系统参数")
	@PostMapping(value = "/getByCode")
	@SystemControllerLog(description = "获取系统参数")
	public ResultBean<SystemParam> getByCode(@RequestBody RequestBean<String> reqBody) {
		SystemParam param = systemParamService.getByCode(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(param);
	}

}
