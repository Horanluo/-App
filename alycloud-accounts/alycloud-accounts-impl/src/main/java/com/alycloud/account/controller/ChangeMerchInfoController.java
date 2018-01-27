package com.alycloud.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IChangeMerchInfoService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.ChangeMerchInfo;
import com.alycloud.modules.entity.MerchInfo;

import io.swagger.annotations.ApiOperation;

/**
 * 获取商户变更信息
 * @author Horanluo
 * @date 2017年11月27日
 */
@RequestMapping(value="/channelMerchInfo")
@RestController
public class ChangeMerchInfoController {

	@Autowired
	private IChangeMerchInfoService changeMerchInfoService;
	
	/**
	 * 获取商户变更信息
	 * @author Horanluo
	 * @date 2017年11月27日
	 */
	@ApiOperation(notes = "调用 /getInfo方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取商户变更信息")
	@PostMapping(value = "/getInfo")
	@SystemControllerLog(description = "获取商户变更信息")
	public ResultBean<ChangeMerchInfo> getInfo(@RequestBody MerchInfo merchInfo) throws Exception{
		return RestBeanGenerator.genSuccessResult(changeMerchInfoService.getChangeMerchInfo(merchInfo.getMerchno()));
	}
	
	/**
	 * 更新商户变更信息
	 * @author Horanluo
	 * @date 2017年11月28日
	 */
	@ApiOperation(notes = "调用 /getupdateInfo方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "更新商户变更信息")
	@PostMapping(value = "/update")
	@SystemControllerLog(description = "更新商户变更信息")
	public ResultBean<Integer> update(@RequestBody ChangeMerchInfo cmi) throws Exception{
		return RestBeanGenerator.genSuccessResult(changeMerchInfoService.mod(cmi));
	}
}
