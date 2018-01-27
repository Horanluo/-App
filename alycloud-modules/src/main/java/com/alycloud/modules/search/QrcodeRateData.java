package com.alycloud.modules.search;

import java.math.BigDecimal;

/**
 * 二维码费率信息
 * @author Moyq5
 * @date 2017年7月14日
 */
public class QrcodeRateData {

	private Integer payType;// 支付方式
	private Integer scanType;// 扫码方式，多选
	private Integer settleType;// 结算方式，多选
	private BigDecimal rate;// 费率
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
