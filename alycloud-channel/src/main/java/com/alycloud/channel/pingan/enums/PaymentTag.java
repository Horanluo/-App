package com.alycloud.channel.pingan.enums;

/**
 * 付款方式标签
 * @author Moyq5
 * @date 2017年6月14日
 */
public enum PaymentTag {

	/**
	 * 现金支付
	 */
	CASH("Cash", "现金支付"),
	/**
	 * 支付宝支付（测试）
	 */
	ALIPAY_CS("AlipayCS", "支付宝支付（测试）"),
	/**
	 * 支付宝支付
	 */
	ALIPAY_PAZH("AlipayPAZH", "支付宝支付"),
	/**
	 * 支付宝支付
	 */
	ALIPAY_GY("AlipayGY", "支付宝支付"),
	/**
	 * 微信支付（测试）
	 */
	WEIXIN_BERL("WeixinBERL", "微信支付（测试）"),
	/**
	 * 微信支付
	 */
	WEIXIN("Weixin", "微信支付"),
	/**
	 * 微信支付
	 */
	WEIXIN_LFL("WeixinLFL", "微信支付"),
	/**
	 * 自定义支付
	 */
	DIY("Diy", "自定义支付");
	
	private String value;
	
	private String text;
	
	PaymentTag(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public String getText() {
		return text;
	}
}
