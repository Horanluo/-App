package com.alycloud.channel.yufu.enums;

/**
 * 支付渠道
 * @author Moyq5
 * @date 2017年8月2日
 */
public enum PayChannel {

	/**
	 * 微信
	 */
	WEIXIN("00","微信"),
	/**
	 * 支付宝
	 */
	ALIPAY("01","支付宝"),
	/**
	 * 百度支付包
	 */
	BAIDU("02","百度支付包"),
	/**
	 * 翼支付
	 */
	BESTPAY("03","翼支付"),
	/**
	 * QQ钱包
	 */
	QQ("04","QQ钱包"),
	/**
	 * 快捷支付
	 */
	FAST("99","快捷支付");
	
	private String text;
	private String value;
	PayChannel(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}
}
