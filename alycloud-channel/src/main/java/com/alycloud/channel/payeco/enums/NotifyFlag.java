package com.alycloud.channel.payeco.enums;

/**
 * 易联支付－订单通知标志
 * @author Moyq5
 * @date 2017年4月12日
 */
public enum NotifyFlag {

	/**
	 * 成功才通知（02状态）
	 */
	ONLY_SUCCESS("成功才通知"),
	/**
	 * 全部通知（02、03、04、05状态）
	 */
	ALL("全部通知");
	
	private String text;
	
	NotifyFlag(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
