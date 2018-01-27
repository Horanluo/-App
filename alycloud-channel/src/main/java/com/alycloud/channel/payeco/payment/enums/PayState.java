package com.alycloud.channel.payeco.payment.enums;

/**
 * 代付状态码
 * @author Moyq5
 * @date 2017年4月25日
 */
public enum PayState {
	/**
	 * 订单交易成功
	 */
	STATE_0000("0000", "订单交易成功"),
	/**
	 * 交易状态不明确
	 */
	STATE_00A4("00A4", "交易状态不明确");
	
	private String value;
	private String text;
	
	PayState(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

}
