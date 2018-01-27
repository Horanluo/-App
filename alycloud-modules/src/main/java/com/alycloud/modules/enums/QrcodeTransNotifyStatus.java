package com.alycloud.modules.enums;

/**
 * （公众号）通知状态
 * 
 * @author Moyq5
 * @date 2017年4月6日
 */
public enum QrcodeTransNotifyStatus {

	/**
	 * 未通知
	 */
	NEED("未通知"),
	/**
	 * 不需要通知
	 */
	NEEDLESS("不需要通知"),
	/**
	 * 已通知
	 */
	NOTIFIED("已通知"),
	/**
	 * 通知失败
	 */
	ERROR("通知失败");
	
	private String text;

	QrcodeTransNotifyStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
