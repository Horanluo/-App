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
import com.alycloud.modules.entity.QrcodeBranchFee;
import com.alycloud.modules.search.QrcodeBranchFee4Search;
import com.alycloud.pay.mapper.QrcodeBranchFeeMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 机构二维码费率
 * @author Moyq5
 * @date 2017年10月18日
 */
@RestController
@RequestMapping("/qrcodeBranchFee")
@Api(value = "机构二维码费率接口")
public class QrcodeBranchFeeController {
	
	@Autowired
	private QrcodeBranchFeeMapper qrcodeBranchFeeMapper;

	/**
	 * 获取机构二维码费率
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /getRate方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取机构二维码费率")
	@PostMapping(value = "/getRate")
	@SystemControllerLog(description = "获取机构二维码费率")
	public ResultBean<BigDecimal> getRate(@RequestBody RequestBean<QrcodeBranchFee4Search> reqBody) {
		List<QrcodeBranchFee> feeList = qrcodeBranchFeeMapper.list(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(feeList.get(0).getRate());
	}

}
