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
 * 下单接口响应data参数对象，需要将CommonResult对象的属性data解密并转换成该对象
 * @author Moyq5
 * @date 2017年6月14日
 */
public class PayOrderResult {

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
	 * 订单号，必填
	 */
	@JsonProperty("ord_no")
	private String orderNo;
	
	/**
	 * 订单类型
	 */
	@JsonProperty("ord_type")
	@JsonDeserialize(converter = OrderTypeDeserialize.class)
	private OrderType orderType;
	
	/**
	 * 原始金额（以分为单位，没有小数点）
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
	 * 订单状态，必填
	 */
	@JsonDeserialize(converter = OrderStatusDeserialize.class)
	private OrderStatus status;
	
	/**
	 * 收单机构原始交易数据，必填
	 */
	@JsonProperty("trade_result")
	private String tradeResult;
	
	/**
	 * 开发者流水号，必填
	 */
	@JsonProperty("out_no")
	private String customOrderNo;
	
	/**
	 * 公众号订单支付地址，如果为非公众号订单，此参数为空
	 */
	@JsonProperty("jsapi_pay_url")
	private String jsApiPayUrl;
	
	/**
	 * 微信APP支付返回参数：app端调用微信支付的_partnerid参数，如果为非微信APP支付订单，此参数为空
	 */
	@JsonProperty("wxapp_partnerid")
	private String wxAppPartnerId;
	
	/**
	 * 微信APP支付返回参数：app端调用微信支付的_prepayid参数，如果为非微信APP支付订单，此参数为空
	 */
	@JsonProperty("wxapp_prepayid")
	private String wxAppPrepayId;
	
	/**
	 * 微信APP支付返回参数：app端调用微信支付的_package参数，如果为非微信APP支付订单，此参数为空
	 */
	@JsonProperty("wxapp_package")
	private String wxAppPackage;
	
	/**
	 * 微信APP支付返回参数：app端调用微信支付的noncestr参数，如果为非微信APP支付订单，此参数为空
	 */
	@JsonProperty("wxapp_noncestr")
	private String wxAppNonceStr;
	
	/**
	 * 微信APP支付返回参数：app端调用微信支付的_timestamp参数，如果为非微信APP支付订单，此参数为空
	 */
	@JsonProperty("swxapp_timestamp")
	private String wxAppTimestamp;
	
	/**
	 * 微信APP支付返回参数：app端调用微信支付的sign参数，如果为非微信APP支付订单，此参数为空
	 */
	@JsonProperty("wxapp_sign")
	private String wxAppSign;

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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getTradeResult() {
		return tradeResult;
	}

	public void setTradeResult(String tradeResult) {
		this.tradeResult = tradeResult;
	}

	public String getCustomOrderNo() {
		return customOrderNo;
	}

	public void setCustomOrderNo(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}

	public String getJsApiPayUrl() {
		return jsApiPayUrl;
	}

	public void setJsApiPayUrl(String jsApiPayUrl) {
		this.jsApiPayUrl = jsApiPayUrl;
	}

	public String getWxAppPartnerId() {
		return wxAppPartnerId;
	}

	public void setWxAppPartnerId(String wxAppPartnerId) {
		this.wxAppPartnerId = wxAppPartnerId;
	}

	public String getWxAppPrepayId() {
		return wxAppPrepayId;
	}

	public void setWxAppPrepayId(String wxAppPrepayId) {
		this.wxAppPrepayId = wxAppPrepayId;
	}

	public String getWxAppPackage() {
		return wxAppPackage;
	}

	public void setWxAppPackage(String wxAppPackage) {
		this.wxAppPackage = wxAppPackage;
	}

	public String getWxAppNonceStr() {
		return wxAppNonceStr;
	}

	public void setWxAppNonceStr(String wxAppNonceStr) {
		this.wxAppNonceStr = wxAppNonceStr;
	}

	public String getWxAppTimestamp() {
		return wxAppTimestamp;
	}

	public void setWxAppTimestamp(String wxAppTimestamp) {
		this.wxAppTimestamp = wxAppTimestamp;
	}

	public String getWxAppSign() {
		return wxAppSign;
	}

	public void setWxAppSign(String wxAppSign) {
		this.wxAppSign = wxAppSign;
	}

	@Override
	public String toString() {
		return "PayOrderResult [payName=" + paymentName + ", payType=" + paymentTag + ", merchOrderId=" + merchOrderId
				+ ", shopOrderId=" + shopOrderId + ", orderNum=" + orderNo + ", orderType=" + orderType
				+ ", originalAmount=" + originalAmount + ", discountAmount=" + discountAmount + ", ignoreAmount="
				+ ignoreAmount + ", tradeAccount=" + tradeAccount + ", tradeNum=" + tradeNo + ", tradeAmount="
				+ tradeAmount + ", tradeQrcode=" + tradeQrcode + ", tradePayTime=" + tradePayTime + ", status=" + status
				+ ", tradeResult=" + tradeResult + ", customOrderNum=" + customOrderNo + ", jsApiPayUrl=" + jsApiPayUrl
				+ ", wxAppPartnerId=" + wxAppPartnerId + ", wxAppPrepayId=" + wxAppPrepayId + ", wxAppPackage="
				+ wxAppPackage + ", wxAppNonceStr=" + wxAppNonceStr + ", wxAppTimestamp=" + wxAppTimestamp
				+ ", wxAppSign=" + wxAppSign + "]";
	}

}
