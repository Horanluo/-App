package com.alycloud.modules.enums;

/**
 * 代理商类型
 * 
 * @author Moyq5
 * @date 2017年3月29日
 */
public enum PaymentTransBizType {

	/**
	 * 快捷交易
	 */
	FAST("快捷交易"),
	/**
	 * 银行卡交易
	 */
	POS("银行卡交易"),
	/**
	 * 二维码交易
	 */
	QRCODE("二维码交易"),
	/**
	 * 网关交易
	 */
	GATEWAY("网关交易"),
	/**
	 * 重出款
	 */
	REPAYMENT("重出款"),
	/**
	 * 商户提现
	 */
	MERCH_DRAW("商户提现"),
	/**
	 * 代理商提现
	 */
	AGENT_DRAW("代理商提现");

	private String text;

	PaymentTransBizType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
