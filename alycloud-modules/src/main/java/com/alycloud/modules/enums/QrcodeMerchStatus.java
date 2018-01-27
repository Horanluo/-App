package com.alycloud.modules.enums;

/**
 * 二维码渠道商户启用状态<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年9月8日
 */
public enum QrcodeMerchStatus {

	/**
	 * 未定义（历史原因）
	 */
	UNDEFINED("未定义"),
	/**
	 * 启用
	 */
	ON("启用"),
	/**
	 * 禁用
	 */
	OFF("禁用");
	
	private String text;

	QrcodeMerchStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
