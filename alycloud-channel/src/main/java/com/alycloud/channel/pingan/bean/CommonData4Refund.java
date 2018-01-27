package com.alycloud.channel.pingan.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 退款接口－公众请求参数。所有支付相关的接口最初请求参数。
 * @author Moyq5
 * @date 2017年7月10日
 */
public class CommonData4Refund extends CommonData {

	/**
	 * 加密类型RSA或者RSA2，必填。
	 */
	@JsonProperty("sign_type")
	private String signType;

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	@Override
	public String toString() {
		return "CommonData4Refund [signType=" + signType + ", toString()=" + super.toString() + "]";
	}
	
}
