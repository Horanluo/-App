package com.alycloud.modules.enums;

/**
 * 商户类别<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年6月6日
 */
public enum MerchMerchType {

	/**
	 * MPOS
	 */
	MPOS("MPOS"),
	/**
	 * 传统POS
	 */
	POS("传统POS"),
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
	QUICK("快捷支付");

	private String text;

	MerchMerchType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
