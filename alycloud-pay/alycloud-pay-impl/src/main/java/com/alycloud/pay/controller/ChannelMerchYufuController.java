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
import com.alycloud.modules.entity.ChannelMerchYufu;
import com.alycloud.modules.search.ChannelMerchYufu4Search;
import com.alycloud.pay.mapper.ChannelMerchYufuMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 御付渠道商户
 * @author Moyq5
 * @date 2017年10月18日
 */
@RestController
@RequestMapping("/channelMerchYufu")
@Api(value = "御付渠道商户接口")
public class ChannelMerchYufuController {
	
	@Autowired
	private ChannelMerchYufuMapper channelMerchYufuMapper;

	/**
	 * 获取御付渠道商户信息
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取御付渠道商户信息")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "获取御付渠道商户信息")
	public ResultBean<List<ChannelMerchYufu>> listByPage(@RequestBody RequestBean<ChannelMerchYufu4Search> reqBody) {
		List<ChannelMerchYufu> yufuList = channelMerchYufuMapper.listByPage(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(yufuList);
	}

}
