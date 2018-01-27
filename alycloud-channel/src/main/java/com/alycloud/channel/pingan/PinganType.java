package com.alycloud.channel.pingan;

import com.alycloud.channel.pingan.support.AbstractClient;
import com.alycloud.channel.pingan.support.client.PayOrder;
import com.alycloud.channel.pingan.support.client.PayRefund;
import com.alycloud.channel.pingan.support.client.PayStatus;

/**
 * 平安银行支付－接口类型
 * @author Moyq5
 * @date 2017年5月9日
 */
public enum PinganType {

	/**
	 * 下单接口接口：PayOrder
	 */
	PAY_ORDER(PayOrder.class),
	/**
	 * 查询付款状态接口：PayStatus
	 */
	PAY_STATUS(PayStatus.class),
	/**
	 * 订单退款接口：PayRefund
	 */
	PAY_REFUND(PayRefund.class);
	
	private Class<? extends AbstractClient<?,?>> clientClass;
	
	PinganType(Class<? extends AbstractClient<?,?>> clientClass) {
		this.clientClass = clientClass;
	}

	public Class<? extends AbstractClient<?,?>> getClientClass() {
		return clientClass;
	}

}
