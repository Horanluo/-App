package com.alycloud.modules.enums;

/**
 * D0费率类型<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年8月15日
 */
public enum SysT0RateType {

	/**
	 * 全量费率
	 */
	FULL("全量费率"),
	/**
	 * 增量费率
	 */
	INCREMENT("增量费率");
	
	private String text;

	SysT0RateType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
