package com.alycloud.channel.payeco.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.alycloud.channel.payeco.enums.OrderStatus;
import com.alycloud.channel.payeco.jackson.converter.OrderStatusDeserialize;
import com.alycloud.channel.payeco.jackson.converter.StringToDateBy_yyyyMMdd;
import com.alycloud.channel.payeco.jackson.converter.StringToDateBy_yyyyMMddHHmmss;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public abstract class AbstractOrderStatusResult extends AbstractResultBody {

	/**
	 * 商户订单金额，必填<br>
	 */
	protected BigDecimal amount;
	/**
	 * 商户保留信息
	 */
	protected String extData;
	/**
	 * 易联订单号，必填<br>
	 */
	protected String orderId;
	/**
	 * 订单状态，必填<br>
	 */
	@JsonDeserialize(converter = OrderStatusDeserialize.class)
	protected OrderStatus status;
	/**
	 * 支付成功时间
	 */
	@JsonDeserialize(converter = StringToDateBy_yyyyMMddHHmmss.class)
	protected Date payTime;
	/**
	 * 清算日期
	 */
	@JsonDeserialize(converter = StringToDateBy_yyyyMMdd.class)
	protected Date settleDate;
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getExtData() {
		return extData;
	}
	public void setExtData(String extData) {
		this.extData = extData;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}
	@Override
	public String toString() {
		return "AbstractOrderStatusResult [amount=" + amount + ", extData=" + extData + ", orderId=" + orderId
				+ ", status=" + status + ", payTime=" + payTime + ", settleDate=" + settleDate + ", toString()="
				+ super.toString() + "]";
	}

}