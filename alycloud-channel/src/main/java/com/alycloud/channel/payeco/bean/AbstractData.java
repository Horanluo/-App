package com.alycloud.channel.payeco.bean;

/**
 * 易联支付请求参数公共部分
 * @author Moyq5
 * @date 2017年4月11日
 */
public abstract class AbstractData {
	/**
	 * 商户代码，必填<br>
	 */
	private String merchantId;
	/**
	 * 商户订单号，必填<br>
	 * 若短信发送已经产生了订单号，建议填写该值，有利于订单发送短信情况排查和统计；<br>
	 * 若填写了该值，支付接口会对该值进行比较
	 */
	private String merchOrderId;
	/**
	 * 签名，必填<br>
	 * 对上面的所有字段（除“交易码”）进行按顺序RSA签名
	 */
	private String sign;
	
	/**
	 * 交易码，必填
	 */
	public abstract String getTradeCode();
	
	/**
	 * 通讯协议版本号，必填，固定为："2.0.0"<br>
	 */
	public String getVersion() {
		return "2.0.0";
	}
	
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchOrderId() {
		return merchOrderId;
	}
	public void setMerchOrderId(String merchOrderId) {
		this.merchOrderId = merchOrderId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
