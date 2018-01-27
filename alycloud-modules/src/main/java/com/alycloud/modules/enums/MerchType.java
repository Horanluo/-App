package com.alycloud.modules.enums;

/**
 * 商户类型，
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年8月21日
 */
public enum MerchType {

	/**
	 * 个人
	 */
	PERSONAL("个人"),
	/**
	 * 企业
	 */
	CORPORATE("企业");
	
	private String text;

	MerchType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
