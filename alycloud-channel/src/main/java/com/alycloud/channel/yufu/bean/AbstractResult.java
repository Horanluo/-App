package com.alycloud.channel.yufu.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 响应参数公众部分
 * @author Moyq5
 * @date 2017年7月31日
 */
public abstract class AbstractResult {

	/**
	 * 流水号，和请求参数一致，必填，16
	 */
	@JsonProperty("serial_no")
	private String serialNo;
	/**
	 * 签名， 必填
	 */
	private String sign;
	/**
	 * 应答返回码（“0000”,执行成功,其他，失败）， 必填
	 */
	@JsonProperty("resp_code")
	private String respCode;
	/**
	 * 应答返回提示信息， 必填
	 */
	@JsonProperty("resp_msg")
	private String respMsg;
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	@Override
	public String toString() {
		return "ResultAbstract [serialNo=" + serialNo + ", sign=" + sign + ", respCode=" + respCode + ", respMsg="
				+ respMsg + "]";
	}
}