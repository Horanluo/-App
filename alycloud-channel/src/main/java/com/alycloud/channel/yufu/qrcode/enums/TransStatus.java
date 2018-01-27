package com.alycloud.channel.yufu.qrcode.enums;

/**
 * 订单状态
 * @author Moyq5
 * @date 2017年8月29日
 */
public enum TransStatus {

	/**
	 * 成功
	 */
	SUCCESS("成功"),
	/**
	 * 失败
	 */
	FAIL("失败"),
	/**
	 * 未知
	 */
	UNKNOWN("未知"),
	/**
	 * 已撤销
	 */
	INVOKE("已撤销"),
	/**
	 * 已关闭（视同失败）
	 */
	CLOSED("已关闭（视同失败）"),
	/**
	 * 有退货（之前消费成功）
	 */
	REFUNED("有退货（之前消费成功）");
	
	private String text;
	
	TransStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
