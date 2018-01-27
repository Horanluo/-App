package com.alycloud.channel.pingan.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 支付接口－响应参数。所有支付相关的接口最初响应参数。
 * @author Moyq5
 * @date 2017年6月14日
 */
public class CommonResult {

	/**
	 * 返回码，必填
	 */
	@JsonProperty("errcode")
	private Integer code;
	
	/**
	 * 结果描述信息
	 */
	private String msg;
	
	/**
	 * Unix时间戳
	 */
	private Integer timestamp;
	
	/**
	 * 签名
	 */
	private String sign;
	
	/**
	 * 响应内容（加密过的JSON内容，需要解密成）
	 */
	private String data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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
		return "CommonResult [code=" + code + ", msg=" + msg + ", timestamp=" + timestamp + ", sign=" + sign + ", data="
				+ data + "]";
	}
}
