package com.alycloud.channel.yufu.qrcode.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 请求参数公众部分
 * @author Moyq5
 * @date 2017年8月28日
 */
public abstract class AbstractData {

	/**
	 * 商户号，长15，必填
	 */
	@JsonProperty("merNo")
	private String merchNo;
	/**
	 * 签名，长32，必填，<br>
	 */
	@JsonProperty("signIn")
	private String sign;
	public String getMerchNo() {
		return merchNo;
	}
	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "AbstractData [merchNo=" + merchNo + ", sign=" + sign + "]";
	}

	
}