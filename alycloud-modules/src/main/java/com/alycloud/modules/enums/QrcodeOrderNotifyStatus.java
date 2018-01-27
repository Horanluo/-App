package com.alycloud.modules.enums;

/**
 * 二维码支付订单通知状态;
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年10月8日
 */
public enum QrcodeOrderNotifyStatus {

	/**
	 * 未通知
	 */
	NEW("未通知"),
	/**
	 * 通知成功
	 */
	SUCCESS("通知成功"),
	/**
	 * 通知失败
	 */
	FAIL("通知失败");
	
	private String text;

	QrcodeOrderNotifyStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
