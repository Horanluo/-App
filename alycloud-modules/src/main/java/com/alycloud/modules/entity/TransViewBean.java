package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 交易订单视图
 * @author Moyq5
 * @date 2017年4月27日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class TransViewBean implements Serializable {

	private String merchno;// 商户号
	private String orderno;// 系统订单号（我们系统自己生成的单号）
	private BigDecimal amount;// 交易金额（元）
	private BigDecimal merchFee;// （商户）手续费（元）
	private Integer payType; // 支付方式：0-快捷支付1-支付宝 2-微信 3-百度 4-QQ 5-京东 6-翼支付
	private Integer payStatus;// 订单付款状态：0未付款、1付款成功、2付款失败
	private String payDescr;// 付款状态描述
	private Integer transType;// 交易类型：0-二维码交易，1-无卡交易
	private String transTime;// 创建时间：yyyy-MM-dd HH:mm:ss
	public String getMerchno() {
		return merchno;
	}
	public void setMerchno(String merchno) {
		this.merchno = merchno;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getMerchFee() {
		return merchFee;
	}
	public void setMerchFee(BigDecimal merchFee) {
		this.merchFee = merchFee;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayDescr() {
		return payDescr;
	}
	public void setPayDescr(String payDescr) {
		this.payDescr = payDescr;
	}
	public Integer getTransType() {
		return transType;
	}
	public void setTransType(Integer transType) {
		this.transType = transType;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	@Override
	public String toString() {
		return "TransViewBean [merchno=" + merchno + ", orderno=" + orderno + ", amount=" + amount + ", merchFee="
				+ merchFee + ", payType=" + payType + ", payStatus=" + payStatus + ", payDescr=" + payDescr
				+ ", transType=" + transType + ", transTime=" + transTime + "]";
	}
	
}
