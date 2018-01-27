package com.alycloud.modules.enums;

/**
 * 二维码渠道计费方式<br>
 * 注意，枚举顺序不可变
 * @author Moyq5
 * @date 2017年9月28日
 */
public enum ChannelCalculateType {

	/**
	 * 四舍五入
	 */
	ROUND_HALF_UP("四舍五入"),
	/**
	 * 进一法
	 */
	ROUND_UP("进一法"),
	/**
	 * 去尾法
	 */
	ROUND_DOWN("去尾法");
	
	private String text;

	ChannelCalculateType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
