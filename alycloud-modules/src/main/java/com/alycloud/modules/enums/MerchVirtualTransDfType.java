package com.alycloud.modules.enums;

/**
 * 商户虚拟账户提现类型
 * 
 * @author Moyq5
 * @date 2017年3月17日
 */
public enum MerchVirtualTransDfType {

	/**
	 * T0
	 */
	T0("T0"),
	/**
	 * T1
	 */
	T1("T1");
	
	private String text;

	MerchVirtualTransDfType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
