package com.alycloud.channel.pingan.support.client;

import com.alycloud.channel.pingan.bean.PayOrderData;
import com.alycloud.channel.pingan.bean.PayOrderResult;
import com.alycloud.channel.pingan.support.AbstractClient;

/**
 * 下单接口
 * @author Moyq5
 * @date 2017年6月16日
 */
public class PayOrder extends AbstractClient<PayOrderData, PayOrderResult> {

	@Override
	protected Class<PayOrderResult> getDetailClass() {
		return PayOrderResult.class;
	}

	@Override
	protected String getServerPath() {
		return super.getServerPath() + "payorder";
	}

}
