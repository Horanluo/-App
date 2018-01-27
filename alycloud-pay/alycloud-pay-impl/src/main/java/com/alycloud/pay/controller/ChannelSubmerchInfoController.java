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
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.search.ChannelSubmerchInfo4Search;
import com.alycloud.pay.mapper.ChannelSubmerchInfoMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 渠道商户信息(新)
 * @author Moyq5
 * @date 2017年10月21日
 */
@RestController
@RequestMapping("/channelSubmerchInfo")
@Api(value = "渠道商户信息接口")
public class ChannelSubmerchInfoController {
	
	@Autowired
	private ChannelSubmerchInfoMapper channelSubmerchInfoMapper;
	
	/**
	 * 获取渠道商户信息
	 * @author Moyq5
	 * @date 2017年10月21日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取渠道商户列表信息")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "获取渠道商户列表信息")
	public ResultBean<List<ChannelSubmerchInfo>> listByPage(@RequestBody RequestBean<ChannelSubmerchInfo4Search> reqBody) {
		List<ChannelSubmerchInfo> channelList = channelSubmerchInfoMapper.listByPage(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(channelList);
	}

}
