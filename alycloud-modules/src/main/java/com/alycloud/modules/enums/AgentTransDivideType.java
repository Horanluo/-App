package com.alycloud.modules.enums;

/**
 * 
 * @author Moyq5
 * @date 2017年11月4日
 */
public enum AgentTransDivideType {

	/**
	 * 手续费分成
	 */
	FEE("手续费分成"),
	/**
	 * 交易金额分成
	 */
	TRANS_AMOUNT("交易金额分成"),
	/**
	 * 结算金额分成
	 */
	SETTLE_AMOUNT("结算金额分成");

	private String text;

	AgentTransDivideType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
