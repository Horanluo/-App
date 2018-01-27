package com.alycloud.channel.payeco.enums;

/**
 * 易联支付－订单状态
 * @author Moyq5
 * @date 2017年4月11日
 */
public enum OrderStatus {

	/**
	 * 未知
	 */
	UNKNOWN("00","未知"),
	/**
	 * 未支付
	 */
	NEW("01","未支付"),
	/**
	 * 已支付
	 */
	PAID("02","已支付"),
	/**
	 * 已退款(全额撤销/冲正)
	 */
	REFUNDED("03","已退款(全额撤销/冲正)"),
	/**
	 * 已过期
	 */
	EXPIRED("04","已过期"),
	/**
	 * 已作废
	 */
	VOIDED("05","已作废"),
	/**
	 * 支付中
	 */
	PAYING("06","支付中"),
	/**
	 * 退款中
	 */
	REFUNDING("07","退款中"),
	/**
	 * 已被商户撤销
	 */
	UNDO_BY_MERCH("08","已被商户撤销"),
	/**
	 * 已被持卡人撤销
	 */
	UNDO_BY_USER("09","已被持卡人撤销"),
	/**
	 * 调账-支付成功
	 */
	ADJUST_PAID("10","调账-支付成功"),
	/**
	 * 调账-退款成功
	 */
	ADJUST_REFUNDED("11","调账-退款成功"),
	/**
	 * 已退货
	 */
	SALES_RETURNED("12","已退货");
	
	private String value;
	private String text;
	
	OrderStatus(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}
}
