package com.alycloud.channel.yufu.qrcode.bean;

import java.util.Date;

import com.alycloud.channel.yufu.qrcode.jackson.converter.DateSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 支付（预统一下单、反扫）接口－请求参数（公众部分）
 * @author Moyq5
 * @date 2017年8月29日
 */
public class AbstractOrderData extends AbstractData {

	/**
	 * 商户订单号，长30，必填，<br>
	 * 商户订单号，同一商户号和终端号下唯一
	 */
	private String orderNo;
	/**
	 * 订单金额，长12，必填，<br>
	 * 单位为分
	 */
	private String amount;
	/**
	 * 请求交易的流水号，长32，必填，<br>
	 * 请求交易的流水号，同一商户号和终端号下唯一
	 */
	private String reqId;
	/**
	 * 请求时间，长14，必填，<br>
	 * YYYYMMDDhhmmss
	 */
	@JsonSerialize(converter = DateSerialize.class)
	private Date reqTime;
	/**
	 * 终端编码，长200，必填，<br>
	 */
	private String termNo;
	/**
	 * 操作员编号，长30，非必填，<br>
	 * 商户操作员编号
	 */
	private String operatorId;
	/**
	 * 币种，长20，非必填，<br>
	 * 默认CNY
	 */
	private String currency;
	/**
	 * 商品名称，长100，非必填，<br>
	 */
	private String goodsName;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public Date getReqTime() {
		return reqTime;
	}
	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}
	public String getTermNo() {
		return termNo;
	}
	public void setTermNo(String termNo) {
		this.termNo = termNo;
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
		return "AbstractOrderData [orderNo=" + orderNo + ", amount=" + amount + ", reqId=" + reqId + ", reqTime="
				+ reqTime + ", termNo=" + termNo + ", operatorId=" + operatorId + ", currency=" + currency
				+ ", goodsName=" + goodsName + ", extraDesc=" + extraDesc + ", toString()=" + super.toString() + "]";
	}
}
