package com.alycloud.channel.yufu.qrcode.support.client;

import com.alycloud.channel.yufu.qrcode.bean.OfficalOrderData;
import com.alycloud.channel.yufu.qrcode.bean.OfficalOrderResult;
import com.alycloud.channel.yufu.qrcode.support.AbstractClient;
import com.alycloud.channel.yufu.qrcode.support.Factory;

/**
 * 微信公众号、小程序支付
 * @author Moyq5
 * @date 2017年9月14日
 */
public class OfficalOrder extends AbstractClient<OfficalOrderData, OfficalOrderResult> {

	@Override
	protected String getServerPath() {
		return Factory.getConfig().getServerPath() + "wxGZHUnifiedOrder20";
	}

	@Override
	protected Class<OfficalOrderResult> getResultClass() {
		return OfficalOrderResult.class;
	}

}
