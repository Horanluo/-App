package com.alycloud.channel.payeco;

import com.alycloud.channel.payeco.support.client.AbstractClient;
import com.alycloud.channel.payeco.support.client.PayByAccClient;
import com.alycloud.channel.payeco.support.client.QueryOrderClient;
import com.alycloud.channel.payeco.support.client.SendSmCodeClient;

/**
 * 易联支付－接口类型
 * @author Moyq5
 * @date 2017年4月14日
 */
public enum PayecoType {

	/**
	 * 无磁无密交易接口：PayByAccClient
	 */
	PAY_BY_ACC(PayByAccClient.class),
	/**
	 * 订单查询接口：QueryOrderClient
	 */
	QUERY_ORDER(QueryOrderClient.class),
	/**
	 * 短信验证码发送接口：SendSmCodeClient
	 */
	SEND_SM_CODE(SendSmCodeClient.class);
	
	private Class<? extends AbstractClient<?,?>> clientClass;
	
	PayecoType(Class<? extends AbstractClient<?,?>> clientClass) {
		this.clientClass = clientClass;
	}

	public Class<? extends AbstractClient<?,?>> getClientClass() {
		return clientClass;
	}

}
