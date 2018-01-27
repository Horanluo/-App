package com.alycloud.modules.enums;

/**
 * 代理商审核状态
 * 
 * @author Moyq5
 * @date 2017年3月29日
 */
public enum AgentAuditStatus {

	/**
	 * 待审核
	 */
	UNAUDITED(1, "待审核"),
	/**
	 * 审核拒绝
	 */
	REJECTIVE(2, "审核拒绝"),
	/**
	 * 审核通过
	 */
	AUDITED(3, "审核通过");

	private Integer value;
	private String text;

	AgentAuditStatus(Integer value, String text) {
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
