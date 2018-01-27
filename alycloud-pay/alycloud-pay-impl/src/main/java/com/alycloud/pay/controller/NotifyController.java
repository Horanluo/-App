//package com.alycloud.pay.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import com.alibaba.fastjson.JSONObject;
//import com.alycloud.core.utils.MD5;
//import com.alycloud.modules.entity.QrcodeOrder;
//import com.alycloud.modules.enums.QrcodeOrderStatus;
//import com.alycloud.pay.service.impl.QrcodeOrderService;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author Horanluo
// * @date 2017年11月20日
// */
//@RestController
//@Slf4j
//public class NotifyController {
//	@Autowired
//	private QrcodeOrderService qrcodeOrderService;
//	
//	/**
//	 * 扫呗wap支付成功 回调
//	 * @author Horanluo
//	 * @throws Exception 
//	 * @date 2018年1月5日
//	 */
//	@PostMapping(value="/notify/saobei")
//	public void saobeiNotify(@RequestBody String respMes) throws Exception {
//		log.info("扫呗支付响应结果:"+respMes);
//		
//		JSONObject jsonResp = JSONObject.parseObject(respMes);
//		if(MD5.verify(respMes, jsonResp.remove("key_sign").toString(), "utf-8")){
//			throw new Exception("验签失败");
//		}
//		
//		String returnCode = jsonResp.getString("return_code");
//		String returnMsg = jsonResp.getString("return_msg");
//		String resultCode = jsonResp.getString("result_code");
//		String merchantNo = jsonResp.getString("merchant_no");
//		String terminalTrace = jsonResp.getString("terminal_trace");
//		String payTime = jsonResp.getString("pay_time");
//		String totalFee = jsonResp.getString("total_fee");
//		String endTime = jsonResp.getString("end_time");
//		String outTradeNo = jsonResp.getString("out_trade_no");
//		String channelTradeNo = jsonResp.getString("channel_trade_no");
//		log.info("响应码:{},响应信息:{},业务返回结果:{},商户号:{},交易流水号:{},交易时间:{},交易金额:{},交易完成时间:{},利楚唯一订单号:{},通道订单号:{}",
//				returnCode,returnMsg,resultCode,merchantNo,terminalTrace,payTime,totalFee,endTime,outTradeNo,channelTradeNo);
//		
//		QrcodeOrder order = new QrcodeOrder();
//		if("01".equals(returnCode)&&"01".equals(resultCode)){
//			//交易成功,更新订单状态
//			order.setOrderno(terminalTrace);
//			order.setChannelOrderno(outTradeNo);
//			qrcodeOrderService.qrcodePaySuccess(order);
//		}else{
//			order.setStatus(QrcodeOrderStatus.FAIL.ordinal());
//			order.setStatusDesc(returnMsg);
//		}
//	}
//}
