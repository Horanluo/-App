package com.alycloud.channel.esyto.enums;

/**
 * 订单支付状态;
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年6月8日
 */
public enum OrderStatus {

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

	OrderStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
