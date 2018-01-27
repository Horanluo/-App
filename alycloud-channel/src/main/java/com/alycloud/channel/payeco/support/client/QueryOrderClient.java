package com.alycloud.channel.payeco.support.client;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.alycloud.channel.payeco.bean.QueryOrderData;
import com.alycloud.channel.payeco.bean.QueryOrderResult;
import com.alycloud.channel.payeco.bean.QueryOrderResultBody;
import com.alycloud.channel.payeco.utils.Base64;

/**
 * 易联支付－订单查询接口
 * @author Moyq5
 * @date 2017年4月13日
 */
public class QueryOrderClient extends AbstractClient<QueryOrderData, QueryOrderResult> {
	
	@Override
	protected String getSignString(QueryOrderData data) {
		return mkQueryString(true, data);
	}
	
	@Override
	protected final String getQueryString(final QueryOrderData data) {
		return mkQueryString(false, data);
	}
	
	@Override
	protected Class<QueryOrderResult> getResultClass() {
		return QueryOrderResult.class;
	}
	
	@Override
	protected final String getSignString(QueryOrderResult result) {
		QueryOrderResultBody body = result.getBody();
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
		sb.append(null == body.getPayTime() ? "": new SimpleDateFormat("yyyyMMddHHmmss").format(body.getPayTime()));
		sb.append("&SettleDate=");
		sb.append(null == body.getSettleDate() ? "": new SimpleDateFormat("yyyyMMdd").format(body.getSettleDate()));
		
		return sb.toString();
	}

	private String mkQueryString(boolean is4Sign, QueryOrderData data) {
		StringBuffer sb = new StringBuffer();
		sb.append("Version=");
		sb.append(data.getVersion());
		sb.append("&MerchantId=");
		sb.append(data.getMerchantId());
		sb.append("&MerchOrderId=");
		sb.append(data.getMerchOrderId());
		sb.append("&TradeTime=");
		sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(data.getTradeTime()));
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
