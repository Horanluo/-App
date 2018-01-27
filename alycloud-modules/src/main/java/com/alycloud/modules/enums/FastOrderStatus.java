package com.alycloud.modules.enums;

/**
 * 快捷支付订单状态;
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年4月20日
 */
public enum FastOrderStatus {

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
	FAIL("支付失败");
	
	private String text;

	FastOrderStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
