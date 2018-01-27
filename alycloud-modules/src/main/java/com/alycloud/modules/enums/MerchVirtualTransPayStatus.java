package com.alycloud.modules.enums;

/**
 * 商户虚拟账户提现订单状态
 * 
 * @author Moyq5
 * @date 2017年3月14日
 */
public enum MerchVirtualTransPayStatus {

	/**
	 * 未付款
	 */
	NEW("未付款"),
	/**
	 * 付款中
	 */
	PAYING("付款中"),
	/**
	 * 付款成功
	 */
	SUCCESS("付款成功"),
	/**
	 * 付款失败
	 */
	FAIL("付款失败"),
	/**
	 * 付款异常
	 */
	ERROR("付款异常");
	
	private String text;

	MerchVirtualTransPayStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
