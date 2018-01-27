package com.alycloud.channel.pingan.bean;

import com.alycloud.channel.pingan.support.utils.SHA1;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 订单退款接口data参数对象，需要转换成JSON并加密赋给<code>CommonData</code>对象的<code>data</code>属性
 * @author Moyq5
 * @date 2017年7月10日
 */
public class PayRefundData {

	/**
	 * 原始订单的开发者交易流水号，必填，20
	 */
	@JsonProperty("out_no")
	private String customOrderNo;
	
	/**
	 * 新退款订单的开发者流水号，同一门店内唯一，必填，20
	 */
	@JsonProperty("refund_out_no")
	private String customRefundOrderNo;
	
	/**
	 * 退款订单名称，可以为空，50
	 */
	@JsonProperty("refund_ord_name")
	private String refundOrderName;
	
	/**
	 * 退款金额（以分为单位，没有小数点），20
	 */
	@JsonProperty("refund_amount")
	private Integer refundAmount;
	
	/**
	 * 交易帐号（收单机构交易的银行卡号，手机号等，可为空），50
	 */
	@JsonProperty("trade_account")
	private String tradeAccount;
	
	/**
	 * 交易号（收单机构交易号，可为空），50
	 */
	@JsonProperty("trade_no")
	private String tradeNo;
	
	/**
	 * 收单机构原始交易信息，请转换为json数据，4000
	 */
	@JsonProperty("trade_result")
	private String tradeResult;
	
	/**
	 * 终端令牌，终端上线后获得的令牌，32
	 */
	@JsonProperty("tml_token")
	private String terminalToken;
	
	/**
	 * 退款备注，100
	 */
	private String remark;
	
	/**
	 * 主管密码，对密码进行sha1加密，默认为123456，必填
	 */
	@JsonProperty("shop_pass")
	private String shopPass;

	public String getCustomOrderNo() {
		return customOrderNo;
	}

	public void setCustomOrderNo(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}

	public String getCustomRefundOrderNo() {
		return customRefundOrderNo;
	}

	public void setCustomRefundOrderNo(String customRefundOrderNo) {
		this.customRefundOrderNo = customRefundOrderNo;
	}

	public String getRefundOrderName() {
		return refundOrderName;
	}

	public void setRefundOrderName(String refundOrderName) {
		this.refundOrderName = refundOrderName;
	}

	public Integer getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
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

	public String getTradeResult() {
		return tradeResult;
	}

	public void setTradeResult(String tradeResult) {
		this.tradeResult = tradeResult;
	}

	public String getTerminalToken() {
		return terminalToken;
	}

	public void setTerminalToken(String terminalToken) {
		this.terminalToken = terminalToken;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShopPass() {
		return shopPass;
	}

	public void setShopPass(String shopPass) {
		shopPass = SHA1.encrypt(shopPass);
		this.shopPass = shopPass;
	}

	@Override
	public String toString() {
		return "PayRefundData [customOrderNo=" + customOrderNo + ", customRefundOrderNo=" + customRefundOrderNo
				+ ", refundOrderName=" + refundOrderName + ", refundAmount=" + refundAmount + ", tradeAccount="
				+ tradeAccount + ", tradeNo=" + tradeNo + ", tradeResult=" + tradeResult + ", terminalToken="
				+ terminalToken + ", remark=" + remark + ", shopPass=" + shopPass + "]";
	}
	
}
