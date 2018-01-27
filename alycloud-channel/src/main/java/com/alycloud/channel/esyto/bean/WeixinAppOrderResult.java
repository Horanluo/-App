package com.alycloud.channel.esyto.bean;

/**
 * 微信APP支付－响应参数
 * @author Moyq5
 * @date 2017年9月29日
 */
public class WeixinAppOrderResult {

	/**
	 * 商户自定义订单号(回传)，长20，必填<br>
	 */
	private String customOrderNo;
	/**
	 * 平台订单号，长32，非必填<br>
	 */
	private String orderNo;
	/**
	 * app端调用微信支付的appId参数，长30，必填<br>
	 */
	private String appId;
	/**
	 * app端调用微信支付的partnerId参数，长32，必填<br>
	 */
	private String partnerId;
	/**
	 * app端调用微信支付的prepayId参数，长32，必填<br>
	 */
	private String prepayId;
	/**
	 * app端调用微信支付的package参数，长128，必填<br>
	 */
	private String pack;
	/**
	 * app端调用微信支付的timeStamp参数，长10，必填<br>
	 */
	private String timeStamp;
	/**
	 * app端调用微信支付的nonceStr参数，长32，必填<br>
	 */
	private String nonceStr;
	/**
	 * app端调用微信支付的sign参数，长32，必填<br>
	 */
	private String sign;
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "WeixinAppOrderResult [customOrderNo=" + customOrderNo + ", orderNo=" + orderNo + ", appId=" + appId
				+ ", partnerId=" + partnerId + ", prepayId=" + prepayId + ", pack=" + pack + ", timeStamp=" + timeStamp
				+ ", nonceStr=" + nonceStr + ", sign=" + sign + "]";
	}
	
}
