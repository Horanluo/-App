package com.alycloud.channel.payeco.support.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import com.alycloud.channel.payeco.bean.PayByAccData;
import com.alycloud.channel.payeco.bean.PayByAccResult;
import com.alycloud.channel.payeco.bean.PayByAccResultBody;
import com.alycloud.channel.payeco.utils.Base64;

/**
 * 易联支付－无磁无密交易接口
 * @author Moyq5
 * @date 2017年4月13日
 */
public class PayByAccClient extends AbstractClient<PayByAccData, PayByAccResult> {
	
	@Override
	protected String getSignString(PayByAccData data) {
		return mkQueryString(true, data);
	}
	
	@Override
	protected final String getQueryString(final PayByAccData data) {
		
		String encode = context.getConfig().getDataEncode();
		try {
			// 可能能中文的转base64
			data.setOrderDesc(Base64.encodeBytes(data.getOrderDesc().getBytes(encode)));
			if (null != data.getExtData()) {
				data.setExtData(Base64.encodeBytes(data.getExtData().getBytes(encode)));
			}
			if (null != data.getMiscData()) {
				data.setMiscData(Base64.encodeBytes(data.getMiscData().getBytes(encode)));
			}
			// 通知地址做URLEncoder处理
			data.setNotifyUrl(URLEncoder.encode(data.getNotifyUrl(), encode));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return mkQueryString(false, data);
	}
	
	@Override
	protected Class<PayByAccResult> getResultClass() {
		return PayByAccResult.class;
	}

	@Override
	protected final String getSignString(PayByAccResult result) {
		PayByAccResultBody body = result.getBody();
		StringBuffer sb = new StringBuffer();
		sb.append("Version=");
		sb.append(body.getVersion());
		sb.append("&MerchantId=");
		sb.append(body.getMerchantId());
		sb.append("&MerchOrderId=");
		sb.append(body.getMerchOrderId());
		sb.append("&Amount=");
		sb.append(body.getAmount());
		String extData = body.getExtData();
		if (extData != null){
			String encode = context.getConfig().getDataEncode();
			extData = extData.replaceAll(" ", "+");
			try {
				extData = new String(Base64.decode(extData), encode);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		sb.append("&ExtData=");
		sb.append(extData);
		sb.append("&OrderId=");
		sb.append(body.getOrderId());
		sb.append("&Status=");
		sb.append(body.getStatus().getValue());
		sb.append("&PayTime=");
		sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(body.getPayTime()));
		sb.append("&SettleDate=");
		sb.append(new SimpleDateFormat("yyyyMMdd").format(body.getSettleDate()));
		
		return sb.toString();
	}
	
	private String mkQueryString(boolean is4Sign, PayByAccData data) {
		StringBuffer sb = new StringBuffer();
		sb.append("Version=");
		sb.append(data.getVersion());
		sb.append("&MerchantId=");
		sb.append(data.getMerchantId());
		sb.append("&IndustryId=");
		sb.append(data.getIndustryId());
		sb.append("&MerchOrderId=");
		sb.append(data.getMerchOrderId());
		sb.append("&Amount=");
		sb.append(data.getAmount());
		sb.append("&OrderDesc=");
		sb.append(data.getOrderDesc());
		sb.append("&TradeTime=");
		sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(data.getTradeTime()));
		sb.append("&ExpTime=");
		sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(data.getExpTime()));
		sb.append("&NotifyUrl=");
		sb.append(data.getNotifyUrl());
		sb.append("&ExtData=");
		sb.append(data.getExtData());
		sb.append("&MiscData=");
		sb.append(data.getMiscData());
		sb.append("&NotifyFlag=");
		sb.append(data.getNotifyFlag().ordinal());
		sb.append("&SmId=");
		sb.append(data.getSmId());
		sb.append("&SmCode=");
		sb.append(data.getSmCode());
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
