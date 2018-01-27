package com.alycloud.channel.esyto.bean;

/**
 * 请求参数
 * @author Moyq5
 * @date 2017年9月29日
 */
public class Data {

	/**
	 * 商户号，长15，必填
	 */
	private String merchNo;
	/**
	 * 接口参数，必填，Json结构字符串
	 */
	private String data;
	/**
	 * 随机字符串，长32，必填
	 */
	private String nonceStr;
	/**
	 * 时间戳，必填，1970年到今天的秒数
	 */
	private Integer timestamp;
	/**
	 * 签名，长32，必填，<br>
	 */
	private String sign;
	public String getMerchNo() {
		return merchNo;
	}
	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
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
	@Override
	public String toString() {
		return "CommonData [merchNo=" + merchNo + ", data=" + data + ", nonceStr=" + nonceStr + ", timestamp="
				+ timestamp + ", sign=" + sign + "]";
	}
	
}