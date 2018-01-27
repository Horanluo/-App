package com.alycloud.channel.pingan.bean;

import java.util.Date;

import com.alycloud.channel.pingan.enums.OrderStatus;
import com.alycloud.channel.pingan.enums.OrderType;
import com.alycloud.channel.pingan.enums.PaymentTag;
import com.alycloud.channel.pingan.jackson.converter.DateDeserialize;
import com.alycloud.channel.pingan.jackson.converter.OrderStatusDeserialize;
import com.alycloud.channel.pingan.jackson.converter.OrderTypeDeserialize;
import com.alycloud.channel.pingan.jackson.converter.PaymentTagDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 查询付款状态接口响应data参数对象，需要将CommonResult对象的属性data解密并转换成该对象
 * @author Moyq5
 * @date 2017年6月14日
 */
public class PayStatusResult {

	/**
	 * 收单机构名称（支付方式名称），必填
	 */
	@JsonProperty("pmt_name")
	private String paymentName;
	
	/**
	 * 收单机构标签（支付方式），必填
	 */
	@JsonProperty("pmt_tag")
	@JsonDeserialize(converter = PaymentTagDeserialize.class)
	private PaymentTag paymentTag;
	
	/**
	 * 商户流水号（从1开始自增长不重复），必填
	 */
	@JsonProperty("ord_mct_id")
	private Long merchOrderId;
	
	/**
	 * 门店流水号（从1开始自增长不重复），必填
	 */
	@JsonProperty("ord_shop_id")
	private Long shopOrderId;
	
	/**
	 * 订单名称（描述），必填
	 */
	@JsonProperty("ord_name")
	private String orderName;
	
	/**
	 * 下单时间例如：2017-05-08 17:00:00，必填
	 */
	@JsonProperty("add_time")
	@JsonDeserialize(converter = DateDeserialize.class)
	private Date addTime;
	
	/**
	 * 订单号，必填
	 */
	@JsonProperty("ord_no")
	private String orderNo;
	
	/**
	 * 订单类型，必填
	 */
	@JsonProperty("ord_type")
	@JsonDeserialize(converter = OrderTypeDeserialize.class)
	private OrderType orderType;
	
	/**
	 * 原始金额（以分为单位，没有小数点），必填
	 */
	@JsonProperty("original_amount")
	private Integer originalAmount;
	
	/**
	 * 折扣金额（以分为单位，没有小数点），必填
	 */
	@JsonProperty("discount_amount")
	private Integer discountAmount;
	
	/**
	 * 抹零金额（以分为单位，没有小数点），必填
	 */
	@JsonProperty("ignoreAmount")
	private Integer ignoreAmount;
	
	/**
	 * 交易帐号（银行卡号、支付宝帐号、微信帐号等，某些收单机构没有此数据）
	 */
	@JsonProperty("trade_account")
	private String tradeAccount;
	
	/**
	 * 收单机构交易号
	 */
	@JsonProperty("trade_no")
	private String tradeNo;
	
	/**
	 * 实际交易金额（以分为单位，没有小数点），必填
	 */
	@JsonProperty("trade_amount")
	private Integer tradeAmount;
	
	/**
	 * 交易时间，必填
	 */
	@JsonProperty("trade_time")
	@JsonDeserialize(converter = DateDeserialize.class)
	private Date tradeTime;
	
	/**
	 * 二维码字符串
	 */
	@JsonProperty("trade_qrcode")
	private String tradeQrcode;
	
	/**
	 * 付款完成时间（以收单机构为准）
	 */
	@JsonProperty("trade_pay_time")
	@JsonDeserialize(converter = DateDeserialize.class)
	private Date tradePayTime;
	
	/**
	 * 备注，必填
	 */
	private String remark;
	
	/**
	 * 订单状态，必填
	 */
	@JsonDeserialize(converter = OrderStatusDeserialize.class)
	private OrderStatus status;
	
	/**
	 * 原始订单号，必填
	 */
	@JsonProperty("original_ord_no")
	private String originalOrderNo;
	
	/**
	 * 收单机构原始交易数据，必填
	 */
	@JsonProperty("trade_result")
	private String tradeResult;
	
	/**
	 * 币种，必填
	 */
	private String currency;
	
	/**
	 * 币种符号，必填
	 */
	@JsonProperty("currency_sign")
	private String currencySign;
	
	/**
	 * 开发者流水号，必填
	 */
	@JsonProperty("out_no")
	private String customOrderNo;
	
	/**
	 * 订单标签，必填
	 */
	private String tag;
	
	/**
	 * 收银员编号，必填
	 */
	@JsonProperty("scr_id")
	private String cashierId;
	
	/**
	 * 门店号，必填
	 */
	@JsonProperty("shop_no")
	private String shopNo;

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public PaymentTag getPaymentTag() {
		return paymentTag;
	}

	public void setPaymentTag(PaymentTag paymentTag) {
		this.paymentTag = paymentTag;
	}

	public Long getMerchOrderId() {
		return merchOrderId;
	}

	public void setMerchOrderId(Long merchOrderId) {
		this.merchOrderId = merchOrderId;
	}

	public Long getShopOrderId() {
		return shopOrderId;
	}

	public void setShopOrderId(Long shopOrderId) {
		this.shopOrderId = shopOrderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
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

	public Integer getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Integer tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeQrcode() {
		return tradeQrcode;
	}

	public void setTradeQrcode(String tradeQrcode) {
		this.tradeQrcode = tradeQrcode;
	}

	public Date getTradePayTime() {
		return tradePayTime;
	}

	public void setTradePayTime(Date tradePayTime) {
		this.tradePayTime = tradePayTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getOriginalOrderNo() {
		return originalOrderNo;
	}

	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}

	public String getTradeResult() {
		return tradeResult;
	}

	public void setTradeResult(String tradeResult) {
		this.tradeResult = tradeResult;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencySign() {
		return currencySign;
	}

	public void setCurrencySign(String currencySign) {
		this.currencySign = currencySign;
	}

	public String getCustomOrderNo() {
		return customOrderNo;
	}

	public void setCustomOrderNo(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCashierId() {
		return cashierId;
	}

	public void setCashierId(String cashierId) {
		this.cashierId = cashierId;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	@Override
	public String toString() {
		return "PayStatusResult [paymentName=" + paymentName + ", paymentTag=" + paymentTag + ", merchOrderId="
				+ merchOrderId + ", shopOrderId=" + shopOrderId + ", orderName=" + orderName + ", addTime=" + addTime
				+ ", orderNo=" + orderNo + ", orderType=" + orderType + ", originalAmount=" + originalAmount
				+ ", discountAmount=" + discountAmount + ", ignoreAmount=" + ignoreAmount + ", tradeAccount="
				+ tradeAccount + ", tradeNo=" + tradeNo + ", tradeAmount=" + tradeAmount + ", tradeTime=" + tradeTime
				+ ", tradeQrcode=" + tradeQrcode + ", tradePayTime=" + tradePayTime + ", remark=" + remark + ", status="
				+ status + ", originalOrderNo=" + originalOrderNo + ", tradeResult=" + tradeResult + ", currency="
				+ currency + ", currencySign=" + currencySign + ", customOrderNo=" + customOrderNo + ", tag=" + tag
				+ ", cashierId=" + cashierId + ", shopNo=" + shopNo + "]";
	}
	
	
}
