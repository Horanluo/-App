package com.alycloud.modules.enums;

/**
 * 二维码订单付款状态
 * 
 * @author Moyq5
 * @date 2017年3月14日
 */
public enum QrcodeTransPayStatus {

	/**
	 * 处理中
	 */
	PAYING("处理中"),
	/**
	 * 付款成功
	 */
	SUCCESS("付款成功"),
	/**
	 * 付款失败
	 */
	FAIL("付款失败");
	
	private String text;

	QrcodeTransPayStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
