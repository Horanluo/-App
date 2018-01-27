package com.alycloud.modules.enums;

/**
 * 商户虚拟账户提现订单状态
 * 
 * @author Moyq5
 * @date 2017年3月14日
 */
public enum MerchVirtualTransStatus {

	/**
	 * 处理中
	 */
	LOCKING("处理中"),
	/**
	 * 交易成功
	 */
	SUCCESS("交易成功"),
	/**
	 * 交易失败
	 */
	FAIL("交易失败"),
	/**
	 * 交易异常
	 */
	ERROR("交易异常");
	
	private String text;

	MerchVirtualTransStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
