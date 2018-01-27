package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alycloud.modules.enums.ChannelCalculateType;
import com.alycloud.modules.enums.ChannelStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 二维码渠道信息
 * @author Moyq5
 * @date 2017年9月28日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class Channel implements Serializable {

	private Integer id;
	private String channelCode;// 渠道编码
	private String channelName;// 渠道名称
	private String channelAlias;// 渠道别名（向用户展示的名称）
	private String payUrl;// 支付地址
	private String payReturnUrl;// 同步通知地址
	private String payNotifyUrl;// 异步通知地址
	private String paymentUrl;// 代付地址
	private String paymentNotifyUrl;// 代付异步通知地址
	/**
	 * @see {@link ChannelCalculateType}
	 */
	private Integer calculateType;// 计费方式
	/**
	 * @see {@link ChannelStatus}
	 */
	private Integer status;// 状态
	private String keyPrivate;// RSA私钥
	private String keyPublic;// RSA公钥
	private String keyMd5;// MD5密钥
	private Integer t0Status;// t0状态:0不支持,1需要提现,2不需要提现
	private Integer t1Status;// t1状态:0不支持,1需要提现,2不需要提现
	private BigDecimal amountMin;// 单笔最小金额
	private BigDecimal amountMax;// 单笔最大金额
	private BigDecimal amountDay;// 日最高交易额
	private String t0StartTime;// T0起始时间点
	private String t0EndTime;// T0结束时间点
	private String remark;// 备注
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getPayUrl() {
		return payUrl;
	}
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	public String getPayReturnUrl() {
		return payReturnUrl;
	}
	public void setPayReturnUrl(String payReturnUrl) {
		this.payReturnUrl = payReturnUrl;
	}
	public String getPayNotifyUrl() {
		return payNotifyUrl;
	}
	public void setPayNotifyUrl(String payNotifyUrl) {
		this.payNotifyUrl = payNotifyUrl;
	}
	public String getPaymentUrl() {
		return paymentUrl;
	}
	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	public String getPaymentNotifyUrl() {
		return paymentNotifyUrl;
	}
	public void setPaymentNotifyUrl(String paymentNotifyUrl) {
		this.paymentNotifyUrl = paymentNotifyUrl;
	}
	public Integer getCalculateType() {
		return calculateType;
	}
	public void setCalculateType(Integer calculateType) {
		this.calculateType = calculateType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getKeyPrivate() {
		return keyPrivate;
	}
	public void setKeyPrivate(String keyPrivate) {
		this.keyPrivate = keyPrivate;
	}
	public String getKeyPublic() {
		return keyPublic;
	}
	public void setKeyPublic(String keyPublic) {
		this.keyPublic = keyPublic;
	}
	public String getKeyMd5() {
		return keyMd5;
	}
	public void setKeyMd5(String keyMd5) {
		this.keyMd5 = keyMd5;
	}
	public Integer getT0Status() {
		return t0Status;
	}
	public void setT0Status(Integer t0Status) {
		this.t0Status = t0Status;
	}
	public Integer getT1Status() {
		return t1Status;
	}
	public void setT1Status(Integer t1Status) {
		this.t1Status = t1Status;
	}
	public BigDecimal getAmountMin() {
		return amountMin;
	}
	public void setAmountMin(BigDecimal amountMin) {
		this.amountMin = amountMin;
	}
	public BigDecimal getAmountMax() {
		return amountMax;
	}
	public void setAmountMax(BigDecimal amountMax) {
		this.amountMax = amountMax;
	}
	public BigDecimal getAmountDay() {
		return amountDay;
	}
	public void setAmountDay(BigDecimal amountDay) {
		this.amountDay = amountDay;
	}
	public String getT0StartTime() {
		return t0StartTime;
	}
	public void setT0StartTime(String t0StartTime) {
		this.t0StartTime = t0StartTime;
	}
	public String getT0EndTime() {
		return t0EndTime;
	}
	public void setT0EndTime(String t0EndTime) {
		this.t0EndTime = t0EndTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getChannelAlias() {
		return channelAlias;
	}
	public void setChannelAlias(String channelAlias) {
		this.channelAlias = channelAlias;
	}
	
}
