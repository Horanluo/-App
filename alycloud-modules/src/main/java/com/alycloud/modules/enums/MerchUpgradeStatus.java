package com.alycloud.modules.enums;

/**
 * 商户升级状态，
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年7月14日
 */
public enum MerchUpgradeStatus {

	/**
	 * 待审核
	 */
	NEW("待审核"),
	/**
	 * 升级成功
	 */
	SUCCESS("升级成功"),
	/**
	 * 升级失败
	 */
	FAIL("升级失败");

	private String text;

	MerchUpgradeStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
