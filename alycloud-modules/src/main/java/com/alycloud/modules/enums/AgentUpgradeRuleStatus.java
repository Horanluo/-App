package com.alycloud.modules.enums;

/**
 * 代理升级申请设置启用状态
 * @author Moyq5
 * @date 2017年3月23日
 */
public enum AgentUpgradeRuleStatus {

	/**
	 * 停用
	 */
	OFF("停用"),
	/**
	 * 启用
	 */
	ON("启用");
	
	private String text;

	AgentUpgradeRuleStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
