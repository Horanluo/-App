package com.alycloud.channel.yufu.qrcode.support.client;

import com.alycloud.channel.yufu.qrcode.bean.OrderQueryData;
import com.alycloud.channel.yufu.qrcode.bean.OrderQueryResult;
import com.alycloud.channel.yufu.qrcode.support.AbstractClient;
import com.alycloud.channel.yufu.qrcode.support.Factory;

/**
 * 查询接口
 * @author Moyq5
 * @date 2017年8月29日
 */
public class OrderQuery extends AbstractClient<OrderQueryData, OrderQueryResult> {

	@Override
	protected String getServerPath() {
		return Factory.getConfig().getServerPath() + "orderQuery20";
	}

	@Override
	protected Class<OrderQueryResult> getResultClass() {
		return OrderQueryResult.class;
	}

}
