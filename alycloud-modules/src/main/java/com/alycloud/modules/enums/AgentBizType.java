package com.alycloud.modules.enums;

/**
 * 代理商业务类型
 * @author Moyq5
 * @date 2017年5月19日
 */
public enum AgentBizType {

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
	QUICK("快捷支付");

	private String text;

	AgentBizType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
