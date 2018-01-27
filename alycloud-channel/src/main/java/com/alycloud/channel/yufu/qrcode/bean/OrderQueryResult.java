package com.alycloud.channel.yufu.qrcode.bean;

import com.alycloud.channel.yufu.qrcode.enums.ChannelFlag;
import com.alycloud.channel.yufu.qrcode.enums.TransStatus;
import com.alycloud.channel.yufu.qrcode.enums.TransType;
import com.alycloud.channel.yufu.qrcode.jackson.converter.ChannelFlagDeserialize;
import com.alycloud.channel.yufu.qrcode.jackson.converter.TransStatusDeserialize;
import com.alycloud.channel.yufu.qrcode.jackson.converter.TransTypeDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 查询（包括消费、撤销、退款）接口－响应参数
 * @author Moyq5
 * @date 2017年8月29日
 */
public class OrderQueryResult extends AbstractResult {

	/**
	 * 交易金额，长12，非必填，<br>
	 * 单位为分
	 */
	private String amount;
	/**
	 * 支付渠道，长2，必填，<br>
	 */
	@JsonDeserialize(converter = ChannelFlagDeserialize.class)
	private ChannelFlag channelFlag;
	/**
	 * 商户订单号，长30，非必填，<br>
	 * 商户订单号（回传）
	 */
	private String orderNo;
	/**
	 * 平台方流水号，长32，非必填，<br>
	 */
	private String orgTransId;
	/**
	 * 交易的流水号，长30，非必填，<br>
	 * 商户订单号请求交易的流水号
	 */
	private String orgReqId;
	/**
	 * 渠道订单号，长32，非必填，<br>
	 * 当result返回0000时
	 */
	private String outOrderNo;
	/**
	 * 交易类型，长1，非必填，<br>
	 */
	@JsonDeserialize(converter = TransTypeDeserialize.class)
	private TransType transType;
	/**
	 * 订单状态，长1，非必填，<br>
	 */
	@JsonDeserialize(converter = TransStatusDeserialize.class)
	private TransStatus transStatus;
	/**
	 * 操作员编号，长30，非必填，<br>
	 */
	private String operatorId;
	/**
	 * 币种，长30，非必填，<br>
	 */
	private String currency;
	/**
	 * 消费者账号，非必填，<br>
	 * 支付宝消费交易可返回
	 */
	private String buyerId;
	/**
	 * 用户标识，非必填，<br>
	 * 微信支付下的 openid, 支付宝支付下的 userid
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
	 * 商品名，长30，非必填，<br>
	 * 付款金额（单位元）
	 */
	private String goodsName;
	/**
	 * 附加信息，长2048，非必填，<br>
	 */
	private String extraDesc;
	/**
	 * 付款银行，长8，非必填，<br>
	 */
	private String bankType;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public ChannelFlag getChannelFlag() {
		return channelFlag;
	}
	public void setChannelFlag(ChannelFlag channelFlag) {
		this.channelFlag = channelFlag;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrgTransId() {
		return orgTransId;
	}
	public void setOrgTransId(String orgTransId) {
		this.orgTransId = orgTransId;
	}
	public String getOrgReqId() {
		return orgReqId;
	}
	public void setOrgReqId(String orgReqId) {
		this.orgReqId = orgReqId;
	}
	public String getOutOrderNo() {
		return outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public TransType getTransType() {
		return transType;
	}
	public void setTransType(TransType transType) {
		this.transType = transType;
	}
	public TransStatus getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(TransStatus transStatus) {
		this.transStatus = transStatus;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
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
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	@Override
	public String toString() {
		return "OrderQueryResult [amount=" + amount + ", channelFlag=" + channelFlag + ", orderNo=" + orderNo
				+ ", orgTransId=" + orgTransId + ", orgReqId=" + orgReqId + ", outOrderNo=" + outOrderNo
				+ ", transType=" + transType + ", transStatus=" + transStatus + ", operatorId=" + operatorId
				+ ", currency=" + currency + ", buyerId=" + buyerId + ", uuid=" + uuid + ", payTime=" + payTime
				+ ", totalAmount=" + totalAmount + ", receiptAmount=" + receiptAmount + ", invoiceAmount="
				+ invoiceAmount + ", buyerPayAmount=" + buyerPayAmount + ", goodsName=" + goodsName + ", extraDesc="
				+ extraDesc + ", bankType=" + bankType + ", toString()=" + super.toString() + "]";
	}
	
}
