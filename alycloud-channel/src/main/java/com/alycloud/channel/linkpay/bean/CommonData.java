package com.alycloud.channel.linkpay.bean;

import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.jackson.converter.ServiceSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 支付接口－请求参数－公众部分
 * 
 * @author Moyq5
 * @date 2017年4月10日
 */
public class CommonData {

	/**
	 * 请求方的合作编号，必填
	 */
	private String groupId;
	/**
	 * 请求的交易服务码，必填
	 */
	@JsonSerialize(converter = ServiceSerialize.class)
	private Service service;
	/**
	 * 签名类型MD5，必填
	 */
	private String signType;
	/**
	 * 数据的签名字符串，必填
	 */
	private String sign;
	/**
	 * 系统时间（yyyyMMddHHmmss），必填
	 */
	private String datetime;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
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
	@Override
	public String toString() {
		return "CommonData [groupId=" + groupId + ", service=" + service + ", signType=" + signType + ", sign=" + sign
				+ ", datetime=" + datetime + "]";
	}
	
}
