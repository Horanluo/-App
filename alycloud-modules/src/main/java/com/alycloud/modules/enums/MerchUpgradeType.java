package com.alycloud.modules.enums;

/**
 * 商户升级类型，
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年7月14日
 */
public enum MerchUpgradeType {

	/**
	 * 商户升级
	 */
	MERCH("商户升级"),
	/**
	 * 代理商升级
	 */
	AGENT("代理商升级");

	private String text;

	MerchUpgradeType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
