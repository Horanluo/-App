package com.alycloud.channel.linkpay.enums;

/**
 * 江苏电子支付接口－订单支付代码
 * 
 * @author Moyq5
 * @date 2017年4月10日
 */
public enum PayState {

	/**
	 * 未下单
	 */
	ORDER_UNKNOWN(1, "未下单"),
	/**
	 * 下单成功，商户成功下单，但客户未进行支付或者支付未完成
	 */
	ORDER_SUCCESS(2, "下单成功"),
	/**
	 * 下单失败
	 */
	ORDER_FAIL(3, "下单失败"),
	/**
	 * 支付成功
	 */
	PAY_SUCCESS(4, "支付成功"),
	/**
	 * 支付失败
	 */
	PAY_FAIL(5, "支付失败"),
	/**
	 * 支付结果未明
	 */
	PAY_UNKNOWN(6, "支付结果未明");
	
	private Integer value;
	private String text;
	
	PayState(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public Integer getValue() {
		return value;
	}
}
