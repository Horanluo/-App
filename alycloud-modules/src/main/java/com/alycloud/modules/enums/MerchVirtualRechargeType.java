package com.alycloud.modules.enums;

/**
 * 商户虚拟账户充值类型
 * 
 * @author Moyq5
 * @date 2017年3月15日
 */
public enum MerchVirtualRechargeType {

	/**
	 * 二维码交易
	 */
	QRCODE_TRANS("二维码交易"),
	/**
	 * 快捷支付交易
	 */
	FAST_TRANS("快捷支付交易");
	
	private String text;

	MerchVirtualRechargeType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
