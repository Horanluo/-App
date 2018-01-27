package com.alycloud.modules.enums;

/**
 * 商户虚拟账户状态
 * 
 * @author Moyq5
 * @date 2017年3月15日
 */
public enum MerchVirtualCardStatus {

	/**
	 * 正常
	 */
	NORMAL("正常"),
	/**
	 * 已冻结
	 */
	LOCKED("已冻结"),
	/**
	 * 已销户
	 */
	DELETE("已销户");
	
	private String text;

	MerchVirtualCardStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
