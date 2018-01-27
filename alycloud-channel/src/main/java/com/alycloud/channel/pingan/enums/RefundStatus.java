package com.alycloud.channel.pingan.enums;

/**
 * 订单退款状态
 * @author Moyq5
 * @date 2017年7月10日
 */
public enum RefundStatus {

	/**
	 * 成功
	 */
	SUCCESS("成功"),
	/**
	 * 失败
	 */
	FAIL("失败");
	
	private String text;
	
	RefundStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
