package com.alycloud.modules.enums;

/**
 * 代理商分润类型
 * 
 * @author Moyq5
 * @date 2017年11月12日
 */
public enum AgentTransType {

	/**
	 * 普通分润
	 */
	COMMON("普通分润"),
	/**
	 * 升级分润
	 */
	UPGRADE("升级分润");

	private String text;

	AgentTransType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
