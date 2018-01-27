package com.alycloud.modules.enums;

/**
 * 商户操作员状态，
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年6月7日
 */
public enum MerchUserStatus {

	/**
	 * 正常
	 */
	NORMAL("正常"),
	/**
	 * 冻结
	 */
	FROZEN("冻结"),
	/**
	 * 锁定
	 */
	LOCKED("锁定");

	private String text;

	MerchUserStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
