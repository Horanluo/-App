package com.alycloud.channel.yufu.qrcode.bean;

import com.alycloud.channel.yufu.qrcode.enums.ChannelFlag;
import com.alycloud.channel.yufu.qrcode.jackson.converter.ChannelFlagDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 扫码收款（结果同步返回）接口－响应参数
 * @author Moyq5
 * @date 2017年8月29日
 */
public class ScanSyncOrderResult extends AbstractResult {

	/**
	 * 平台方唯一交易请求流水号，长20，非必填，<br>
	 */
	private String transId;
	/**
	 * 请求交易的流水号，长32，非必填，<br>
	 * 请求交易的流水号（回传）
	 */
	private String reqId;
	/**
	 * 商户订单号，长30，非必填，<br>
	 * 商户订单号（回传）
	 */
	private String orderNo;
	/**
	 * 渠道订单号，长32，非必填，<br>
	 */
	private String outOrderNo;
	/**
	 * 支付渠道，长2，非必填，<br>
	 */
	@JsonDeserialize(converter = ChannelFlagDeserialize.class)
	private ChannelFlag channelFlag;
	/**
	 * 消费者账号，非必填，<br>
	 * 【支付宝】消费交易可返回
	 */
	private String buyerId;
	/**
	 * 币种，长20，非必填，<br>
	 * 默认 CNY
	 */
	private String currency;
	/**
	 * 用户标识，非必填，<br>
	 * 微信支付下的openid,支付宝支付下的 userid
	 */
	private String uuid;
	/**
	 * 支付完成时间，长24，非必填，<br>
	 * yyyy-MM-dd HH:mm:ss
	 */
	private String payTime;
	/**
	 * 订单金额，非必填，<br>
	 * 支付宝交易：totalAmount订单金额（单位元）
	 */
	private String totalAmount;
	/**
	 * 实收金额，非必填，<br>
	 * 实收金额（单位元）
	 */
	private String receiptAmount;
	/**
	 * 开票金额，非必填，<br>
	 * 开票金额（单位元）
	 */
	private String invoiceAmount;
	/**
	 * 付款金额，非必填，<br>
	 * 付款金额（单位元）
	 */
	private String buyerPayAmount;
	/**
	 * 付款银行，长8，非必填，<br>
	 */
	private String bankType;
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOutOrderNo() {
		return outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public ChannelFlag getChannelFlag() {
		return channelFlag;
	}
	public void setChannelFlag(ChannelFlag channelFlag) {
		this.channelFlag = channelFlag;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getBuyerPayAmount() {
		return buyerPayAmount;
	}
	public void setBuyerPayAmount(String buyerPayAmount) {
		this.buyerPayAmount = buyerPayAmount;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	@Override
	public String toString() {
		return "ScanSyncOrderResult [transId=" + transId + ", reqId=" + reqId + ", orderNo=" + orderNo + ", outOrderNo="
				+ outOrderNo + ", channelFlag=" + channelFlag + ", buyerId=" + buyerId + ", currency=" + currency
				+ ", uuid=" + uuid + ", payTime=" + payTime + ", totalAmount=" + totalAmount + ", receiptAmount="
				+ receiptAmount + ", invoiceAmount=" + invoiceAmount + ", buyerPayAmount=" + buyerPayAmount
				+ ", bankType=" + bankType + ", toString()=" + super.toString() + "]";
	}
	
}
