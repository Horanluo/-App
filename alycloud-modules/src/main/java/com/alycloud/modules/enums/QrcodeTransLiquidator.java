package com.alycloud.modules.enums;

/**
 * 二维码订单清算方
 * 
 * @author Moyq5
 * @date 2017年3月14日
 */
public enum QrcodeTransLiquidator {

	/**
	 * 未知
	 */
	UNKNOW("未知"),
	/**
	 * 平台清算
	 */
	PLATFORM("平台清算"),
	/**
	 * 虚拟账户
	 */
	VIRTUAL("虚拟账户");
	
	private String text;

	QrcodeTransLiquidator(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
