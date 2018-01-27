package com.alycloud.pay.controller;

import java.math.BigDecimal;
import java.util.List;

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
import com.alycloud.modules.entity.QrcodeAgentFee;
import com.alycloud.modules.search.QrcodeAgentFee4Search;
import com.alycloud.pay.api.IQrcodeAgentFeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 代理商二维码费率
 * @author Moyq5
 * @date 2017年10月29日
 */
@RestController
@RequestMapping("/qrcodeAgentFee")
@Api(value = "代理商二维码费率接口")
public class QrcodeAgentFeeController {
	
	@Autowired
	private IQrcodeAgentFeeService qrcodeAgentFeeService;

	/**
	 * 添加代理商二维码费率
	 * @author Moyq5
	 * @date 2017年10月29日
	 */
	@ApiOperation(notes = "调用 /batchAdd方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "添加代理商二维码费率")
	@PostMapping(value = "/batchAdd")
	@SystemControllerLog(description = "添加代理商二维码费率")
	public ResultBean<?> batchAdd(@RequestBody RequestBean<List<QrcodeAgentFee>> reqBody) {
		qrcodeAgentFeeService.batchAdd(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}

	/**
	 * 删除代理商二维码费率
	 * @author Moyq5
	 * @date 2017年10月29日
	 */
	@ApiOperation(notes = "调用 /delByAgentno方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "删除代理商二维码费率")
	@PostMapping(value = "/delByAgentno")
	@SystemControllerLog(description = "删除代理商二维码费率")
	public ResultBean<?> delByAgentno(@RequestBody RequestBean<String> reqBody) {
		qrcodeAgentFeeService.delByAgentno(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}

	/**
	 * 获取代理商二维码费率
	 * @author Moyq5
	 * @date 2017年11月7日
	 */
	@ApiOperation(notes = "调用 /getRate方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取代理商二维码费率")
	@PostMapping(value = "/getRate")
	@SystemControllerLog(description = "获取代理商二维码费率")
	public ResultBean<BigDecimal> getRate(@RequestBody RequestBean<QrcodeAgentFee4Search> reqBody) {
		List<QrcodeAgentFee> feeList = qrcodeAgentFeeService.list(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(feeList.get(0).getRate());
	}

	
}
