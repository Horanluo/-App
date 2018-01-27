package com.alycloud.modules.enums;

/**
 * 商户经营资质资料审核状态
 * @author Moyq5
 * @date 2017年9月20日
 */
public enum ChangeMerchQualificationAuditStatus {
	/**
	 * 待审核
	 */
	UNAUDITED("待审核"),
	/**
	 * 审核中
	 */
	AUDITING("审核中"),
	/**
	 * 审核通过
	 */
	AUDITED("审核通过"),
	/**
	 * 审核失败
	 */
	REJECTIVE("审核失败");

	private String text;

	ChangeMerchQualificationAuditStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
