package com.alycloud.channel.payeco.payment.enums;

/**
 * 接口类型
 * @author Moyq5
 * @date 2017年4月25日
 */
public enum MsgType {

	/**
	 * 批量代付
	 */
	AGENT_PAYMENT(100001, "批量代付");
	
	private int value;
	private String text;
	
	MsgType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

}
