package com.alycloud.channel.yufu.qrcode.bean;

/**
 * 微信公众号支付－请求参数
 * @author Moyq5
 * @date 2017年9月14日
 */
public class OfficalOrderData extends AbstractOrderData {

	/**
	 * 接入方微信公众号 id（于微信分配），长18，必填，<br>
	 * 接入方微信公众号 id（于微信分配）
	 */
	private String subAppId;
	/**
	 * 用户在subAppId下的唯一标识，长28，必填，<br>
	 * 接入方下单前需要调用微信【网页授权获取用户信息】接口获取到用户的 opened，
	 * https://mp.weixin.qq.com/wiki?id=mp1421140842&highline=%E6%8E%88%E6%9D%83
	 */
	private String subOpenId;
	/**
	 * 异步通知url，长120，非必填，<br>
	 * 需要向申请白名单
	 */
	private String notifyUrl;
	public String getSubAppId() {
		return subAppId;
	}
	public void setSubAppId(String subAppId) {
		this.subAppId = subAppId;
	}
	public String getSubOpenId() {
		return subOpenId;
	}
	public void setSubOpenId(String subOpenId) {
		this.subOpenId = subOpenId;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	@Override
	public String toString() {
		return "OfficalOrderData [subAppId=" + subAppId + ", subOpenId=" + subOpenId + ", notifyUrl=" + notifyUrl
				+ ", toString()=" + super.toString() + "]";
	}
	
}
