package com.alycloud.channel.esyto.bean;

import com.alycloud.channel.esyto.enums.OrderStatus;
import com.alycloud.channel.esyto.enums.PayType;
import com.alycloud.channel.esyto.enums.ScanType;
import com.alycloud.channel.esyto.jackson.converter.OrderStatusDeserialize;
import com.alycloud.channel.esyto.jackson.converter.PayTypeDeserialize;
import com.alycloud.channel.esyto.jackson.converter.ScanTypeDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 查询接口－响应参数
 * @author Moyq5
 * @date 2017年9月30日
 */
public class OrderQueryResult {

	/**
	 * 商户单号，必填
	 */
	private String customOrderNo;
	/**
	 * 平台单号，必填
	 */
	private String orderNo;
	/**
	 * 支付方式
	 */
	@JsonDeserialize(converter = PayTypeDeserialize.class)
	private PayType payType;
	/**
	 * 扫码方式
	 */
	@JsonDeserialize(converter = ScanTypeDeserialize.class)
	private ScanType scanType;
	/**
	 * 交易金额，单位：元，必填
	 */
	private String amount;
	/**
	 * 手续费，单位：元，必填
	 */
	private String fee;
	/**
	 * 支付状态
	 */
	@JsonDeserialize(converter = OrderStatusDeserialize.class)
	private OrderStatus status;
	public String getCustomOrderNo() {
		return customOrderNo;
	}
	public void setCustomOrderNo(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public PayType getPayType() {
		return payType;
	}
	public void setPayType(PayType payType) {
		this.payType = payType;
	}
	public ScanType getScanType() {
		return scanType;
	}
	public void setScanType(ScanType scanType) {
		this.scanType = scanType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OrderQueryResult [customOrderNo=" + customOrderNo + ", orderNo=" + orderNo + ", payType=" + payType
				+ ", scanType=" + scanType + ", amount=" + amount + ", fee=" + fee + ", status=" + status + "]";
	}
}
