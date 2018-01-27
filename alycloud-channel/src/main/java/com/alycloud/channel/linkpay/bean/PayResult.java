package com.alycloud.channel.linkpay.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 支付接口－下单结果－响应参数<br>
 * 微信支付、支付宝支付、QQ钱包支付、网关支付等接口下单响应结果
 * @author Moyq5
 * @date 2017年4月10日
 */
public class PayResult {

	/**
	 * 合作商订单号，必填
	 */
	@JsonProperty("orderNum")
	private String customOrderNum;
	/**
	 * 平台订单号，必填
	 */
	@JsonProperty("pl_orderNum")
	private String orderNum;
	/**
	 * 支付URL，用于生成二维码，必填
	 */
	@JsonProperty("pl_url")
	private String url;
	
	public String getCustomOrderNum() {
		return customOrderNum;
	}
	public void setCustomOrderNum(String customOrderNum) {
		this.customOrderNum = customOrderNum;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "PayResult [customOrderNum=" + customOrderNum + ", orderNum=" + orderNum + ", url=" + url + "]";
	}
	
}
