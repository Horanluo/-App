package com.alycloud.modules.enums;

/**
 * 二维码支付扫码类型<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年6月6日
 */
public enum QrcodeScanType {

	/**
	 * 主扫
	 */
	ACTIVE("主扫"),
	/**
	 * 被扫
	 */
	PASSIVE("被扫"),
	/**
	 * 公众号
	 */
	OFFICAL("公众号"),
	/**
	 * WAP
	 */
	WAP("WAP"),
	/**
	 * APP
	 */
	APP("APP"),
	/**
	 * 快捷
	 */
	FAST("快捷");
	
	private String text;

	QrcodeScanType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
