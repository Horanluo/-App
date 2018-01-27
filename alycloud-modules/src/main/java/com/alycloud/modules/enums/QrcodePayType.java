package com.alycloud.modules.enums;

/**
 * 二维码付款方式，枚举顺序不可变
 * 
 * @author Moyq5
 * @date 2017年4月27日
 */
public enum QrcodePayType {

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
	JD("京东支付"),
	/**
	 * 快捷支付
	 */
	FAST("快捷支付");
	
	private String text;

	QrcodePayType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
