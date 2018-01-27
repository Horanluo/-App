package com.alycloud.channel.yufu.qrcode.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信APP支付－响应参数
 * @author Moyq5
 * @date 2017年9月27日
 */
public class WeixinAppOrderResult extends AbstractResult {

	/**
	 * 平台方唯一交易请求流水号，长20，非必填，<br>
	 */
	private String transId;
	/**
	 * 请求交易的流水号，长32，非必填，<br>
	 * 请求交易的流水号（回传）
	 */
	private String reqId;
	/**
	 * 商户订单号，长30，非必填，<br>
	 * 商户订单号（回传）
	 */
	private String orderNo;
	/**
	 * 服务商appid，长30，非必填，<br>
	 * 如：wx8888888888888888
	 */
	private String appId;
	/**
	 * 微信支付分配的商户号，长32，非必填，<br>
	 * 如：1900000109
	 */
	private String partnerId;
	/**
	 * 微信返回的支付交易会话ID，长32，非必填，<br>
	 * 如：WX1217752501201407033233368018
	 */
	private String prepayId;
	/**
	 * 暂填写固定值，长128，非必填，<br>
	 * Sign=WXPay
	 */
	private String pack;
	/**
	 * 时间戳，长13，非必填，<br>
	 * 如：1414561699
	 */
	private String timeStamp;
	/**
	 * 随机字符串，长30，非必填，<br>
	 * 如：5K8264ILTKCH16CQ2502SI8ZNMTM67VS
	 */
	private String nonceStr;
	/**
	 * 签名，长32，非必填，<br>
	 * 如：C380BEC2BFD727A4B6845133519F3AD6
	 */
	@JsonProperty("sign")
	private String signStr;
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignStr() {
		return signStr;
	}
	public void setSignStr(String signStr) {
		this.signStr = signStr;
	}
	@Override
	public String toString() {
		return "WeixinAppOrderResult [transId=" + transId + ", reqId=" + reqId + ", orderNo=" + orderNo + ", appId="
				+ appId + ", partnerId=" + partnerId + ", prepayId=" + prepayId + ", pack=" + pack + ", timeStamp="
				+ timeStamp + ", nonceStr=" + nonceStr + ", signStr=" + signStr + ", toString()=" + super.toString()
				+ "]";
	}
	
}
