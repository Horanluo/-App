package com.alycloud.modules.enums;

/**
 * 商户（基本资料）初审状态，枚举顺序不可变
 * @author Moyq5
 * @date 2017年5月24日
 */
public enum MerchAuditStatus {

	/**
	 * 资料未填写
	 */
	UNCOMMITTED("资料未填写"),
	/**
	 * 待审核
	 */
	UNAUDITED("待审核"),
	/**
	 * 审核拒绝
	 */
	REJECTIVE("审核拒绝"),
	/**
	 * 审核通过
	 */
	AUDITED("审核通过");

	private String text;

	MerchAuditStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
