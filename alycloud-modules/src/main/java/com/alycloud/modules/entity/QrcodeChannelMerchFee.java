package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alycloud.modules.enums.QrcodeChannelMerchFeeStatus;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.QrcodeScanType;
import com.alycloud.modules.enums.SysSettleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 渠道商户二维码费率
 * @author Moyq5
 * @date 2017年10月9日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class QrcodeChannelMerchFee implements Serializable {
	
	private Integer id;
	private Integer channelMerchId;// 渠道商户ID
	/**
	 * @see {@link QrcodePayType}
	 */
	private Integer payType; // 支付方式，多选
	/**
	 * @see {@link QrcodeScanType}
	 */
	private Integer scanType;// 扫码类型，多选
	/**
	 * @see {@link SysSettleType}
	 */
	private Integer settleType;// 结算类型，多选
	private BigDecimal rate;// 商户费率信息
	private BigDecimal fee;// 单笔提现费，元
	/**
	 * @see {@link QrcodeChannelMerchFeeStatus}
	 */
	private Integer status;// 状态
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getChannelMerchId() {
		return channelMerchId;
	}
	public void setChannelMerchId(Integer channelMerchId) {
		this.channelMerchId = channelMerchId;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
