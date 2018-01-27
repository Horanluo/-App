package com.alycloud.modules.enums;

/**
 * 代理商类型
 * 
 * @author Moyq5
 * @date 2017年3月29日
 */
public enum AgentAgentType {

	/**
	 * 普通代理商
	 */
	COMMON_AGENT("普通代理商"),
	/**
	 * OEM代理商
	 */
	OEM_AGENT("OEM代理商"),
	/**
	 * 机构接入代理商
	 */
	BRANCH_AGENT("机构接入代理商");

	private String text;

	AgentAgentType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
