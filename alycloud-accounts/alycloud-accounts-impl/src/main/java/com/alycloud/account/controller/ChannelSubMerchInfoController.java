package com.alycloud.account.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.account.api.IChannelSubMerchInfoService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="channelSubMerchInfo")
public class ChannelSubMerchInfoController {

	@Autowired
	private IChannelSubMerchInfoService channelMerchInfoService;
	
	@ApiOperation(notes = "调用 /list方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询渠道信息")
	@PostMapping(value="/list")
	public ResultBean<List<ChannelSubmerchInfo>> list(@RequestBody ChannelSubmerchInfo csmi) throws Exception{
        
		List<ChannelSubmerchInfo> channelMerchInfoList = channelMerchInfoService.getChannelMerchInfoList(csmi);
		return RestBeanGenerator.genSuccessResult(channelMerchInfoList);
	}
	
	@ApiOperation(notes = "调用 /updateRecord方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "更新渠道信息")
	@PostMapping(value="/updateRecord")
	@SystemControllerLog(description = "更新渠道信息")
	public Integer updateRecord(@RequestBody ChannelSubmerchInfo csmi) throws Exception{
		return channelMerchInfoService.updateRecord(csmi);
	}
}
