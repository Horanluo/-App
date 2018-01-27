package com.alycloud.channel.esyto.enums;

/**
 * 支付方式，枚举顺序不可变
 * 
 * @author Moyq5
 * @date 2017年4月27日
 */
public enum PayType {

	/**
	 * 银联支付
	 */
	UNIONPAY("银联支付"),
	/**
	 * 支付宝
	 */
	ALIPAY("支付宝支付"),
	/**
	 * 微信支付
	 */
	WEIXIN("微信支付"),
	/**
	 * 百度支付
	 */
	BAIDU("百度支付"),
	/**
	 * QQ支付
	 */
	QQ("QQ支付"),
	/**
	 * 京东支付
	 */
	JD("京东支付");
	
	private String text;

	PayType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
