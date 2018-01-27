package com.alycloud.modules.enums;

/**
 * 代理商管理员状态
 * 
 * @author Moyq5
 * @date 2017年3月28日
 */
public enum AgentUserStatus {

	/**
	 * 未知
	 */
	UNKNOWN("未知"),
	/**
	 * 正常
	 */
	NORMAL("正常"),
	/**
	 * 冻结
	 */
	LOCKED("冻结"),
	/**
	 * 锁定
	 */
	CANCEL("锁定");

	private String text;

	AgentUserStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
