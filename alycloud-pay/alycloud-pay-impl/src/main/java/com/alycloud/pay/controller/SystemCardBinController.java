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
import com.alycloud.modules.entity.SystemCardBin;
import com.alycloud.pay.mapper.SystemCardBinMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 卡BIN
 * @author Moyq5
 * @date 2017年10月21日
 */
@RestController
@RequestMapping("/systemCardBin")
@Api(value = "卡BIN接口")
public class SystemCardBinController {
	
	@Autowired
	private SystemCardBinMapper systemCardBinMapper;
	
	/**
	 * 获取卡BIN信息
	 * @author Moyq5
	 * @date 2017年10月21日
	 */
	@ApiOperation(notes = "调用 /getByCardno方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取卡BIN信息")
	@PostMapping(value = "/getByCardno")
	@SystemControllerLog(description = "获取卡BIN信息")
	public ResultBean<SystemCardBin> getByCardno(@RequestBody RequestBean<String> reqBody) {
		SystemCardBin bin = systemCardBinMapper.getByCardno(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(bin);
	}

}
