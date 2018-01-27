package com.alycloud.modules.enums;

/**
 * 结算方式<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年6月6日
 */
public enum SysSettleType {

	/**
	 * T0
	 */
	T0("T0"),
	/**
	 * T1
	 */
	T1("T1");
	
	private String text;

	SysSettleType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
