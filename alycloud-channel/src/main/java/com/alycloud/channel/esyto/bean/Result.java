package com.alycloud.channel.esyto.bean;

import com.alycloud.channel.esyto.enums.RespCode;
import com.alycloud.channel.esyto.jackson.converter.RespCodeDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 响应参数
 * @author Moyq5
 * @date 2017年9月29日
 */
public class Result<D> {

	/**
	 * 返回码，长5，必填
	 */
	@JsonDeserialize(converter = RespCodeDeserialize.class)
	private RespCode code;
	/**
	 * 返回结果描述，必填
	 */
	private String msg;
	/**
	 * 接口结果参数，非必填
	 */
	private D data;
	/**
	 * 签名借，长32， 必填
	 */
	private String sign;
	public RespCode getCode() {
		return code;
	}
	public void setCode(RespCode code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public D getData() {
		return data;
	}
	public void setData(D data) {
		this.data = data;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + ", sign=" + sign + "]";
	}
}