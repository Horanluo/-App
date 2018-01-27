package com.alycloud.modules.enums;

/**
 * 交易类型。注意：枚举顺序不可变
 * @author Moyq5
 * @date 2017年4月24日
 */
public enum SysTransType {

	/**
	 * 快捷支付交易
	 */
	FAST("快捷支付交易"),
	/**
	 * pos刷卡交易
	 */
	POS("pos刷卡交易"),
	/**
	 * 二维码交易
	 */
	QRCODE("二维码交易");
	
	private String text;

	SysTransType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
