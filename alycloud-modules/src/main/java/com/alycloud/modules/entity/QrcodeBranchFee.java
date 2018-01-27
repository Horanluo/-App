package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.QrcodeScanType;
import com.alycloud.modules.enums.SysSettleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 机构二维码费率
 * @author Moyq5
 * @date 2017年10月18日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class QrcodeBranchFee implements Serializable {
	
	private Integer id;
	private String branchno;// 机构编码
	/**
	 * @see {@link QrcodePayType}
	 */
	private Integer payType; // 支付方式
	/**
	 * @see {@link QrcodeScanType}
	 */
	private Integer scanType;// 扫码类型，多选
	/**
	 * @see {@link SysSettleType}
	 */
	private Integer settleType;// 结算类型，多选
	private BigDecimal rate;// 费率
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBranchno() {
		return branchno;
	}
	public void setBranchno(String branchno) {
		this.branchno = branchno;
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
 
}
