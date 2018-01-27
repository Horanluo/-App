package com.alycloud.channel.pingan.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 查询付款状态接口data参数对象，需要转换成JSON并加密赋给<code>CommonData</code>对象的<code>data</code>属性
 * @author Moyq5
 * @date 2017年6月14日
 */
public class PayStatusData {

	/**
	 * 订单号，开发者流水号必须填写一个
	 */
	@JsonProperty("ord_no")
	private String orderNo;
	
	/**
	 * 开发者流水号，订单号必须填写一项
	 */
	@JsonProperty("out_no")
	private String customOrderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomOrderNo() {
		return customOrderNo;
	}

	public void setCustomOrderNo(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}

	@Override
	public String toString() {
		return "PayStatusData [orderNo=" + orderNo + ", customOrderNo=" + customOrderNo + "]";
	}
	
	
}
