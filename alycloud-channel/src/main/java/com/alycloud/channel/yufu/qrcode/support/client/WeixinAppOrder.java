package com.alycloud.channel.yufu.qrcode.support.client;

import com.alycloud.channel.yufu.qrcode.bean.WeixinAppOrderData;
import com.alycloud.channel.yufu.qrcode.bean.WeixinAppOrderResult;
import com.alycloud.channel.yufu.qrcode.support.AbstractClient;
import com.alycloud.channel.yufu.qrcode.support.Factory;

/**
 * 微信APP支付接口
 * @author Moyq5
 * @date 2017年9月27日
 */
public class WeixinAppOrder extends AbstractClient<WeixinAppOrderData, WeixinAppOrderResult> {

	@Override
	protected String getServerPath() {
		return Factory.getConfig().getServerPath() + "wxAppUnifiedOrder20";
	}

	@Override
	protected Class<WeixinAppOrderResult> getResultClass() {
		return WeixinAppOrderResult.class;
	}

}
