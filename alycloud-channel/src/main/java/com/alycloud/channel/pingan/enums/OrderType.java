package com.alycloud.channel.pingan.enums;

/**
 * 订单类型，注意：枚举顺序不可变
 * @author Moyq5
 * @date 2017年6月14日
 */
public enum OrderType {

	/**
	 * 消费
	 */
	CONSUME("消费"),
	/**
	 * 辙单
	 */
	REVOCATION("辙单");
	
	private String text;
	
	OrderType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
