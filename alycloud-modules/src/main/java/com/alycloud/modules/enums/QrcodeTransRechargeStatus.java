package com.alycloud.modules.enums;

/**
 * 二维码订单充值状态
 * 
 * @author Moyq5
 * @date 2017年3月14日
 */
public enum QrcodeTransRechargeStatus {

	/**
	 * 未知
	 */
	UNKNOW("未知"),
	/**
	 * 无需充值
	 */
	NOT_RECHARGE("无需充值"),
	/**
	 * 充值成功
	 */
	SUCCESS("充值成功"),
	/**
	 * 充值失败
	 */
	FAIL("充值失败");
	
	private String text;

	QrcodeTransRechargeStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
