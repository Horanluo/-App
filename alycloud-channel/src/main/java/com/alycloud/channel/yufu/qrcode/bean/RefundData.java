package com.alycloud.channel.yufu.qrcode.bean;

import java.util.Date;

import com.alycloud.channel.yufu.qrcode.jackson.converter.DateSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 退款接口－请求参数
 * @author Moyq5
 * @date 2017年8月30日
 */
public class RefundData extends AbstractData {

	/**
	 * 商户订单号或平台方流水号，长30，非必填，<br>
	 */
	private String orderNo;
	/**
	 * 终端编码，长200，非必填，<br>
	 */
	private String termNo;
	/**
	 * 退款金额，长12，非必填，<br>
	 * 退款金额(如不传则视为全部可退款，总退款金额不能超过原始消费金额)
	 */
	private String money;
	/**
	 * 商户请求交易的流水号，长32，必填，<br>
	 */
	private String reqId;
	/**
	 * 平台方唯一交易请求流水号，长20，非必填，<br>
	 * 平台方唯一交易请求流水号（原消费交易）
	 */
	private String orgTransId;
	/**
	 * 商户请求交易的流水号，长32，非必填，<br>
	 * 原始请求交易流水号（原消费交易）
	 */
	private String orgReqId;
	/**
	 * 请求时间，长24，必填，<br>
	 * 格式: YYYYMMDDhhmmss
	 */
	@JsonSerialize(converter = DateSerialize.class)
	private Date reqTime;
	/**
	 * 操作员编号，长30，非必填，<br>
	 */
	private String operatorId;
	/**
	 * 附加信息，长2048，非必填，<br>
	 */
	private String extraDesc;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTermNo() {
		return termNo;
	}
	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
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
	public Date getReqTime() {
		return reqTime;
	}
	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getExtraDesc() {
		return extraDesc;
	}
	public void setExtraDesc(String extraDesc) {
		this.extraDesc = extraDesc;
	}
	@Override
	public String toString() {
		return "OrderQueryData [orderNo=" + orderNo + ", termNo=" + termNo + ", money=" + money + ", reqId=" + reqId
				+ ", orgTransId=" + orgTransId + ", orgReqId=" + orgReqId + ", reqTime=" + reqTime + ", operatorId="
				+ operatorId + ", extraDesc=" + extraDesc + ", toString()=" + super.toString() + "]";
	}
}
