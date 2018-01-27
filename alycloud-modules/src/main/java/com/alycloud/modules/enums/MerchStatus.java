package com.alycloud.modules.enums;

/**
 * 商户状态
 * @author Moyq5
 * @date 2017年6月6日
 */
public enum MerchStatus {

	/**
	 * 未开通
	 */
	NOT_OPENED(1, "未开通"),
	/**
	 * 已开通
	 */
	OPENED(2, "已开通");

	private Integer value;
	private String text;

	MerchStatus(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public Integer getValue() {
		return value;
	}
}
