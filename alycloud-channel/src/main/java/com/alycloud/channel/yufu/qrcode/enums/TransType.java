package com.alycloud.channel.yufu.qrcode.enums;

/**
 * 交易类型
 * @author Moyq5
 * @date 2017年8月29日
 */
public enum TransType {

	/**
	 * 消费
	 */
	CONSUME("消费"),
	/**
	 * 撤销
	 */
	REVOKE("撤销"),
	/**
	 * 退货
	 */
	REFUND("退货");
	
	private String text;
	
	TransType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
