package com.alycloud.channel.yufu.merch.enums;

/**
 * 商户状态
 * @author Moyq5
 * @date 2017年8月1日
 */
public enum MerchState {

	/**
	 * 待审核
	 */
	NEW("待审核"),
	/**
	 * 通过
	 */
	PASS("通过"),
	/**
	 * 拒绝
	 */
	REJECTIVE("拒绝");
	
	private String text;
	
	MerchState(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
