package com.alycloud.channel.linkpay.bean;

/**
 * 代付接口－请求参数
 * @author Moyq5
 * @date 2017年4月10日
 */
public class AgentPayData {

	/**
	 * 平台商户编号，必填
	 */
	private String merchantCode;
	/**
	 * 平台商户终端编号，必填
	 */
	private String terminalCode;
	/**
	 * 交易日期（YYYYMMDD），必填
	 */
	private String transDate;
	/**
	 * 交易时间（HH24mmss），必填
	 */
	private String transTime;
	/**
	 * 合作商订单号，全局唯一，必填
	 */
	private String orderNum;
	/**
	 * 收款人账户名，必填
	 */
	private String accountName;
	/**
	 * 收款人账户号，必填
	 */
	private String bankCard;
	/**
	 * 收款人账户开户行名称，必填
	 */
	private String bankName;
	/**
	 * 收款人账户开户行联行号，必填
	 */
	private String bankLinked;
	/**
	 * 交易金额，必填
	 */
	private String transMoney;
	
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankLinked() {
		return bankLinked;
	}
	public void setBankLinked(String bankLinked) {
		this.bankLinked = bankLinked;
	}
	public String getTransMoney() {
		return transMoney;
	}
	public void setTransMoney(String transMoney) {
		this.transMoney = transMoney;
	}
	@Override
	public String toString() {
		return "AgentPayData [merchantCode=" + merchantCode + ", terminalCode=" + terminalCode + ", transDate="
				+ transDate + ", transTime=" + transTime + ", orderNum=" + orderNum + ", accountName=" + accountName
				+ ", bankCard=" + bankCard + ", bankName=" + bankName + ", bankLinked=" + bankLinked + ", transMoney="
				+ transMoney + "]";
	}
}
