package com.alycloud.modules.enums;

/**
 * 出款方式
 * 
 * @author Moyq5
 * @date 2017年10月25日
 */
public enum PaymentTransPayType {

	/**
	 * 人工操作，即由人工线下已确认打款后更新出款状态
	 */
	MANUAL("人工操作"),
	/**
	 * 系统自动出款（如调渠道接口），即由系统自动任务去变更出款状态
	 */
	AUTOMATIC("系统自动");

	private String text;

	PaymentTransPayType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
