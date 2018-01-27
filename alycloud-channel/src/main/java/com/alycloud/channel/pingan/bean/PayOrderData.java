package com.alycloud.channel.pingan.bean;

import com.alycloud.channel.pingan.enums.PaymentTag;
import com.alycloud.channel.pingan.jackson.converter.PaymentTagSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 下单接口data参数对象，需要转换成JSON并加密赋给<code>CommonData</code>对象的<code>data</code>属性
 * @author Moyq5
 * @date 2017年6月14日
 */
public class PayOrderData {

	/**
	 * 开发者流水号，确认同一门店内唯一，必填。
	 */
	@JsonProperty("out_no")
	private String customOrderNo;
	
	/**
	 * 付款方式，必填
	 */
	@JsonProperty("pmt_tag")
	@JsonSerialize(converter = PaymentTagSerialize.class)
	private PaymentTag paymentTag;
	
	/**
	 * 商户自定义付款方式名称，当paymentTag为Diy时，不能为空。
	 */
	@JsonProperty("pmt_name")
	private String paymentName;
	
	/**
	 * 订单名称（描述）
	 */
	@JsonProperty("ord_name")
	private String orderName;
	
	/**
	 * 原始交易金额（以分为单位，没有小数点），必填
	 */
	@JsonProperty("original_amount")
	private Integer originalAmount;
	
	/**
	 * 折扣金额（以分为单位，没有小数点）
	 */
	@JsonProperty("discount_amount")
	private Integer discountAmount;
	
	/**
	 * 抹零金额（以分为单位，没有小数点）
	 */
	@JsonProperty("ignore_amount")
	private Integer ignoreAmount;
	
	/**
	 * 实际交易金额（以分为单位，没有小数点），必填
	 */
	@JsonProperty("trade_amount")
	private Integer tradeAmount;
	
	/**
	 * 交易帐号（收单机构交易的银行卡号，手机号等，可为空）,长度50
	 */
	@JsonProperty("trade_account")
	private String tradeAccount;
	
	/**
	 * 交易号（收单机构交易号，可为空）,长度50
	 */
	@JsonProperty("trade_no")
	private String tradeNo;
	
	/**
	 * 订单备注，长度100
	 */
	private String remark;
	
	/**
	 * 订单标记，订单附加数据，长度50
	 */
	private String tag;
	
	/**
	 * 异步通知地址，长度200
	 */
	@JsonProperty("notify_url")
	private String notifyUrl;
	
	/**
	 * 条码支付的授权码（条码抢扫手机扫到的一串数字）。<br>
	 * 支付方式：主扫、条码支付（商家扫用户手机）。<br>
	 * 长度50
	 */
	@JsonProperty("auth_code")
	private String authCode;
	
	/**
	 * 公众号/服务窗支付必填参数，支付结果跳转地址。<br>
	 * 支付方式：微信公众号、支付宝服务窗支付。<br>
	 * 长度200
	 */
	@JsonProperty("jump_url")
	private String jumpUrl;
	
	/**
	 * 微信APP支付时必填参数，商户在微信开放平台上申请的APPID,说明见《外部应用接口_微信APP接入说明》。<br>
	 * 支付方式：微信APP支付。<br>
	 * 长度200
	 */
	@JsonProperty("wx_appid")
	private String wxAppId;
	
	/**
	 * 商品标记，代金券或立减优惠功能的参数其它说明详见微信说明。<br>
	 * 微信高级参数。<br>
	 * json串
	 */
	@JsonProperty("goods_tag")
	private String wxGoodsTag;
	
	/**
	 * limit_pay=no_credit，限制用户不能使用信用卡支付。<br>
	 * 微信高级参数。<br>
	 */
	@JsonProperty("limit_pay")
	private String wxLimitPay;
	
	/**
	 * 订单包含的商品列表信息，Json格式，其它说明详见支付宝商品明细说明。<br>
	 * 支付宝高级参数。<br>
	 * json串
	 */
	@JsonProperty("goods_detail")
	private String aliGoodsDetail;
	
	/**
	 * 业务扩展参数，详见支付宝商品明细说明。<br>
	 * 支付宝高级参数。<br>
	 * json串
	 */
	@JsonProperty("extend_params")
	private String aliExtendParams;

	public String getCustomOrderNo() {
		return customOrderNo;
	}

	public void setCustomOrderNo(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}

	public PaymentTag getPaymentTag() {
		return paymentTag;
	}

	public void setPaymentTag(PaymentTag paymentTag) {
		this.paymentTag = paymentTag;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Integer getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(Integer originalAmount) {
		this.originalAmount = originalAmount;
	}

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Integer getIgnoreAmount() {
		return ignoreAmount;
	}

	public void setIgnoreAmount(Integer ignoreAmount) {
		this.ignoreAmount = ignoreAmount;
	}

	public Integer getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Integer tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getTradeAccount() {
		return tradeAccount;
	}

	public void setTradeAccount(String tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getWxAppId() {
		return wxAppId;
	}

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public String getWxGoodsTag() {
		return wxGoodsTag;
	}

	public void setWxGoodsTag(String wxGoodsTag) {
		this.wxGoodsTag = wxGoodsTag;
	}

	public String getWxLimitPay() {
		return wxLimitPay;
	}

	public void setWxLimitPay(String wxLimitPay) {
		this.wxLimitPay = wxLimitPay;
	}

	public String getAliGoodsDetail() {
		return aliGoodsDetail;
	}

	public void setAliGoodsDetail(String aliGoodsDetail) {
		this.aliGoodsDetail = aliGoodsDetail;
	}

	public String getAliExtendParams() {
		return aliExtendParams;
	}

	public void setAliExtendParams(String aliExtendParams) {
		this.aliExtendParams = aliExtendParams;
	}

	@Override
	public String toString() {
		return "PayOrderData [customOrderNo=" + customOrderNo + ", paymentTag=" + paymentTag + ", paymentName="
				+ paymentName + ", orderName=" + orderName + ", originalAmount=" + originalAmount + ", discountAmount="
				+ discountAmount + ", ignoreAmount=" + ignoreAmount + ", tradeAmount=" + tradeAmount + ", tradeAccount="
				+ tradeAccount + ", tradeNo=" + tradeNo + ", remark=" + remark + ", tag=" + tag + ", notifyUrl="
				+ notifyUrl + ", authCode=" + authCode + ", jumpUrl=" + jumpUrl + ", wxAppId=" + wxAppId
				+ ", wxGoodsTag=" + wxGoodsTag + ", wxLimitPay=" + wxLimitPay + ", aliGoodsDetail=" + aliGoodsDetail
				+ ", aliExtendParams=" + aliExtendParams + "]";
	}

	
}
