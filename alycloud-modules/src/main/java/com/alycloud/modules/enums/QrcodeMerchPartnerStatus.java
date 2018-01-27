package com.alycloud.modules.enums;

/**
 * 二维码渠道商户审核状态<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年9月8日
 */
public enum QrcodeMerchPartnerStatus {

	/**
	 * 待审核
	 */
	NEW("待审核"),
	/**
	 * 审核通过
	 */
	SUCCESS("审核通过"),
	/**
	 * 审核失败
	 */
	ADD_FAIL("审核失败"),
	/**
	 * 同步失败
	 */
	SYNC_FAIL("同步失败");
	
	private String text;

	QrcodeMerchPartnerStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
