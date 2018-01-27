package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 御付渠道商户业务
 * @author Moyq5
 * @date 2017年8月5日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class ChannelMerchYufuBiz implements Serializable {

	private Long id;
	private Long yufuId;// 渠道商户Id（平台ID）
	private String payService;// 支付服务名WX：微信、QQ：手机QQ、ZFB：支付宝、CARD：银行卡，KJ：快捷支付，WXAPP：微信APP，微信和微信app只能选择其一，如果都需要只能申请两个商户
	private String scale;// 费率(‰)单位：千分之，例如千6费率填6
	private String debitCardRate; // 借记卡费率(‰)单位：千分之，例如千6费率填6
	private String creditCardRate; // 贷记卡费率(‰)单位：千分之，例如千6费率填6
	private String limitAmount; // 封顶手续金额, 快捷0表示手续费无封顶), 单位（分）
	private String d0Rate; // D0费率， 单位：千分之，例如千6费率填6，bat的要保持一致，d0Rate是在scale的基础上额外收取d0手续费
	private String d0MinAmount; // D0单笔最低交易金额，单位（分）,单笔最低交易金额，超过这个金额才D0, bat的要保持一致
	private String d0FeeBi; // D0每笔费用， 单位（分），bat的要保持一致
	private String tradeType;// 行业类型
	private String bottomFee; // 保底费用，单位（分），保留字段
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getYufuId() {
		return yufuId;
	}
	public void setYufuId(Long yufuId) {
		this.yufuId = yufuId;
	}
	public String getPayService() {
		return payService;
	}
	public void setPayService(String payService) {
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
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getBottomFee() {
		return bottomFee;
	}
	public void setBottomFee(String bottomFee) {
		this.bottomFee = bottomFee;
	}
	
}
