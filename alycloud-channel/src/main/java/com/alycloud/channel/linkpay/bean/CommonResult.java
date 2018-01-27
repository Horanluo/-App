package com.alycloud.channel.linkpay.bean;

import com.alycloud.channel.linkpay.enums.Code;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.jackson.converter.CodeDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 支付接口－响应参数－公众部分
 * 
 * @author Moyq5
 * @date 2017年4月10日
 */
public class CommonResult {

	/**
	 * 请求的交易服务码，必填
	 */
	@JsonProperty("pl_service")
	private Service service;
	/**
	 * 签名类型MD5，必填
	 */
	@JsonProperty("pl_signType")
	private String signType;
	/**
	 * 数据的签名字符串，必填
	 */
	@JsonProperty("pl_sign")
	private String sign;
	/**
	 * 系统时间（yyyyMMddHHmmss），必填
	 */
	@JsonProperty("pl_datetime")
	private String datetime;
	/**
	 * 返回代码，详情见返回码说明，必填
	 */
	@JsonDeserialize(converter = CodeDeserialize.class)
	@JsonProperty("pl_code")
	private Code code;
	/**
	 * 返回消息，详细错误信息，必填
	 */
	@JsonProperty("pl_message")
	private String message;
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public Code getCode() {
		return code;
	}
	public void setCode(Code code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "CommonResult [service=" + service + ", signType=" + signType + ", sign=" + sign + ", datetime="
				+ datetime + ", code=" + code + ", message=" + message + "]";
	}
	
}
