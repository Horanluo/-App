package com.alycloud.channel.pingan.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 签名参数（请求参数）对象。
 * @author Moyq5
 * @date 2017年6月15日
 */
public class SignData extends CommonData {

	/**
	 * 必填
	 */
	@JsonProperty("open_key")
	private String openKey;

	public String getOpenKey() {
		return openKey;
	}

	public void setOpenKey(String openKey) {
		this.openKey = openKey;
	}

	@Override
	public String toString() {
		return "SignData [openKey=" + openKey + ", toString()=" + super.toString() + "]";
	}
	
}
