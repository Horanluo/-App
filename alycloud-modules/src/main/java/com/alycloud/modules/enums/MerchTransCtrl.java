package com.alycloud.modules.enums;

/**
 * 商户POS功能<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年6月6日
 */
public enum MerchTransCtrl {

	/**
	 * 消费
	 */
	CONSUME("消费"),
	/**
	 * 余额查询
	 */
	BALANCE_ENQUIRY("余额查询");

	private String text;

	MerchTransCtrl(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
