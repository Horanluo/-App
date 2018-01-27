package com.alycloud.pay.support.channel.api.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.utils.HttpPostUtil;
import com.alycloud.core.utils.MD5;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.QrcodeMerch;
import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.search.QrcodeMerch4Search;
import com.alycloud.pay.mapper.QrcodeMerchMapper;
import com.alycloud.pay.support.channel.api.ChannelApiAdapter;
import com.alycloud.pay.support.channel.api.ChannelApiException;
import com.alycloud.pay.support.channel.api.ChannelApiExecuteFailException;
import com.alycloud.pay.support.channel.api.ChannelOrderStatus;
import com.alycloud.pay.support.channel.api.ChannelOrderStatusResult;
import com.alycloud.pay.support.channel.api.ChannelQrcodeOrderResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 扫呗渠道(二维码)接口
 * @author Horanluo
 * @date 2018年01月08日
 */
@Slf4j
@Service
public class SaoBeiQrcodeApi extends ChannelApiAdapter {

	@Autowired
	private QrcodeMerchMapper qrcodeMerchMapper;
	@Value("${prePay_url}")
	private String prePayUrl;
	@Value("${front_url}")
	private String frontUrl;
	@Value("${notify_url}")
	private String notifyUrl;
	@Value("${pay_ver}")
	private String payVer;
	@Value("${service_id}")
	private String serviceId;
//	@Value("${terminal_id}")
//	private String terminalId;
	@Value("${query_url}")
	private String queryUrl;
	
	@Override
	@ServiceLogAnnotation(moduleName="扫呗二维码支付")
	public ChannelQrcodeOrderResult qrcodePay(QrcodeOrder order) throws ChannelApiException {
		QrcodeMerch qrcodeMerch = getQrcodeMerch(order);
		log.info("平台商户号:{},渠道商户号:{},终端号:{},:{},access_token:{},预支付地址:{},前端通知地址:{},异步通知地址:{}", qrcodeMerch.getMerchno(),
				qrcodeMerch.getPartnerId(),qrcodeMerch.getTermno(),qrcodeMerch.getMerchKey(),prePayUrl,frontUrl,notifyUrl);
		
		ChannelQrcodeOrderResult res = new ChannelQrcodeOrderResult();
		try {
			//加签封装数据
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("merchant_no", qrcodeMerch.getPartnerId());
			sParaTemp.put("terminal_id", qrcodeMerch.getTermno());
			sParaTemp.put("terminal_trace", order.getOrderno());
			sParaTemp.put("terminal_time", order.getTraceno());
			sParaTemp.put("total_fee", order.getAmount().multiply(new BigDecimal("100")).setScale(0).toPlainString());
			sParaTemp.put("order_body", "CollectMoney");
			sParaTemp.put("notify_url", "");
			sParaTemp.put("front_url", frontUrl);
			sParaTemp.put("auto_pay","");
			sParaTemp.put("attach", "");
			
			Map<String, String> sParaForSign = StrUtil.paraFilter(sParaTemp);
			String preSignstr = StrUtil.createLinkString(sParaForSign);
			preSignstr = preSignstr+"&access_token="+qrcodeMerch.getMerchKey();
			log.info("签名前参数:{}",preSignstr);
			String keySign = MD5.sign(preSignstr, "utf-8");
			log.info("签名值:{}",keySign);
			
			//请求封装数据
			Map<String, String> reqParam = new HashMap<String, String>();
			reqParam.put("merchant_no", sParaTemp.get("merchant_no"));
			reqParam.put("terminal_id", sParaTemp.get("terminal_id"));
			reqParam.put("terminal_trace", sParaTemp.get("terminal_trace"));
			reqParam.put("terminal_time", sParaTemp.get("terminal_time"));
			reqParam.put("total_fee", sParaTemp.get("total_fee"));
			reqParam.put("order_body", sParaTemp.get("order_body"));
			reqParam.put("notify_url", java.net.URLEncoder.encode(sParaTemp.get("notify_url"),"utf-8"));
			reqParam.put("front_url", java.net.URLEncoder.encode(frontUrl,"utf-8"));
			reqParam.put("auto_pay", sParaTemp.get(""));
			reqParam.put("attach", sParaTemp.get(""));
			reqParam.put("key_sign", keySign);
			
			reqParam = StrUtil.paraFilter(reqParam);
			String reqStr = StrUtil.createLinkString(reqParam);
			String getReqUrl=prePayUrl+"?"+reqStr;
			log.info("请求扫呗wap接口参数:{}",getReqUrl);
			res.setQrcode(getReqUrl);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new ChannelApiExecuteFailException("请求扫呗支付失败");
		}
		return res;
		
	}

