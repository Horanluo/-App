package com.alycloud.channel.yufu.qrcode.support.client;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.yufu.qrcode.bean.NotifyCallbackResult;
import com.alycloud.channel.yufu.qrcode.enums.ChannelFlag;
import com.alycloud.channel.yufu.qrcode.enums.RespCode;
import com.alycloud.channel.yufu.qrcode.support.Client;
import com.alycloud.channel.yufu.qrcode.support.Factory;
import com.alycloud.channel.yufu.qrcode.support.Tool;

/**
 * 异步通知处理
 * @author Moyq5
 * @date 2017年9月12日
 */
public class NotifyCallback implements Client<HttpServletRequest, NotifyCallbackResult> {

	private static final Logger log = LoggerFactory.getLogger(NotifyCallback.class);
	
	@Override
	public NotifyCallbackResult execute(HttpServletRequest request) {
		NotifyCallbackResult resResult = new NotifyCallbackResult();
		try {
			
			Map<String, String> sortMap = new TreeMap<String, String>();
			@SuppressWarnings("unchecked")
			Map<String, String[]> paramMap = request.getParameterMap();
			Iterator<String> it = paramMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				sortMap.put(key, paramMap.get(key)[0]);
			}
			
			String signIn = sortMap.get("signIn");
			sortMap.remove("signIn");// sign参数不参与签名
			
			String sign = Tool.sign(sortMap, Factory.getConfig().getKey());
			if (!sign.equals(signIn)) {
				throw new Exception("签名验证失败");
			} else {
				log.debug("签名验证通过");
			}
			
			String channelFlag = sortMap.get("channelFlag");
			RespCode code = RespCode.SUCCESS;
			code.setValue("0");
			code.setText("交易成功");
			resResult.setRespCode(code);
			resResult.setRespDesc(code.getText());
			
			resResult.setAmount(sortMap.get("amount"));
			resResult.setBankType(sortMap.get("bankType"));
			resResult.setBuyerId(sortMap.get("buyerId"));
			resResult.setBuyerPayAmount(sortMap.get("buyerPayAmount"));
			resResult.setChannelFlag(ChannelFlag.values()[Integer.parseInt(channelFlag)]);
			resResult.setCurrency(sortMap.get("currency"));
			resResult.setExtraDesc(sortMap.get("extraDesc"));
			resResult.setGoodsName(sortMap.get("goodsName"));
			resResult.setInvoiceAmount(sortMap.get("invoiceAmount"));
			resResult.setMerchNo(sortMap.get("merNo"));
			resResult.setNotifyTime(sortMap.get("notifyTime"));
			resResult.setOrderNo(sortMap.get("orderNo"));
			resResult.setOutOrderNo(sortMap.get("outOrderNo"));
			resResult.setPayTime(sortMap.get("payTime"));
			resResult.setReceiptAmount(sortMap.get("receiptAmount"));
			resResult.setReqId(sortMap.get("reqId"));
			//resResult.setRespCode(respCode);
			//resResult.setRespDesc(respDesc);
			resResult.setSign(signIn);
			resResult.setTermNo(sortMap.get("termNo"));
			resResult.setTotalAmount(sortMap.get("totalAmount"));
			resResult.setTransId(sortMap.get("transId"));
			resResult.setUuid(sortMap.get("uuid"));
			
		} catch (Exception e) {
			log.error("接口调用失败", e);
			RespCode code = RespCode.FAIL;
			code.setValue("9999");
			code.setText(e.getMessage());
			resResult.setRespCode(code);
			resResult.setRespDesc(e.getMessage());
		}
		return resResult;
	}

}
