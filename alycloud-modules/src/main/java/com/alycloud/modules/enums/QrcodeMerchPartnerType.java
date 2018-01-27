package com.alycloud.modules.enums;

/**
 * 二维码渠道商户模式<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年11月16日
 */
public enum QrcodeMerchPartnerType {

	/**
	 * 大商户
	 */
	BIGGER("大商户"),
	/**
	 * 进件商户
	 */
	REGISTERED("进件商户");
	
	private String text;

	QrcodeMerchPartnerType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
