package com.alycloud.channel.pingan.enums;

/**
 * 订单状态
 * @author Moyq5
 * @date 2017年6月14日
 */
public enum OrderStatus {

	/**
	 * 待支付
	 */
	WAIT_PAY("待支付"),
	/**
	 * 等待用户输入密码确认
	 */
	WAIT_PWD("等待用户输入密码确认"),
	/**
	 * 交易成功
	 */
	SUCCESS("交易成功"),
	/**
	 * 已取消
	 */
	CANCELED("已取消");
	
	private String text;
	
	OrderStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
