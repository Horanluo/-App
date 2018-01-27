package com.alycloud.channel.yufu;

import com.alycloud.channel.yufu.support.AbstractClient;
import com.alycloud.channel.yufu.support.client.MerchOrderQuery;
import com.alycloud.channel.yufu.support.client.OrderQuery;
import com.alycloud.channel.yufu.support.client.Pay;

/**
 * 御付支付－接口类型
 * @author Moyq5
 * @date 2017年8月2日
 */
public enum YufuApiType {

	/**
	 * 支付接口：Pay
	 */
	PAY(Pay.class),
	/**
	 * 订单查询接口：OrderQuery
	 */
	ORDER_QUERY(OrderQuery.class),
	/**
	 * 商户订单查询接口：MerchOrderQuery
	 */
	MERCH_ORDER_QUERY(MerchOrderQuery.class);
	
	private Class<? extends AbstractClient<?,?>> clientClass;
	
	YufuApiType(Class<? extends AbstractClient<?,?>> clientClass) {
		this.clientClass = clientClass;
	}

	public Class<? extends AbstractClient<?,?>> getClientClass() {
		return clientClass;
	}

}
