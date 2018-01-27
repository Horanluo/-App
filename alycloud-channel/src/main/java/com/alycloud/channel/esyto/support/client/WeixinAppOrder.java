package com.alycloud.channel.esyto.support.client;

import com.alycloud.channel.esyto.bean.WeixinAppOrderData;
import com.alycloud.channel.esyto.bean.WeixinAppOrderResult;
import com.alycloud.channel.esyto.support.AbstractClient;

/**
 * 微信APP支付接口
 * @author Moyq5
 * @date 2017年9月29日
 */
public class WeixinAppOrder extends AbstractClient<WeixinAppOrderData, WeixinAppOrderResult> {

	@Override
	protected String getServerPath() {
		return super.getServerPath() + "weixinAppOrder";
	}

	@Override
	protected Class<WeixinAppOrderResult> getResultClass() {
		return WeixinAppOrderResult.class;
	}

}
