package com.alycloud.channel.yufu.qrcode.bean;

import com.alycloud.channel.yufu.qrcode.enums.RespCode;
import com.alycloud.channel.yufu.qrcode.jackson.converter.RespCodeDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 响应参数公众部分
 * @author Moyq5
 * @date 2017年8月28日
 */
public abstract class AbstractResult {

	/**
	 * 返回码，长4，必填
	 */
	@JsonDeserialize(converter = RespCodeDeserialize.class)
	@JsonProperty("result")
	private RespCode respCode;
	/**
	 * 返回码描述， 长64，必填
	 */
	@JsonProperty("desc")
	private String respDesc;
	/**
	 * 加密字符串，长32， 非必填
	 */
	@JsonProperty("signOut")
	private String sign;
	public RespCode getRespCode() {
		return respCode;
	}
	public void setRespCode(RespCode respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "AbstractResult [respCode=" + respCode + ", respDesc=" + respDesc + ", sign=" + sign + "]";
	}
	
}