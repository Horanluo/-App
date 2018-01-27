package com.alycloud.channel.yufu.bean;

import com.alycloud.channel.yufu.enums.OrderStatus;
import com.alycloud.channel.yufu.enums.PayChannel;
import com.alycloud.channel.yufu.jackson.converter.DateDeserializer;
import com.alycloud.channel.yufu.jackson.converter.OrderStatusDeserialize;
import com.alycloud.channel.yufu.jackson.converter.PayChannelDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 订单查询接口响应参数对象
 * @author Moyq5
 * @date 2017年8月2日
 */
public class OrderQueryResult extends AbstractResult {
	
	/**
	 * 订单流水号，必填
	 */
	@JsonProperty("order_no")
	private String orderNo;
	/**
	 * 订单金额，必填，子订单实际支付金额
	 */
	private String amount;
	/**
	 * 创建时间，必填，yyyymmddhhmiss
	 */
	@JsonDeserialize(converter = DateDeserializer.class)
	@JsonProperty("create_time")
	private String createTime;
	/**
	 * 状态，必填
	 */
	@JsonDeserialize(converter = OrderStatusDeserialize.class)
	private OrderStatus status;
	/**
	 * 支付渠道，必填
	 */
	@JsonDeserialize(converter = PayChannelDeserialize.class)
	@JsonProperty("channel")
	private PayChannel payChannel;
	/**
	 * 商户业务，必填，商户业务信息
	 */
	private String remark;
	/**
	 * 费用，必填，交易费用  单位：分
	 */
	private String fee;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public PayChannel getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(PayChannel payChannel) {
		this.payChannel = payChannel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "OrderQueryResult [orderNo=" + orderNo + ", amount=" + amount + ", createTime=" + createTime
				+ ", status=" + status + ", payChannel=" + payChannel + ", remark=" + remark + ", fee=" + fee
				+ ", toString()=" + super.toString() + "]";
	}
	
}
