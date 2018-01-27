package com.alycloud.channel.payeco.payment.support.client;

import java.security.interfaces.RSAPrivateKey;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.payeco.payment.PayecoConfig;
import com.alycloud.channel.payeco.payment.PayecoContext;
import com.alycloud.channel.payeco.payment.bean.Payment;
import com.alycloud.channel.payeco.payment.bean.TransDetail;
import com.alycloud.channel.payeco.payment.support.utils.Base64;
import com.alycloud.channel.payeco.payment.support.utils.Des;
import com.alycloud.channel.payeco.payment.support.utils.RSA;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * 易联代付接口
 * @author Moyq5
 * @date 2017年4月25日
 */
public class PaymentClient {

	private static Logger log = LoggerFactory.getLogger(PaymentClient.class);
	private ObjectMapper om;
	protected PayecoContext context;
	
	public PaymentClient() {
		om = new XmlMapper();
		om.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	public Payment request(Payment data) {
		try {
			PayecoConfig cfg = context.getConfig();
			String pfxPath = cfg.getPfxPath();
			String pfxPass = cfg.getPfxPass();
			String pubKey = cfg.getPubKey();
			
	        RSAPrivateKey rsaKey = RSA.getPrivateKey(pfxPath, pfxPass);
	        
	        // 生成签名
			String signSrc = getSignString(data);
			String sign = RSA.signMd5(rsaKey, signSrc);
			data.setMsgSign(sign);
			
			String hexKey = genHexString(24);
			String dataXml = om.writeValueAsString(data);
			log.debug("请求报文(明文):" + dataXml);
			
			String encReqData = Des.encrypt(hexKey, dataXml);
			String encReqHexKey = RSA.encrypt64(hexKey, pubKey);
	        
			String requestBody = encReqData + "|" + encReqHexKey;
			log.debug("请求报文(密文):" + requestBody);
			
	        String responseBody = context.getHttpClient().post(cfg.getServer(), requestBody);
	        log.debug("响应报文(密文):" + responseBody);
	        
	        String encResData = responseBody.split("\\|")[0];
	        String encResHexKey = responseBody.split("\\|")[1];
	        
	        // 解密密钥
	        hexKey = RSA.decrypt(rsaKey, encResHexKey);
	        
	        // 解密报文
	        dataXml = Des.decrypt(hexKey, encResData);
	        log.debug("响应报文(明文):" + dataXml);
	        
	        data = om.readValue(dataXml, Payment.class);
	        
	        signSrc = getSignString(data);
	        boolean isValied = RSA.verifyMd5(data.getMsgSign(), pubKey, signSrc);
	        if (!isValied) {
	        	throw new Exception("签名有误");
	        }
	        
	        return data;
		} catch (Exception e) {
			log.error("代付异常", e);
		}
		return null;
	}
	
	private String getSignString(Payment data){
		StringBuffer sb = new StringBuffer(data.getBatchNo());
		sb.append(getFieldValue(data.getUserName()));
		sb.append(getFieldValue(isEmpty(data.getMsgType())? null: data.getMsgType().getValue()));
		sb.append(getFieldValue(isEmpty(data.getTransState())? null: data.getTransState().getValue()));
		List<TransDetail> details = data.getTransDetails().getTransDetails();
		for(TransDetail detail:details){
			sb.append(getFieldValue(detail.getSn()));
			sb.append(getFieldValue(isEmpty(detail.getPayState())? null: detail.getPayState().getValue()));
			sb.append(getFieldValue(detail.getAccNo()));
			sb.append(getFieldValue(detail.getAccName()));
			sb.append(getFieldValue(detail.getAmount()));
			sb.append(getFieldValue(isEmpty(detail.getCny()) ? null: detail.getCny().name()));
			sb.append(getFieldValue(isEmpty(detail.getUserLevel()) ? null: detail.getUserLevel().ordinal()));
		}
		log.debug("签名源数据：\r\n{}", sb.toString());
		return sb.toString();
	}
	
	private boolean isEmpty(Object obj) {
		return null == obj || String.valueOf(obj).trim().isEmpty();
	}
	private String getFieldValue(Object value) {
		return isEmpty(value)? "":" " + value;
	}
	
	public PayecoContext getContext() {
		return context;
	}

	public void setContext(PayecoContext context) {
		this.context = context;
	}
	
	private static String genHexString(int length) {
		final String str = "0123456789ABCDEF";
		Random rand = new Random();
		String key = "";
		for(int i = 0;i < length;i++) {
			key += str.charAt(rand.nextInt(9999)%16);
		}
		return Base64.encodeBytes(key.getBytes());
	}
	
}
