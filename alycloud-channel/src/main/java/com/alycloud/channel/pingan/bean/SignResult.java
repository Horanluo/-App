package com.alycloud.channel.pingan.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 签名参数（响应参数）对象。
 * @author Moyq5
 * @date 2017年6月14日
 */
public class SignResult extends CommonResult {

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
		return "SignResult [openKey=" + openKey + ", toString()=" + super.toString() + "]";
	}
}
