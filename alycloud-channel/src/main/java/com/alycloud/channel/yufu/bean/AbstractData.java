package com.alycloud.channel.yufu.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 请求参数公众部分
 * @author Moyq5
 * @date 2017年7月31日
 */
public abstract class AbstractData {

	/**
	 * 商户号，必填
	 */
	@JsonProperty("mer_no")
	private String merchNo;
	
	/**
	 * 流水号，必填，16
	 */
	@JsonProperty("serial_no")
	private String serialNo;

	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Override
	public String toString() {
		return "AbstractData [merchNo=" + merchNo + ", serialNo=" + serialNo + "]";
	}
	
}