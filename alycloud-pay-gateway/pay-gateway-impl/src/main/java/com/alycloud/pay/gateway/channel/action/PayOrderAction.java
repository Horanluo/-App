//package com.alycloud.pay.gateway.channel.action;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.alibaba.fastjson.JSON;
//import com.alycloud.pay.gateway.channel.util.HttpClientUtil;
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
// * 下单接口
// * 
// */
//@Controller
//public class PayOrderAction {
//
//	@RequestMapping(value = "/payStart.htm")
//	public String queryIndex(HttpServletRequest httpServletRequest) {
//		String mchId = PropertyUtils.getProperty("mchId");
//		httpServletRequest.setAttribute("mchId", mchId);
//		String subMchId = PropertyUtils.getProperty("subMchId");
//		httpServletRequest.setAttribute("subMchId", subMchId);
//		httpServletRequest.setAttribute("outTradeNo", System.currentTimeMillis());
//		return "payStart";
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(value = "/createPay.htm")
//	public String createPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		String key = PropertyUtils.getProperty("key");
//		
//		String requestUrl = PropertyUtils.getProperty("requestUrl");
//		
//		Map paramMap = request.getParameterMap();
//		
//		//获取参数
//		Map<String, String> param = RequestUtils.getRequestMapValue(paramMap);
//		
//		//过滤空值或null
//		Map<String, String> filterMap = SignUtil.paraFilter(param);
//		String channel = filterMap.get("channel");
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
//				//根据不同渠道类型做处理
//				request.setAttribute("channel", channel);
//				if(channel.equals("wxPubQR") || channel.equals("alipayQR") || channel.equals("jdQR") ){
//					//展示二维码，请商户调用第三方库将code_url生成二维码图片
//					request.setAttribute("codeUrl", returnMap.get("codeUrl"));
//				}else if(channel.equals("wxApp")){
//					//请商户调用sdk控件发起支付
//					request.setAttribute("payCode", returnMap.get("payCode"));
//				}else if(channel.equals("wxMicro") || channel.equals("alipayMicro") || channel.equals("jdMicro")){
//					//支付成功后的处理，更新订单状态等
//				}else {
//					String payCode = returnMap.get("payCode");
//					if(StringUtils.indexOf(payCode, "http") == 0){
//						//request.setAttribute("payCode", returnMap.get("payCode"));
//						return "redirect:" + returnMap.get("payCode");
//					}else{
//						request.setAttribute("payCode", payCode);
//					}
//				}
//			}else{
//				request.setAttribute("resultCode", returnMap.get("resultCode"));
//				request.setAttribute("errCodeDes", returnMap.get("errCodeDes"));
//				request.setAttribute("returnMsg", returnMap.get("returnMsg"));
//			}
//		}else {
//			request.setAttribute("errorMsg", "验签错误");
//			return "errorPage";
//		}
//		
//		return "successPage";
//
//	}
//
//}
