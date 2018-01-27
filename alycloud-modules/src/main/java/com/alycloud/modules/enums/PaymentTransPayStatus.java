package com.alycloud.modules.enums;

/**
 * 代付业务类型
 * 
 * @author Moyq5
 * @date 2017年10月24日
 */
public enum PaymentTransPayStatus {

	/**
	 * 待出款
	 */
	NEW("待出款"),
	/**
	 * 出款中
	 */
	PAYING("出款中"),
	/**
	 * 出款成功
	 */
	SUCCESS("出款成功"),
	/**
	 * 出款失败
	 */
	FAIL("出款失败"),
	/**
	 * 出款异常
	 */
	ERROR("出款异常");

	private String text;

	PaymentTransPayStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
