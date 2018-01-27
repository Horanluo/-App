package com.alycloud.modules.enums;

/**
 * 升级支付订单状态;
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年10月31日
 */
public enum GradeOrderStatus {

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

	GradeOrderStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
