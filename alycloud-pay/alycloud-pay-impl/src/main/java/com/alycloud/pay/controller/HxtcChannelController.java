package com.alycloud.pay.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.channel.api.IQuickpayService;
import com.alycloud.channel.factory.QuickpayFactory;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.enums.SysChannelType;
import com.alycloud.core.modules.RequestBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 御付渠道
 * @author Horanluo
 * @date 2017年11月13日
 */
@RestController
@RequestMapping("/hxtcChannel")
@Api(value = "汇享天成渠道接口")
public class HxtcChannelController {
	
	/**
	 * 上报汇享天成通道商户费率
	 * @author Horanluo
	 * @date 2017年11月13日
	 */
	@ApiOperation(notes = "调用 /recordMerchFee方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "上报汇享天成通道商户费率")
	@PostMapping(value = "/recordHXTCFee")
	@SystemControllerLog(description = "上报汇享天成通道商户费率")
	public Map<String, Object> recordMerchFee(@RequestBody RequestBean<Map<String, String>> feignData) {
		IQuickpayService quickpayService = QuickpayFactory.getQuickpayService(SysChannelType.HXTC.getCode());
		
		Map<String, String> reqData = feignData.getData();
		Map<String, Object> result = new HashMap<String, Object>();
		if("channel_no_jf".equals(reqData.get("channel_flag"))){
			feignData.getData().remove("channel_flag");
			result = quickpayService.registerChannelSubmerch(feignData.getData());
		}
		
		if("channel_jf".equals(reqData.get("channel_flag"))){
			feignData.getData().remove("channel_flag");
			result = quickpayService.registerChannelJFSubmerch(feignData.getData());
		}
		return result;
	}
}
