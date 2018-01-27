package com.alycloud.modules.enums;

/**
 * 商户费率模式
 * @author Moyq5
 * @date 2017年5月24日
 */
public enum MerchRateMode {

	/**
	 * 固定费率
	 */
	SINGLE(1, "固定费率"),
	/**
	 * 一机多费率
	 */
	MULTIPLE(2, "一机多费率");

	private Integer value;
	private String text;

	MerchRateMode(Integer value, String text) {
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
