package com.alycloud.modules.enums;

/**
 * 清算类型<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年8月19日
 */
public enum SysLiquidateType {

	/**
	 * 平台
	 */
	PLATFORM("平台"),
	/**
	 * 渠道
	 */
	CHANNEL("渠道");
	
	private String text;

	SysLiquidateType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
