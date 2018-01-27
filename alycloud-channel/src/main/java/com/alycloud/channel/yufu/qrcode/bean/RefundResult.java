package com.alycloud.channel.yufu.qrcode.bean;

import com.alycloud.channel.yufu.qrcode.enums.ChannelFlag;
import com.alycloud.channel.yufu.qrcode.jackson.converter.ChannelFlagDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 退款接口－响应参数
 * @author Moyq5
 * @date 2017年8月30日
 */
public class RefundResult extends AbstractResult {

	/**
	 * 退款金额，长12，非必填，<br>
	 * result为0000时返回单位（分）	 
	 */
	private String amount;
	/**
	 * 支付渠道，长2，非必填，<br>
	 * result为0000时返回
	 */
	@JsonDeserialize(converter = ChannelFlagDeserialize.class)
	private ChannelFlag channelFlag;
	/**
	 * 平台方唯一交易请求流水号，长20，非必填，<br>
	 * 平台方唯一交易请求流水号（原消费请求）
	 */
	private String orgTransId;
	/**
	 * 平台方唯一交易请求流水号，长20，非必填，<br>
	 */
	private String transId;
	/**
	 * 外部订单号，长32，非必填，<br>
	 * result为0000时返回
	 */
	@JsonProperty("out_refund_no")
	private String outRefundNo;
	/**
	 * 商户订单号，长30，非必填，<br>
	 */
	private String orderNo;
	/**
	 * 币种，长20，非必填，<br>
	 * 默认 CNY
	 */
	private String currency;
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
	public String getOrgTransId() {
		return orgTransId;
	}
	public void setOrgTransId(String orgTransId) {
		this.orgTransId = orgTransId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getOutRefundNo() {
		return outRefundNo;
	}
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "RefundResult [amount=" + amount + ", channelFlag=" + channelFlag + ", orgTransId=" + orgTransId
				+ ", transId=" + transId + ", outRefundNo=" + outRefundNo + ", orderNo=" + orderNo + ", currency="
				+ currency + ", toString()=" + super.toString() + "]";
	}
	
}
