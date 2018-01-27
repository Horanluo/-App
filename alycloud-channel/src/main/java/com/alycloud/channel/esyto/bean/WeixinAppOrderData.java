package com.alycloud.channel.esyto.bean;

/**
 * 微信APP支付－请求参数
 * @author Moyq5
 * @date 2017年9月29日
 */
public class WeixinAppOrderData {

	/**
	 * 商户自定义订单号，同一商户唯 一，长20，必填
	 */
	private String customOrderNo;
	/**
	 * 商户名称，长50，非必填
	 */
	private String goodsName;
	/**
	 * 交易金额，单位分，必填
	 */
	private Integer amount;// 支付金额，单位：分
	/**
	 * 商户APP在微信申请的appId，必填
	 */
	private String appId;
	/**
	 * 支付结果异步通知地址，非必填
	 */
	private String notifyUrl;
	public String getCustomOrderNo() {
		return customOrderNo;
	}
	public void setCustomOrderNo(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	@Override
	public String toString() {
		return "WeixinAppOrderData [customOrderNo=" + customOrderNo + ", goodsName=" + goodsName + ", amount=" + amount
				+ ", appId=" + appId + ", notifyUrl=" + notifyUrl + "]";
	}
	
}
