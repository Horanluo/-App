package com.alycloud.modules.enums;

/**
 * 渠道商户费率审核状态
 * @author Moyq5
 * @date 2017年11月16日
 */
public enum QrcodeChannelMerchFeeStatus {
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

	QrcodeChannelMerchFeeStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
