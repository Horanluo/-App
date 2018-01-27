package com.alycloud.modules.enums;

/**
 * 银行账户类型
 * @author Moyq5
 * @date 2017年5月19日
 */
public enum SysAccountType {

	/**
	 * 对私
	 */
	PRIVATE(1, "对私"),
	/**
	 * 对公
	 */
	PUBLIC(2, "对公");

	private Integer value;
	private String text;

	SysAccountType(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public Integer getValue() {
		return value;
	}
}
