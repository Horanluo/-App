package com.alycloud.channel.linkpay.enums;

/**
 * 江苏电子代付接口－订单代付状态码<br>
 * 注意，枚举顺序不可变动
 * @author Moyq5
 * @date 2017年4月10日
 */
public enum TransState {

	/**
	 * 代付成功
	 */
	TRANS_SUCCESS(1, "代付成功"),
	/**
	 * 代付失败
	 */
	TRANS_FAIL(2, "代付失败"),
	/**
	 * 代付结果未明
	 */
	TRANS_UNKNOWN(3, "代付结果未明");
	
	private Integer value;
	private String text;
	
	TransState(Integer value, String text) {
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
