//package com.alycloud.pay.gateway.channel.action;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
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
// * 收款二维码生成接口
// * 
// */
//@Controller
//public class GenQRcodeAction {
//
//	@RequestMapping(value = "/codeIndex.htm")
//	public String queryIndex(HttpServletRequest httpServletRequest) {
//		String mchId = PropertyUtils.getProperty("mchId");
//		httpServletRequest.setAttribute("mchId", mchId);
//		String subMchId = PropertyUtils.getProperty("subMchId");
//		httpServletRequest.setAttribute("subMchId", subMchId);
//		return "codeStart";
//	}
//	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/genQRcode.htm",produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public String generateQRcode(HttpServletRequest httpServletRequest) {
//		String retString = "";
//		
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
//				//请在微信客户端打开该url
//				retString = returnMap.get("codeUrl");
//			}else{
//				retString = returnMap.get("returnMsg") + returnMap.get("errCode") + returnMap.get("errCodeDes");
//			}
//		}else {
//			//do nothing
//		}
//		
//		return "二维码生成成功，请在微信客户端打开链接：  " + retString;
//	}
//	
//}
