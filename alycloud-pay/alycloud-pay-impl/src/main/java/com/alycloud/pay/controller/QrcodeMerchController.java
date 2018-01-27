package com.alycloud.pay.controller;

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
import com.alycloud.modules.entity.QrcodeMerch;
import com.alycloud.modules.search.QrcodeMerch4Search;
import com.alycloud.modules.search.QrcodeMerch4Search4Pay;
import com.alycloud.pay.api.IQrcodeMerchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 二维码渠道商户
 * @author Moyq5
 * @date 2017年10月18日
 */
@RestController
@RequestMapping("/qrcodeMerch")
@Api(value = "二维码渠道商户接口")
public class QrcodeMerchController {
	
	@Autowired
	private IQrcodeMerchService qrcodeMerchService;

	/**
	 * 获取二维码渠道商户信息
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取二维码渠道商户信息")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "获取二维码渠道商户信息")
	public ResultBean<List<QrcodeMerch>> listByPage(@RequestBody RequestBean<QrcodeMerch4Search> reqBody) {
		List<QrcodeMerch> merchList = qrcodeMerchService.listByPage(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(merchList);
	}

	/**
	 * 获取可交易的二维码渠道商户信息
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /list4Pay方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取可交易的二维码渠道商户信息")
	@PostMapping(value = "/list4Pay")
	@SystemControllerLog(description = "获取可交易的二维码渠道商户信息")
	public ResultBean<List<QrcodeMerch>> list4Pay(@RequestBody RequestBean<QrcodeMerch4Search4Pay> reqBody) {
		List<QrcodeMerch> merchList = qrcodeMerchService.list4Pay(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(merchList);
	}

}
