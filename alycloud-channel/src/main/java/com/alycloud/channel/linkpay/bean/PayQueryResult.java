package com.alycloud.channel.linkpay.bean;

import com.alycloud.channel.linkpay.enums.BankCardType;
import com.alycloud.channel.linkpay.enums.PayState;
import com.alycloud.channel.linkpay.jackson.converter.PayStateDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 支付订单状态查询接口－响应参数
 * @author Moyq5
 * @date 2017年4月10日
 */
public class PayQueryResult {

	/**
	 * 合作商订单号，必填
	 */
	@JsonProperty("orderNum")
	private String customOrderNum;
	/**
	 * 平台订单号，必填
	 */
	@JsonProperty("pl_orderNum")
	private String orderNum;
	/**
	 * 交易状态，必填
	 */
	@JsonDeserialize(converter = PayStateDeserialize.class)
	@JsonProperty("pl_payState")
	private PayState payState;
	/**
	 * 交易说明，必填
	 */
	@JsonProperty("pl_payMessage")
	private String payMessage;
	/**
	 * 银行卡类型
	 */
	@JsonProperty("pl_bankCardType")
	private BankCardType bankCardType;
	
	public String getCustomOrderNum() {
		return customOrderNum;
	}
	public void setCustomOrderNum(String customOrderNum) {
		this.customOrderNum = customOrderNum;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public PayState getPayState() {
		return payState;
	}
	public void setPayState(PayState payState) {
		this.payState = payState;
	}
	public String getPayMessage() {
		return payMessage;
	}
	public void setPayMessage(String payMessage) {
		this.payMessage = payMessage;
	}
	public BankCardType getBankCardType() {
		return bankCardType;
	}
	public void setBankCardType(BankCardType bankCardType) {
		this.bankCardType = bankCardType;
	}
	@Override
	public String toString() {
		return "PayQueryResult [customOrderNum=" + customOrderNum + ", orderNum=" + orderNum + ", payState=" + payState
				+ ", payMessage=" + payMessage + ", bankCardType=" + bankCardType + "]";
	}
	
}
