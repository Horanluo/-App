package com.alycloud.channel.pingan.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 取消订单、订单退款接口签名参数（请求参数）对象。
 * @author Moyq5
 * @date 2017年7月11日
 */
public class SignData4Refund extends SignData {
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
