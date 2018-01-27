package com.alycloud.channel.esyto;

import com.alycloud.channel.esyto.support.Client;
import com.alycloud.channel.esyto.support.client.NotifyCallback;
import com.alycloud.channel.esyto.support.client.OrderQuery;
import com.alycloud.channel.esyto.support.client.WeixinAppOrder;

/**
 * 易商付支付－接口类型
 * @author Moyq5
 * @date 2017年9月29日
 */
public enum EsytoApiType {
	/**
	 * 查询接口：OrderQuery
	 */
	ORDER_QUERY(OrderQuery.class),
	/**
	 * 异步通知处理：NotifyCallback
	 */
	NOTIFY_CALLBACK(NotifyCallback.class),
	/**
	 * 微信APP支付接口：WeixinAppOrder
	 */
	WEIXIN_APP_ORDER(WeixinAppOrder.class);
	
	private Class<? extends Client<?,?>> clientClass;
	
	EsytoApiType(Class<? extends Client<?,?>> clientClass) {
		this.clientClass = clientClass;
	}

	public Class<? extends Client<?,?>> getClientClass() {
		return clientClass;
	}

}
