package com.alycloud.channel.yufu.qrcode.support.client;

import com.alycloud.channel.yufu.qrcode.bean.UnifiedOrderData;
import com.alycloud.channel.yufu.qrcode.bean.UnifiedOrderResult;
import com.alycloud.channel.yufu.qrcode.support.AbstractClient;
import com.alycloud.channel.yufu.qrcode.support.Factory;

/**
 * 预统一下单接口
 * @author Moyq5
 * @date 2017年8月29日
 */
public class UnifiedOrder extends AbstractClient<UnifiedOrderData, UnifiedOrderResult> {

	@Override
	protected String getServerPath() {
		return Factory.getConfig().getServerPath() + "unifiedOrder20";
	}

	@Override
	protected Class<UnifiedOrderResult> getResultClass() {
		return UnifiedOrderResult.class;
	}

}
