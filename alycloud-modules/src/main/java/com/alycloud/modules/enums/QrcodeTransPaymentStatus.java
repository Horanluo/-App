package com.alycloud.modules.enums;

/**
 * 二维码订单代付状态
 * 
 * @author Moyq5
 * @date 2017年3月14日
 */
public enum QrcodeTransPaymentStatus {

	/**
	 * 未代付
	 */
	NEW("未代付"),
	/**
	 * 无需代付
	 */
	NEEDLESS("无需代付"),
	/**
	 * 代付成功
	 */
	PAID("代付成功"),
	/**
	 * 代付失败
	 */
	ERROR("代付失败");
	
	private String text;

	QrcodeTransPaymentStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
