package com.alycloud.modules.enums;

/**
 * 代理商状态
 * 
 * @author Moyq5
 * @date 2017年3月29日
 */
public enum AgentStatus {

	/**
	 * 未开通
	 */
	UNOPENED(1, "未开通"),
	/**
	 * 已开通
	 */
	OPENED(2, "已开通");

	private Integer value;
	private String text;

	AgentStatus(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public Integer getValue() {
		return value;
	}
}
