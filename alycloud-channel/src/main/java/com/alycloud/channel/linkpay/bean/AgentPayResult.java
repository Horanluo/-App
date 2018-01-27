package com.alycloud.channel.linkpay.bean;

import com.alycloud.channel.linkpay.enums.TransState;
import com.alycloud.channel.linkpay.jackson.converter.TransStateDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 代付接口－响应参数<br>
 * 代付状态查询结果参数。
 * @author Moyq5
 * @date 2017年4月10日
 */
public class AgentPayResult {

	/**
	 * 合作商订单号，必填
	 */
	@JsonProperty("orderNum")
	private String customOrderNum;
	/**
	 * 平台订单号，必填
	 */
	@JsonProperty("pl_orderNum")
	private String orderNum;
	/**
	 * 交易状态
	 */
	@JsonDeserialize(converter = TransStateDeserialize.class)
	@JsonProperty("pl_transState")
	private TransState transState;
	/**
	 * 交易说明
	 */
	@JsonProperty("pl_transMessage")
	private String transMessage;
	
	public String getCustomOrderNum() {
		return customOrderNum;
	}
	public void setCustomOrderNum(String customOrderNum) {
		this.customOrderNum = customOrderNum;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public TransState getTransState() {
		return transState;
	}
	public void setTransState(TransState transState) {
		this.transState = transState;
	}
	public String getTransMessage() {
		return transMessage;
	}
	public void setTransMessage(String transMessage) {
		this.transMessage = transMessage;
	}
	@Override
	public String toString() {
		return "AbstractAgentPayResult [customOrderNum=" + customOrderNum + ", orderNum=" + orderNum + ", transState="
				+ transState + ", transMessage=" + transMessage + "]";
	}
	
	
}
