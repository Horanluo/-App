package com.alycloud.channel.yufu.support.client;

import java.util.Map;
import com.alycloud.channel.yufu.bean.PayData;
import com.alycloud.channel.yufu.bean.PayResult;
import com.alycloud.channel.yufu.support.AbstractClient;
import com.alycloud.channel.yufu.support.Tool;
import com.alycloud.channel.yufu.support.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 商户入驻接口
 * @author Moyq5
 * @date 2017年8月1日
 */
@Slf4j
public class Pay extends AbstractClient<PayData, PayResult> {

	public Pay() {
		super("", PayResult.class);
	}

	@Override
	public PayResult execute(PayData data) {
		PayResult result = new PayResult();
		
		try {
			String jsonString = JSON.toString(data);
			Map<String, String> dataMap = Tool.getSortedFields(jsonString, null, false);
			String sign = Tool.sign(dataMap, true, super.getContext().getConfig().getKey());
			dataMap.put("sign", sign);
			log.info("御付请求参数:"+dataMap);
			String payUrl = super.getContext().getConfig().getPayPath() + "?" + Tool.toQueryString(dataMap, null, false);
			log.debug("支付地址：{}", payUrl);
			result.setPayUrl(payUrl);
			result.setRespCode("0000");
			result.setRespMsg("支付地址获取成功");
			
		} catch (Exception e) {
			log.error("获取支付地址失败", e);
			result.setRespCode("9999");
			result.setRespMsg(e.getMessage());
		} 
		
		return result;
	}

}
