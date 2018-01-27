package com.alycloud.account.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IGradeFeeService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.GradeFee;
import com.alycloud.modules.search.GradeFee4Search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商户等级费率
 * @author Moyq5
 * @date 2017年10月29日
 */
@RestController
@RequestMapping("/gradeFee")
@Api(value = "商户等级费率接口")
public class GradeFeeController {
	
	@Autowired
	private IGradeFeeService gradeFeeService;
	
	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月29日
	 */
	@ApiOperation(notes = "调用 /list方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询商户等级费率列表")
	@PostMapping(value = "/list")
	@SystemControllerLog(description = "查询商户等级费率列表")
	public ResultBean<List<GradeFee>> list(@RequestBody RequestBean<GradeFee4Search> reqBody) {
		List<GradeFee> list = gradeFeeService.list(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(list);
	}

	/**
	 * 查询（按渠道分组）
	 * @author Moyq5
	 * @date 2017年11月10日
	 */
	@ApiOperation(notes = "调用 /listGroup4Channel方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询商户等级费率列表")
	@PostMapping(value = "/listGroup4Channel")
	@SystemControllerLog(description = "查询商户等级费率列表")
	public ResultBean<List<Map<String,Object>>> listGroup4Channel(@RequestBody RequestBean<GradeFee4Search> reqBody) {
		GradeFee4Search fee4s = reqBody.getData();
		if (null != fee4s.getPayType()) {
			fee4s.setPayType(1<<fee4s.getPayType());
		}
		if (null != fee4s.getScanType()) {
			fee4s.setScanType(1<<fee4s.getScanType());
		}
		if (null != fee4s.getSettleType()) {
			fee4s.setSettleType(1<<fee4s.getSettleType());
		}
		List<Map<String,Object>> list = gradeFeeService.listGroup4Channel(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(list);
	}
	
	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月29日
	 */
	@ApiOperation(notes = "调用 /queryGradeInfo方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询商户等级费率信息")
	@PostMapping(value = "/queryGradeInfo")
	@SystemControllerLog(description = "查询商户等级费率列表")
	public ResultBean<List<GradeFee>> queryGradeInfo(@RequestBody String merchno) {
		List<GradeFee> list = gradeFeeService.queryGradeInfo(merchno);
		return RestBeanGenerator.genSuccessResult(list);
	}
}
