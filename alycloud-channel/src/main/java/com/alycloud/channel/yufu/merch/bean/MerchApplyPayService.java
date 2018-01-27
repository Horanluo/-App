package com.alycloud.channel.yufu.merch.bean;

import com.alycloud.channel.yufu.merch.enums.PayService;
import com.alycloud.channel.yufu.merch.jackson.converter.PayServiceSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 商户入驻入接口－请求参数－支付服务信息
 * @author Moyq5
 * @date 2017年7月31日
 */
public class MerchApplyPayService {

	/**
	 * 支付服务名，必填
	 */
	@JsonSerialize(converter = PayServiceSerialize.class)
	private PayService payService;
	
	/**
	 * 费率(‰)，单位：千分之，例如千6费率填6
	 */
	private String scale;

	/**
	 * 借记卡费率(‰)，单位：千分之，例如千6费率填6
	 */
	private String debitCardRate;
	
	/**
	 * 贷记卡费率(‰)，单位：千分之，例如千6费率填6
	 */
	private String creditCardRate;
	
	/**
	 * 封顶手续金额，快捷0表示手续费无封顶，单位（分）
	 */
	@JsonProperty("limitAmont")
	private String limitAmount;

	/**
	 * D0费率，d0Rate，d0MinAmount，d0FeeBi这三个参数如果传了，需要一起传递<br>
	 * 单位：千分之，例如千6费率填6，bat的要保持一致，d0Rate是在scale的基础上额外收取d0手续费
	 */
	private String d0Rate;

	/**
	 * D0单笔最低交易金额，单位（分）,单笔最低交易金额，超过这个金额才D0, bat的要保持一致
	 */
	private String d0MinAmount;

	/**
	 * D0每笔费用，单位（分），bat的要保持一致
	 */
	private String d0FeeBi;

	/**
	 * 保底费用，单位（分），保留字段
	 */
	private String bottomFee;

	/**
	 * 行业类型，必填，快捷可默认填写5210
	 */
	private String tradeType;

	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getDebitCardRate() {
		return debitCardRate;
	}

	public void setDebitCardRate(String debitCardRate) {
		this.debitCardRate = debitCardRate;
	}

	public String getCreditCardRate() {
		return creditCardRate;
	}

	public void setCreditCardRate(String creditCardRate) {
		this.creditCardRate = creditCardRate;
	}

	public String getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(String limitAmount) {
		this.limitAmount = limitAmount;
	}

	public String getD0Rate() {
		return d0Rate;
	}

	public void setD0Rate(String d0Rate) {
		this.d0Rate = d0Rate;
	}

	public String getD0MinAmount() {
		return d0MinAmount;
	}

	public void setD0MinAmount(String d0MinAmount) {
		this.d0MinAmount = d0MinAmount;
	}

	public String getD0FeeBi() {
		return d0FeeBi;
	}

	public void setD0FeeBi(String d0FeeBi) {
		this.d0FeeBi = d0FeeBi;
	}

	public String getBottomFee() {
		return bottomFee;
	}

	public void setBottomFee(String bottomFee) {
		this.bottomFee = bottomFee;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	@Override
	public String toString() {
		return "MerchApplyPayService [payService=" + payService + ", scale=" + scale + ", debitCardRate="
				+ debitCardRate + ", creditCardRate=" + creditCardRate + ", limitAmont=" + limitAmount + ", d0Rate="
				+ d0Rate + ", d0MinAmount=" + d0MinAmount + ", d0FeeBi=" + d0FeeBi + ", bottomFee=" + bottomFee
				+ ", tradeType=" + tradeType + "]";
	}

}
