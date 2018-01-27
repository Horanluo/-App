package com.alycloud.channel.linkpay.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信公众号支付接口－响应参数
 * @author Moyq5
 * @date 2017年4月10日
 */
public class PayOpenIdResult {

	/**
	 * 合作商订单号，必填
	 */
	@JsonProperty("orderNum")
	private String customOrderNum;
	/**
	 * 平台订单号，必填
	 */
	@JsonProperty("pl_orderNum")
	private String orderNum;
	/**
	 * 公众号支付所需参数组成的串，必填<br>
	 * 如：appId=wxdace645e0bc2c424&nonceStr=c31a209619bd422f84cb9d75120c283e&packageStr=prepay_id=wx2017020818364320b6f72a770109582909&paySign=850C2726D0DF47BC0418F0BD3C23E358&signType=MD5&timeStamp=1486550271
	 */
	@JsonProperty("pl_info")
	private String info;
	
	public String getCustomOrderNum() {
		return customOrderNum;
	}
	public void setCustomOrderNum(String customOrderNum) {
		this.customOrderNum = customOrderNum;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "PayOpenIdResult [customOrderNum=" + customOrderNum + ", orderNum=" + orderNum + ", info=" + info
				+ ", toString()=" + super.toString() + "]";
	}
	
}
