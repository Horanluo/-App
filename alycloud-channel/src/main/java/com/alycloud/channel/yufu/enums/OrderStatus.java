package com.alycloud.channel.yufu.enums;

/**
 * 订单状态
 * @author Moyq5
 * @date 2017年8月2日
 */
public enum OrderStatus {

	/**
	 * 未处理
	 */
	NEW("未处理"),
	/**
	 * 处理中
	 */
	PROGRESS("处理中"),
	/**
	 * 成功
	 */
	SUCCESS("成功"),
	/**
	 * 失败
	 */
	FAIL("失败");
	
	private String text;
	
	OrderStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
