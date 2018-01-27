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
import com.alycloud.modules.entity.QrcodeChannelFee;
import com.alycloud.modules.search.QrcodeChannelFee4Search;
import com.alycloud.pay.mapper.QrcodeChannelFeeMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 渠道二维码费率
 * @author Moyq5
 * @date 2017年10月18日
 */
@RestController
@RequestMapping("/qrcodeChannelFee")
@Api(value = "渠道二维码费率接口")
public class QrcodeChannelFeeController {
	
	@Autowired
	private QrcodeChannelFeeMapper qrcodeChannelFeeMapper;

	/**
	 * 获取渠道二维码费率
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /getRate方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取渠道二维码费率")
	@PostMapping(value = "/getRate")
	@SystemControllerLog(description = "获取机构二维码费率")
	public ResultBean<BigDecimal> getRate(@RequestBody RequestBean<QrcodeChannelFee4Search> reqBody) {
		List<QrcodeChannelFee> feeList = qrcodeChannelFeeMapper.list(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(feeList.get(0).getRate());
	}

}
