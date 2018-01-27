package com.alycloud.channel.yufu.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 订单查询请求参数对象
 * @author Moyq5
 * @date 2017年8月2日
 */
public class OrderQueryData extends AbstractData {

	/**
	 * 订单号，必填，商城的支付订单号。
	 */
	@JsonProperty("order_no")
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "OrderQueryData [orderNo=" + orderNo + ", toString()=" + super.toString() + "]";
	}
	
}
