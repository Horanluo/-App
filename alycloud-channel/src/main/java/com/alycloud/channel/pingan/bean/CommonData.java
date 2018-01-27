package com.alycloud.channel.pingan.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 支付接口－公众请求参数。所有支付相关的接口最初请求参数。
 * @author Moyq5
 * @date 2017年6月14日
 */
public class CommonData {

	/**
	 * 商户门店open_id，必填
	 */
	@JsonProperty("open_id")
	private String openId;
	
	/**
	 * Unix时间戳，必填
	 */
	private Integer timestamp;
	
	/**
	 * 签名，必填
	 */
	private String sign;
	
	/**
	 * (json)加密参数
	 */
	private String data;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CommonData [openId=" + openId + ", timestamp=" + timestamp + ", sign=" + sign + ", data=" + data + "]";
	}
	
}
