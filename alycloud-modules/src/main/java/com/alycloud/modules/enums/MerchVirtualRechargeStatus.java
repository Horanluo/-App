package com.alycloud.modules.enums;

/**
 * 商户虚拟账户充值订单状态
 * 
 * @author Moyq5
 * @date 2017年3月15日
 */
public enum MerchVirtualRechargeStatus {

	/**
	 * 新创建订单
	 */
	NEW("新创建订单"),
	/**
	 * 充值中
	 */
	CHARGING("充值中"),
	/**
	 * 充值成功
	 */
	SUCCESS("充值成功"),
	/**
	 * 充值失败
	 */
	FAIL("充值失败");
	
	private String text;

	MerchVirtualRechargeStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
