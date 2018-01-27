package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alycloud.modules.enums.QrcodeMerchPartnerStatus;
import com.alycloud.modules.enums.QrcodeMerchPartnerType;
import com.alycloud.modules.enums.QrcodeMerchStatus;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.QrcodeScanType;
import com.alycloud.modules.enums.SysLiquidateType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.enums.SysT0RateType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// 

/**
 * 二维码渠道商户
 * @author Moyq5
 * @date 2017年6月20日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class QrcodeMerch implements Serializable {
	private Integer id; // 
	private String merchno;// 平台商户号
	private String channelCode;// 渠道编号
	private String merchName; // 渠道商户名称
	private String partnerId; // 渠道商户号
	/**
	 * @see {@link QrcodeMerchPartnerType}
	 */
	private Integer partnerType;// 渠道商户类型
	/**
	 * @see {@link QrcodeMerchPartnerStatus}
	 */
	private Integer partnerStatus;// 渠道商户审核状态
	private String partnerDescr;// 渠道商户审核状态描述
	private String termno;// 终端号
	/**
	 * @see {@link QrcodeMerchStatus}
	 */
	private Integer status;// 启动状态
	/**
	 * @see {@link QrcodeScanType}
	 */
	private Integer scanType;// 扫码类型，多选
	/**
	 * @see {@link QrcodePayType}
	 */
	private Integer payType;// 支付方式，多选
	/**
	 * @see {@link SysSettleType}
	 */
	private Integer settleType;// 结算类型，多选
	private BigDecimal dfFee;// 代付手续费
	/**
	 * @see {@link SysT0RateType}
	 */
	private Integer t0RateType;// T0费率类型
	private BigDecimal t0MinFee;// T0保底费用
	/**
	 * @see {@link SysLiquidateType}
	 */
	private Integer liquidateType;// 清算方
	private String appId;// 微信公众号APPID
	private String appKey;// 微信公众号SECRETKEY
	private String merchKey;// 渠道商户密钥
	private String keyRsa;// 渠道商户RSA密钥
	private String t0StartTime;// 每天t0开始时间
	private String t0EndTime;// 每天t0结束时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMerchno() {
		return merchno;
	}
	public void setMerchno(String merchno) {
		this.merchno = merchno;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getMerchName() {
		return merchName;
	}
	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public Integer getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(Integer partnerType) {
		this.partnerType = partnerType;
	}
	public Integer getPartnerStatus() {
		return partnerStatus;
	}
	public void setPartnerStatus(Integer partnerStatus) {
		this.partnerStatus = partnerStatus;
	}
	public String getPartnerDescr() {
		return partnerDescr;
	}
	public void setPartnerDescr(String partnerDescr) {
		this.partnerDescr = partnerDescr;
	}
	public String getTermno() {
		return termno;
	}
	public void setTermno(String termno) {
		this.termno = termno;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getScanType() {
		return scanType;
	}
	public void setScanType(Integer scanType) {
		this.scanType = scanType;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getSettleType() {
		return settleType;
	}
	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}
	public BigDecimal getDfFee() {
		return dfFee;
	}
	public void setDfFee(BigDecimal dfFee) {
		this.dfFee = dfFee;
	}
	public Integer getT0RateType() {
		return t0RateType;
	}
	public void setT0RateType(Integer t0RateType) {
		this.t0RateType = t0RateType;
	}
	public BigDecimal getT0MinFee() {
		return t0MinFee;
	}
	public void setT0MinFee(BigDecimal t0MinFee) {
		this.t0MinFee = t0MinFee;
	}
	public Integer getLiquidateType() {
		return liquidateType;
	}
	public void setLiquidateType(Integer liquidateType) {
		this.liquidateType = liquidateType;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getMerchKey() {
		return merchKey;
	}
	public void setMerchKey(String merchKey) {
		this.merchKey = merchKey;
	}
	public String getKeyRsa() {
		return keyRsa;
	}
	public void setKeyRsa(String keyRsa) {
		this.keyRsa = keyRsa;
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
	
}
