package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 代理商二维码渠道费率
 * 
 * @author Moyq5
 * @date 2017年7月15日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class QrcodeAgentFee implements Serializable {
	private Integer id;
	private String branchno;
	private String agentno;
	private Integer payType; // 支付方式
	private Integer scanType; // 扫码类型（多选）
	private Integer settleType; // 结算类型（多选）
	private BigDecimal rate; // 渠道费率
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
	public String getAgentno() {
		return agentno;
	}
	public void setAgentno(String agentno) {
		this.agentno = agentno;
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
