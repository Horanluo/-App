package com.alycloud.modules.enums;

/**
 * 商户业务类型
 * @author Moyq5
 * @date 2017年5月24日
 */
public enum MerchBizType {

	/**
	 * T+0
	 */
	T0("T+0"),
	/**
	 * 一机多费率
	 */
	MULTI_RATE("一机多费率"),
	/**
	 * 传统POS
	 */
	POS("传统POS"),
	/**
	 * 手机APP
	 */
	APP("手机APP"),
	/**
	 * 二维码支付
	 */
	QRCODE("二维码支付"),
	/**
	 * 网关支付
	 */
	GATEWAY("网关支付"),
	/**
	 * 快捷支付
	 */
	FAST("快捷支付");

	private String text;

	MerchBizType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
