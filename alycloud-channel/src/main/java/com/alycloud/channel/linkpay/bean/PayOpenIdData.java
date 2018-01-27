package com.alycloud.channel.linkpay.bean;

/**
 * 微信公众号支付接口－请求参数
 * @author Moyq5
 * @date 2017年4月10日
 */
public class PayOpenIdData extends PayQrcodeDataWithLimit {

	/**
	 * 用户的公众号openId，必填
	 */
	private String openId;

	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Override
	public String toString() {
		return "PayOpenIdData [openId=" + openId + ", toString()=" + super.toString() + "]";
	}
}
