package com.alycloud.pay.support.channel.api;

/**
 * 渠道订单状态
 * @author Moyq5
 * @date 2017年8月2日
 */
public enum ChannelOrderStatus {

	/**
	 * 未支付
	 */
	NEW("未支付"),
	/**
	 * 支付成功
	 */
	SUCCESS("支付成功"),
	/**
	 * 支付失败
	 */
	FAIL("支付失败"),
	/**
	 * 支付异常
	 */
	ERROR("支付异常");
	
	private String text;
	
	ChannelOrderStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
