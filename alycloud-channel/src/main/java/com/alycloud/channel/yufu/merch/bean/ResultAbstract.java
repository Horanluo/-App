package com.alycloud.channel.yufu.merch.bean;

/**
 * 响应参数公众部分
 * @author Moyq5
 * @date 2017年7月31日
 */
public class ResultAbstract {

	/**
	 * 流水号，和请求参数一致，必填，16
	 */
	private String serialNo;
	/**
	 * 签名， 必填
	 */
	private String sign;
	/**
	 * 应答返回码（“0000”,执行成功,其他，失败）， 必填
	 */
	private String resultCode;
	/**
	 * 应答返回提示信息， 必填
	 */
	private String resultMessage;

	public ResultAbstract() {
		super();
	}

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

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	@Override
	public String toString() {
		return "MerchApplyResult [serialNo=" + serialNo + ", sign=" + sign + ", resultCode=" + resultCode
				+ ", resultMessage=" + resultMessage + "]";
	}

}