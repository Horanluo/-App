package com.alycloud.channel.esyto.enums;

/**
 * 交易结果
 * @author Moyq5
 * @date 2017年8月2日
 */
public enum RespCode {

	/**
	 * 成功
	 */
	SUCCESS("成功"),
	/**
	 * 处理中
	 */
	FAIL("失败");
	
	private String value;
	private String text;
	
	RespCode(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
