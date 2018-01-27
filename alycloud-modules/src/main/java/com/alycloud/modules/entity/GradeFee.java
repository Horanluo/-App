package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.QrcodeScanType;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysSettleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 商户等级费率
 * @author Moyq5
 * @date 2017年10月29日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class GradeFee implements Serializable {
	private Integer id;
	private Integer gradeType;//  等级
	/**
	 * @see {@link SysChannelType}
	 */
	private Integer channelType;// 渠道类型，多选
	/**
	 * @see {@link QrcodePayType}
	 */
	private Integer payType;// 支付类型，多选
	/**
	 * @see {@link QrcodeScanType}
	 */
	private Integer scanType;// 扫码方式，多选
	/**
	 * @see {@link SysSettleType}
	 */
	private Integer settleType;// 结算类型，多选
	private BigDecimal rate;// 费率
	private BigDecimal fee;// 代付费（元）
	private BigDecimal cap;// 封顶费（元）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGradeType() {
		return gradeType;
	}
	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getScanType() {
		return scanType;
	}
	public void setScanType(Integer scanType) {
		this.scanType = scanType;
	}
	public Integer getSettleType() {
		return settleType;
	}
	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public Integer getChannelType() {
		return channelType;
	}
	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}
	public BigDecimal getCap() {
		return cap;
	}
	public void setCap(BigDecimal cap) {
		this.cap = cap;
	}
	
}
