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
import com.alycloud.modules.entity.QrcodeMerchFee;
import com.alycloud.modules.search.QrcodeMerchFee4Search;
import com.alycloud.pay.api.IQrcodeMerchFeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商户二维码费率
 * @author Moyq5
 * @date 2017年10月18日
 */
@RestController
@RequestMapping("/qrcodeMerchFee")
@Api(value = "商户二维码费率接口")
public class QrcodeMerchFeeController {
	
	@Autowired
	private IQrcodeMerchFeeService qrcodeMerchFeeService;

	/**
	 * 获取商户二维码费率
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /getRate方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取商户二维码费率")
	@PostMapping(value = "/getRate")
	@SystemControllerLog(description = "获取商户二维码费率")
	public ResultBean<BigDecimal> getRate(@RequestBody RequestBean<QrcodeMerchFee4Search> reqBody) {
		List<QrcodeMerchFee> feeList = qrcodeMerchFeeService.list(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(feeList.get(0).getRate());
	}


	/**
	 * 添加商户二维码费率
	 * @author Moyq5
	 * @date 2017年10月29日
	 */
	@ApiOperation(notes = "调用 /addList方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "添加商户二维码费率")
	@PostMapping(value = "/addList")
	@SystemControllerLog(description = "添加商户二维码费率")
	public ResultBean<?> addList(@RequestBody RequestBean<List<QrcodeMerchFee>> reqBody) {
		qrcodeMerchFeeService.addList(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}

	/**
	 * 删除商户二维码费率
	 * @author Moyq5
	 * @date 2017年10月29日
	 */
	@ApiOperation(notes = "调用 /delByMerchno方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "删除商户二维码费率")
	@PostMapping(value = "/delByMerchno")
	@SystemControllerLog(description = "删除商户二维码费率")
	public ResultBean<?> delByMerchno(@RequestBody RequestBean<String> reqBody) {
		qrcodeMerchFeeService.delByMerchno(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}

}
