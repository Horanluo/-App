package com.alycloud.channel.yufu.enums;

/**
 * 支付类型
 * @author Moyq5
 * @date 2017年8月2日
 */
public enum PayType {

	/**
	 * 扫码
	 */
	CODE("扫码"),
	/**
	 * 银行卡快捷支付
	 */
	FAST("银行卡快捷支付");
	
	private String text;
	
	PayType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
