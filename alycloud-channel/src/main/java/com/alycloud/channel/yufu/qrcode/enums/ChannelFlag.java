package com.alycloud.channel.yufu.qrcode.enums;

/**
 * 支付渠道
 * @author Moyq5
 * @date 2017年8月29日
 */
public enum ChannelFlag {

	/**
	 * 微信
	 */
	WEIXIN("微信"),
	/**
	 * 支付宝
	 */
	ALIPAY("支付宝"),
	/**
	 * 百付包
	 */
	BAIDU("百付包"),
	/**
	 * 翼支付
	 */
	BESTPAY("翼支付"),
	/**
	 * QQ
	 */
	QQ("QQ"),
	/**
	 * 京东
	 */
	JD("京东");
	
	private String text;
	
	ChannelFlag(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
