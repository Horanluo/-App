package com.alycloud.channel.yufu.qrcode.bean;

import com.alycloud.channel.yufu.qrcode.enums.ChannelFlag;

/**
 * 扫码收款（结果异步通知）接口－响应参数
 * @author Moyq5
 * @date 2017年9月12日
 */
public class NotifyCallbackResult extends AbstractResult {

	/**
	 * 通知时间，长14，必填，<br>
	 * 20161117153333
	 */
	private String notifyTime;
	/**
	 * 商户号，长20，必填，<br>
	 */
	private String merchNo;
	/**
	 * 终端号，长200，非必填，<br>
	 */
	private String termNo;
	/**
	 * 平台方唯一交易请求流水号，长20，必填，<br>
	 */
	private String transId;
	/**
	 * 请求交易的流水号，长32，必填，<br>
	 * 请求交易的流水号（回传）
	 */
	private String reqId;
	/**
	 * 商户订单号，长30，必填，<br>
	 * 商户订单号（回传）
	 */
	private String orderNo;
	/**
	 * 金额，长12，必填，<br>
	 * 单位为分
	 */
	private String amount;
	/**
	 * 金额，长12，必填，<br>
	 * 单位为分
	 */
	private String outOrderNo;
	/**
	 * 支付渠道，长2，非必填，<br>
	 */
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
	 * 付款银行，长8，非必填，<br>
	 */
	private String bankType;
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
	 * 商品名，非必填，<br>
	 */
	private String goodsName;
	/**
	 * 附加信息，长2048，非必填，<br>
	 */
	private String extraDesc;
	public String getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getMerchNo() {
		return merchNo;
	}
	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}
	public String getTermNo() {
		return termNo;
	}
	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getExtraDesc() {
		return extraDesc;
	}
	public void setExtraDesc(String extraDesc) {
		this.extraDesc = extraDesc;
	}
	@Override
	public String toString() {
		return "NotifyCallbackResult [notifyTime=" + notifyTime + ", merchNo=" + merchNo + ", termNo=" + termNo
				+ ", transId=" + transId + ", reqId=" + reqId + ", orderNo=" + orderNo + ", amount=" + amount
				+ ", outOrderNo=" + outOrderNo + ", channelFlag=" + channelFlag + ", buyerId=" + buyerId + ", currency="
				+ currency + ", uuid=" + uuid + ", bankType=" + bankType + ", payTime=" + payTime + ", totalAmount="
				+ totalAmount + ", receiptAmount=" + receiptAmount + ", invoiceAmount=" + invoiceAmount
				+ ", buyerPayAmount=" + buyerPayAmount + ", goodsName=" + goodsName + ", extraDesc=" + extraDesc
				+ ", toString()=" + super.toString() + "]";
	}
	
}
