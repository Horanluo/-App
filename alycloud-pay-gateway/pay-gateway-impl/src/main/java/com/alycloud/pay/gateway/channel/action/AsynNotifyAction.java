//package com.alycloud.pay.gateway.channel.action;
//
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSON;
//import com.alycloud.pay.gateway.channel.util.PropertyUtils;
//import com.alycloud.pay.gateway.channel.util.SignUtil;
//
///**
// * 说明
// * 1. 以下代码只是为了方便商户接入和测试而提供的样例代码
// * 2. 商户可以根据自己实际的需要，按照技术文档编码完成接入,并非一定要使用本代码，本代码只是提供一个参考
// * 3. 本代码未对必填项参数做校验，商户在接入时需正确填写各必填项参数，适当填写选填项参数
// * 
// * 交易通知接口
// * 
// */
//@Controller
//public class AsynNotifyAction {
//
//	private Logger logger = LoggerFactory.getLogger(getClass());
//	
//	@RequestMapping(value = "/asynNotify.htm")
//	@ResponseBody
//	public String asynNotify(@RequestBody String notifyContent) {
//		logger.info("通知内容:{}" , notifyContent);
//		//解析返回串
//		Map<String, String> returnMap = (Map<String, String>)JSON.parse(notifyContent);
//		String returnCode = returnMap.get("returnCode");
//		if(!returnCode.equals("0")){
//			return "FAIL";
//		}else{
//			//验签
//			String key = PropertyUtils.getProperty("key");
//			if(SignUtil.validSign(returnMap, key)){
//				String status = returnMap.get("status");
//				logger.info("订单状态:{}" , status);
//				return "SUCCESS";
//			}else {
//				logger.info("验签失败");
//				return "FAIL";
//			}
//		}
//	}
//
//}
