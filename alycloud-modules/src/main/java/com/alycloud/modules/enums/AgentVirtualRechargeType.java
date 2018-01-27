package com.alycloud.modules.enums;

/**
 * 代理商虚拟账户充值类型
 * 
 * @author Moyq5
 * @date 2017年3月19日
 */
public enum AgentVirtualRechargeType {

	/**
	 * 分润充值
	 */
	AGENT_TRANS("分润充值");
	
	private String text;

	AgentVirtualRechargeType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
