package com.alycloud.channel.yufu.bean;

import com.alycloud.channel.yufu.enums.PayType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 支付参数
 * @author Moyq5
 * @date 2017年8月2日
 */
public class PayData {

	/**
	 * token
	 */
	private String token;
	
	/**
	 * 商户订单号
	 */
	@JsonProperty("mer_order_no")
	private String merchOrderNo;
	/**
	 * 订单备注
	 */
	private String remark;
	/**
	 * 订单金额(分)
	 */
	@JsonProperty("mer_amount")
	private String amount;
	/**
	 * 支付类型
	 */
	@JsonProperty("pay_type")
	private PayType payType;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMerchOrderNo() {
		return merchOrderNo;
	}
	public void setMerchOrderNo(String merchOrderNo) {
		this.merchOrderNo = merchOrderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public PayType getPayType() {
		return payType;
	}
	public void setPayType(PayType payType) {
		this.payType = payType;
	}
	@Override
	public String toString() {
		return "PayData [token=" + token + ", customOrderNo=" + merchOrderNo + ", remark=" + remark + ", amount="
				+ amount + ", payType=" + payType + "]";
	}
	
}
