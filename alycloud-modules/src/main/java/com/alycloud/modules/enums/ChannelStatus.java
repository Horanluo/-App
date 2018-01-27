package com.alycloud.modules.enums;

/**
 * 二维码渠道状态<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年9月28日
 */
public enum ChannelStatus {

	/**
	 * 未知
	 */
	UNKNOWN("未知"),
	/**
	 * 启用
	 */
	ON("启用"),
	/**
	 * 禁用
	 */
	OFF("禁用");
	
	private String text;

	ChannelStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
