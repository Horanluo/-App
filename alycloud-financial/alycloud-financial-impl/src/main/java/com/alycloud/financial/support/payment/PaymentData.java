package com.alycloud.financial.support.payment;

import java.math.BigDecimal;

public class PaymentData {

	private String bankAccount; // 账户名
	private String bankCardNo; // 银行卡号
	private String bankName; // 银行名称
	private String bankNo; // 联行号
	private String orderNo; // 订单号
	private BigDecimal money;// 交易金额（元）**重要****重要****重要****重要****重要****重要**
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
}
