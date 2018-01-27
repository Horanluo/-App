package com.alycloud.channel.payeco.support.client;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import com.alycloud.channel.payeco.bean.SendSmCodeData;
import com.alycloud.channel.payeco.bean.SendSmCodeResult;
import com.alycloud.channel.payeco.bean.SendSmCodeResultBody;
import com.alycloud.channel.payeco.utils.Base64;

/**
 * 易联支付－短信验证码发送接口
 * @author Moyq5
 * @date 2017年4月13日
 */
public class SendSmCodeClient extends AbstractClient<SendSmCodeData, SendSmCodeResult> {

	@Override
	protected String getSignString(SendSmCodeData data) {
		return mkQueryString(true, data);
	}
	
	@Override
	protected final String getQueryString(final SendSmCodeData data) {
		String encode = context.getConfig().getDataEncode();
		try {
			// 可能能中文的转base64
			data.setSmParam(Base64.encodeBytes(data.getSmParam().getBytes(encode)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return mkQueryString(false, data);
	}
	
	@Override
	protected Class<SendSmCodeResult> getResultClass() {
		return SendSmCodeResult.class;
	}

	@Override
	protected final String getSignString(SendSmCodeResult result) {
		SendSmCodeResultBody body = result.getBody();
		StringBuffer sb = new StringBuffer();
		sb.append("Version=");
		sb.append(body.getVersion());
		sb.append("&MerchantId=");
		sb.append(body.getMerchantId());
		sb.append("&SmId=");
		sb.append(body.getSmId());
		sb.append("&MerchOrderId=");
		sb.append(body.getMerchOrderId());
		sb.append("&TradeTime=");
		sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(body.getTradeTime()));
		sb.append("&Complated=");
		sb.append(body.getComplated());
		sb.append("&Remain=");
		sb.append(body.getRemain());
		sb.append("&ExpTime=");
		sb.append(body.getExpTime());
		return sb.toString();
	}
	
	private String mkQueryString(boolean is4Sign, SendSmCodeData data) {
		StringBuffer sb = new StringBuffer();
		sb.append("Version=");
		sb.append(data.getVersion());
		sb.append("&MerchantId=");
		sb.append(data.getMerchantId());
		sb.append("&SmId=");
		sb.append(data.getSmId());
		sb.append("&MerchOrderId=");
		sb.append(data.getMerchOrderId());
		sb.append("&TradeTime=");
		sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(data.getTradeTime()));
		sb.append("&MobileNo=");
		sb.append(data.getMobileNo());
		sb.append("&VerifyTradeCode=");
		sb.append(data.getVerifyTradeCode());
		sb.append("&SmParam=");
		sb.append(data.getSmParam());
		if (is4Sign) {
			return sb.toString();
		}
		sb.append("&Sign=");
		sb.append(data.getSign());
		sb.append("&TradeCode=");
		sb.append(data.getTradeCode());
		return sb.toString();
	}

}
