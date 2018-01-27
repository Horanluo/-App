//package com.alycloud.pay.gateway.channel.action;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.alibaba.fastjson.JSON;
//import com.alycloud.pay.gateway.channel.util.HttpsClientUtil;
//import com.alycloud.pay.gateway.channel.util.PropertyUtils;
//import com.alycloud.pay.gateway.channel.util.RequestUtils;
//import com.alycloud.pay.gateway.channel.util.SignUtil;
//
///**
// * 说明
// * 1. 以下代码只是为了方便商户接入和测试而提供的样例代码
// * 2. 商户可以根据自己实际的需要，按照技术文档编码完成接入,并非一定要使用本代码，本代码只是提供一个参考
// * 3. 本代码未对必填项参数做校验，商户在接入时需正确填写各必填项参数，适当填写选填项参数
// * 
// * 交易详细查询接口
// * 
// */
//@Controller
//public class QueryOrderAction {
//
//	@RequestMapping(value = "/queryIndex.htm")
//	public String queryIndex(HttpServletRequest httpServletRequest) {
//		String mchId = PropertyUtils.getProperty("mchId");
//		httpServletRequest.setAttribute("mchId", mchId);
//		return "queryIndex";
//	}
//
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/queryOrder.htm", method = RequestMethod.POST)
//	public String queryOrder(HttpServletRequest httpServletRequest) {
//		//生成签名的key
//		String key = PropertyUtils.getProperty("key");
//		
//		//请求路径
//		String requestUrl = PropertyUtils.getProperty("requestUrl");
//		
//		Map<String, String> map = RequestUtils.getRequestMapValue(httpServletRequest.getParameterMap());
//		
//		//过滤空值或null
//		Map<String, String> filterMap = SignUtil.paraFilter(map);
//		
//		//拼接
//		String toSign = SignUtil.createLinkString(filterMap);
//		
//		//生成签名sign
//		String sign = SignUtil.genSign(key, toSign);
//		filterMap.put("sign", sign);
//
//		//转为json串
//		String postStr = JSON.toJSONString(filterMap);
//		
//		//发送请求
//		String returnStr = HttpsClientUtil.sendRequest(requestUrl,postStr,"application/json");
//		
//		//解析返回串
//		Map<String, String> returnMap = (Map<String, String>)JSON.parse(returnStr);
//		
//		//验签
//		if(SignUtil.validSign(returnMap, key)){
//			String returnCode = returnMap.get("returnCode");
//			String resultCode = returnMap.get("resultCode");
//			if(returnCode.equals("0")&&resultCode.equals("0")){
//				//请根据实际情况操作 更新数据到对应的表，或显示到页面
//				httpServletRequest.setAttribute("returnMap", returnMap);
//			}else{
//				//请根据实际情况操作 更新数据到对应的表，或显示到页面
//				httpServletRequest.setAttribute("returnMap", returnMap);
//			}
//		}else {
//			//do nothing
//		}
//		return "queryResult";
//	}
//
//}
