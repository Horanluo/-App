package com.alycloud.modules.enums;

/**
 * 付款方式，
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年5月11日
 */
public enum SysPayType {

	/**
	 * 支付宝
	 */
	ALIPAY("支付宝"),
	/**
	 * 微信
	 */
	WEIXIN("微信"),
	/**
	 * 百度钱包
	 */
	BAIDU("百度钱包"),
	/**
	 * QQ钱包
	 */
	QQ("QQ钱包"),
	/**
	 * 京东钱包
	 */
	JD("京东钱包"),
	/**
	 * 快捷支付
	 */
	FAST("快捷支付"),
	/**
	 * POS交易
	 */
	POS("POS交易");
	
	private String text;

	SysPayType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
