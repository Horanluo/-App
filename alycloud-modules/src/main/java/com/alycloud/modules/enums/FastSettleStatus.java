package com.alycloud.modules.enums;

/**
 * 快捷支付结算状态
 * @author Moyq5
 * @date 2017年4月21日
 */
public enum FastSettleStatus {

	/**
	 * 未结算
	 */
	NEW("未结算"),
	/**
	 * 结算成功
	 */
	SUCCESS("结算成功"),
	/**
	 * 结算失败
	 */
	FAIL("结算失败");
	
	private String text;

	FastSettleStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
