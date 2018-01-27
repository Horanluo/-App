package com.alycloud.modules.enums;

/**
 * 商户信息同步类型，
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年5月11日
 */
public enum MerchSyncType {

	/**
	 * 费率
	 */
	RATE("费率"),
	/**
	 * 结算账户
	 */
	ACCOUNT("结算账户");
	
	private String text;

	MerchSyncType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
