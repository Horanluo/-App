package com.alycloud.channel.yufu.bean;

import com.alycloud.channel.yufu.jackson.converter.OrderListDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 商户订单查询接口响应参数对象
 * @author Moyq5
 * @date 2017年8月8日
 */
public class MerchOrderQueryResult extends AbstractResult {
	
	/**
	 * 商户订单号，必填
	 */
	@JsonProperty("mer_order_no")
	private String merchOrderNo;
	/**
	 * 订单总金额，必填
	 */
	private String amount;
	/**
	 * 支付总金额，必填，完成支付的订单总金额
	 */
	@JsonProperty("finish_amount")
	private String finishAmount;
	/**
	 * 子订单数目，必填
	 */
	@JsonProperty("order_list_num")
	private String orderListNum;
	/**
	 * 子订单列表，必填
	 */
	@JsonDeserialize(using = OrderListDeserializer.class)
	@JsonProperty("order_list")
	private OrderList orderList;
	public String getMerchOrderNo() {
		return merchOrderNo;
	}
	public void setMerchOrderNo(String merchOrderNo) {
		this.merchOrderNo = merchOrderNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFinishAmount() {
		return finishAmount;
	}
	public void setFinishAmount(String finishAmount) {
		this.finishAmount = finishAmount;
	}
	public String getOrderListNum() {
		return orderListNum;
	}
	public void setOrderListNum(String orderListNum) {
		this.orderListNum = orderListNum;
	}
	public OrderList getOrderList() {
		return orderList;
	}
	public void setOrderList(OrderList orderList) {
		this.orderList = orderList;
	}
	@Override
	public String toString() {
		return "MerchOrderQueryResult [merchOrderNo=" + merchOrderNo + ", amount=" + amount + ", finishAmount="
				+ finishAmount + ", orderListNum=" + orderListNum + ", orderList=" + orderList + ", toString()="
				+ super.toString() + "]";
	}
	
}
