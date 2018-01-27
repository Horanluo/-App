package com.alycloud.channel.yufu.merch.enums;

/**
 * 支付服务
 * @author Moyq5
 * @date 2017年7月31日
 */
public enum PayService {

	/**
	 * 微信
	 */
	WX("微信"),
	/**
	 * 手机QQ
	 */
	QQ("手机QQ"),
	/**
	 * 支付宝
	 */
	ZFB("支付宝"),
	/**
	 * 银行卡
	 */
	CARD("银行卡"),
	/**
	 * 快捷支付
	 */
	KJ("快捷支付"),
	/**
	 * 微信APP
	 */
	WXAPP("微信APP");
	
	private String text;
	
	PayService(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
