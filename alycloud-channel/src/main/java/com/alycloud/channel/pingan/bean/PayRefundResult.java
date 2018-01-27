package com.alycloud.channel.pingan.bean;

import java.util.Date;

import com.alycloud.channel.pingan.enums.RefundStatus;
import com.alycloud.channel.pingan.jackson.converter.DateDeserialize;
import com.alycloud.channel.pingan.jackson.converter.RefundStatusDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 订单退款接口响应data参数对象，需要将CommonResult对象的属性data解密并转换成该对象
 * @author Moyq5
 * @date 2017年7月10日
 */
public class PayRefundResult {

	/**
	 * 退款订单号，必填，20
	 */
	@JsonProperty("ord_no")
	private String orderNo;
	
	/**
	 * 商户流水号，必填，11
	 */
	@JsonProperty("ord_mct_id")
	private Long merchOrderId;
	
	/**
	 * 门店流水号，必填，11
	 */
	@JsonProperty("ord_shop_id")
	private Long shopOrderId;
	
	/**
	 * 退款金额，必填，20
	 */
	@JsonProperty("trade_amount")
	private Integer tradeAmount;
	
	/**
	 * 收单机构交易号，必填，50
	 */
	@JsonProperty("trade_no")
	private String tradeNo;
	
	/**
	 * 收单机构原始交易数据，必填，4000
	 */
	@JsonProperty("trade_result")
	private String tradeResult;
	
	/**
	 * 原始订单号，必填，32
	 */
	@JsonProperty("original_ord_no")
	private String originalOrderNo;
	
	/**
	 * 订单状态，必填
	 */
	@JsonDeserialize(converter = RefundStatusDeserialize.class)
	private RefundStatus status;
	
	/**
	 * 币种，必填，10
	 */
	@JsonProperty("ord_currency")
	private String currency;
	
	/**
	 * 币种符号，必填，10
	 */
	@JsonProperty("currency_sign")
	private String currencySign;
	
	/**
	 * 开发者流水号，必填，20
	 */
	@JsonProperty("out_no")
	private String customOrderNo;
	
	/**
	 * 交易时间，必填
	 */
	@JsonProperty("trade_time")
	@JsonDeserialize(converter = DateDeserialize.class)
	private Date tradeTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public Integer getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Integer tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeResult() {
		return tradeResult;
	}

	public void setTradeResult(String tradeResult) {
		this.tradeResult = tradeResult;
	}

	public String getOriginalOrderNo() {
		return originalOrderNo;
	}

	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}

	public RefundStatus getStatus() {
		return status;
	}

	public void setStatus(RefundStatus status) {
		this.status = status;
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

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Override
	public String toString() {
		return "PayRefundResult [orderNo=" + orderNo + ", merchOrderId=" + merchOrderId + ", shopOrderId=" + shopOrderId
				+ ", tradeAmount=" + tradeAmount + ", tradeNo=" + tradeNo + ", tradeResult=" + tradeResult
				+ ", originalOrderNo=" + originalOrderNo + ", status=" + status + ", currency=" + currency
				+ ", currencySign=" + currencySign + ", customOrderNo=" + customOrderNo + ", tradeTime=" + tradeTime
				+ "]";
	}
	
}
