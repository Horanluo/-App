package com.alycloud.modules.enums;

/**
 * 商户（结算账户）终审状态，枚举顺序不可变
 * @author Moyq5
 * @date 2017年6月6日
 */
public enum MerchJudgStatus {

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

	MerchJudgStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
