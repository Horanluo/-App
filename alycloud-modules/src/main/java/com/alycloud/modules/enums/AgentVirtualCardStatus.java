package com.alycloud.modules.enums;

/**
 * 代理商虚拟账户状态
 * 
 * @author Moyq5
 * @date 2017年3月12日
 */
public enum AgentVirtualCardStatus {

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

	AgentVirtualCardStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