	private QrcodeMerch getQrcodeMerch(QrcodeOrder order) throws ChannelApiException {
		QrcodeMerch4Search merch4s = new QrcodeMerch4Search();
		merch4s.setPartnerId(order.getChannelMerchno());
		List<QrcodeMerch> merchList;
		try {
			merchList = qrcodeMerchMapper.listByPage(merch4s);
		} catch (Exception e) {
			throw new ChannelApiException(e);
		}
		return merchList.get(0);
	}

	@Override
	@ServiceLogAnnotation(moduleName="扫呗二维码查询")
	public ChannelOrderStatusResult queryQrcodePay(QrcodeOrder order) throws ChannelApiException {
		ChannelOrderStatusResult result = new ChannelOrderStatusResult();
		
		QrcodeMerch qrcodeMerch = getQrcodeMerch(order);
		String payTpe="";
		switch (order.getPayType()) {
		case 1:
			payTpe="020";
			break;
		case 2:
			payTpe="010";
			break;
		case 3:
			break;
		case 4:
			payTpe="060";
			break;
		case 5:
			payTpe="080";
			break;
		case 6:
			//payTpe="060";
			break;
		default:
			payTpe="000";
			break;
		}
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("pay_ver", payVer);
		jsonParam.put("pay_type", payTpe);
		jsonParam.put("service_id", serviceId);
		jsonParam.put("merchant_no", order.getChannelMerchno());
		jsonParam.put("terminal_id", qrcodeMerch.getTermno());
		jsonParam.put("terminal_trace", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		jsonParam.put("terminal_time", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		jsonParam.put("pay_trace", order.getOrderno());
		jsonParam.put("pay_time", order.getTraceno());
		jsonParam.put("out_trade_no", null==order.getChannelOrderno()?"":order.getChannelOrderno());
		
		String parm = "pay_ver=" + payVer + "&pay_type=" + payTpe + "&service_id="
				+ serviceId + "&merchant_no=" + order.getChannelMerchno() + "&terminal_id="
				+ qrcodeMerch.getTermno() + "&terminal_trace=" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ "&terminal_time=" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "&out_trade_no=" + 
				(null==order.getChannelOrderno()?"":order.getChannelOrderno())+ "&access_token=" +qrcodeMerch.getMerchKey();
		
		String sign = MD5.sign(parm, "utf-8");
		log.info("扫呗查询订单状态加签参数:{},加签值:{}",parm,sign);
		jsonParam.put("key_sign", sign);
		try {
			log.info("扫呗查询订单状态请求参数:{}",jsonParam.toString());
			String respMes = HttpPostUtil.postReq(queryUrl, jsonParam.toJSONString());
			log.info("扫码订单状态查询响应结果:{}",respMes);
			
			JSONObject jsonResp = JSONObject.parseObject(respMes);
			String returnCode = jsonResp.getString("return_code");
			String returnMsg = jsonResp.getString("return_msg");
			String resultCode = jsonResp.getString("result_code");
			String outTradeNo = jsonResp.getString("out_trade_no");
			
			log.info("响应码:{},响应信息:{},业务返回结果:{}",returnCode,returnMsg,resultCode);
			if("01".equals(returnCode)&&"01".equals(resultCode)){
				result.setStatus(ChannelOrderStatus.SUCCESS);
				result.setChannelOrderNo(outTradeNo);
				return result;
			}else{
				throw new ChannelApiException(returnMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("扫呗查询订单状态失败:{}",e.getMessage());
			result.setStatus(ChannelOrderStatus.FAIL);
			return result;
		}
	}
}
