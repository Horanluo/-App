package com.alycloud.channel.payeco.bean;

import java.util.Date;

/**
 * 易联支付－短信验证码发送接口－请求参数
 * @author Moyq5
 * @date 2017年4月11日
 */
public class SendSmCodeData extends AbstractData {

	/**
	 * 短信凭证号，必填<br>
	 * 需要商户保证唯一性；重新发送短信传递相同的【短信凭证号】
	 */
	private String smId;
	/**
	 * 交易提交时间，必填<br>
	 * 格式：yyyymmddhhmmss
	 */
	private Date tradeTime;
	/**
	 * 手机号码，必填<br>
	 * 接收短信的手机号码
	 */
	private String mobileNo;
	/**
	 * 验证短信的交易码，必填<br>
	 * 填写：PayByAcc<br>
	 * 用于交易对短信码验证的交易限制
	 */
	private String verifyTradeCode;
	/**
	 * 短信参数，必填<br>
	 * 参与签名：采用UTF-8编码<br>
	 * 提交参数：采用UTF-8的base64格式编码<br>
	 * 参数格式：行业编号|姓名|证件号码|银行卡号|交易金额<br>
	 * 数据要求：非必填，若填写了值，支付接口会对该值进行比较<br>
	 * 行业编号：不填采用商户默认行业
	 */
	private String smParam;
	
	@Override
	public String getTradeCode() {
		return "SendSmCode";
	}
	
	public String getSmId() {
		return smId;
	}
	public void setSmId(String smId) {
		this.smId = smId;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getVerifyTradeCode() {
		return verifyTradeCode;
	}
	public void setVerifyTradeCode(String verifyTradeCode) {
		this.verifyTradeCode = verifyTradeCode;
	}
	public String getSmParam() {
		return smParam;
	}
	public void setSmParam(String smParam) {
		this.smParam = smParam;
	}
	@Override
	public String toString() {
		return "SendSmCodeData [smId=" + smId + ", mobileNo=" + mobileNo + ", verifyTradeCode=" + verifyTradeCode
				+ ", smParam=" + smParam + ", toString()=" + super.toString() + "]";
	}
}
