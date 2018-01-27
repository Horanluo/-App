package com.alycloud.pay.support.channel.api;

/**
 * 微信APP支付接口结果
 * @author Moyq5
 * @date 2017年9月28日
 */
public class ChannelWeixinAppOrderResult {

	private String appId;// app端调用微信支付的appId参数，客户APP的微信appId(回传)或者服务商的appId，长32
	private String partnerId;// app端调用微信支付的partnerId参数，长32
	private String prepayId;// app端调用微信支付的prepayId参数，长32
	private String pack;// app端调用微信支付的package参数，长128
	private String timeStamp;// app端调用微信支付的timeStamp参数，长13
	private String nonceStr;// app端调用微信支付的nonceStr参数，长32
	private String sign;// app端调用微信支付的sign参数，长32
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
	
}
