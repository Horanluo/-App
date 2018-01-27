package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 商户升级付款记录
 * @author Moyq5
 * @date 2017年7月13日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class MerchUpgrade implements Serializable {
	
	private Integer id;
	private String branchno;// 机构编码
	private String agentno;// 代理商编号
	private String merchno;// 商户编号（付款人）
	private Integer userId;// 操作员（付款人）
	private Integer type;// 升级类型
	private Integer status;// 审核状态
	private BigDecimal transAmount;// 交易金额
	private BigDecimal totalFee;// 交易手续费
	private Integer transType;// 交易方式
	private String refno;// 原交易单号
	private String rule;// 升级规则
	private String rateBefore;// 付款前费率信息
	private String rateAfter;// 付款后费率信息
	private String transDate;// 付款日期
	private String transTime;// 付款时间
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
	public String getMerchno() {
		return merchno;
	}
	public void setMerchno(String merchno) {
		this.merchno = merchno;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public Integer getTransType() {
		return transType;
	}
	public void setTransType(Integer transType) {
		this.transType = transType;
	}
	public String getRefno() {
		return refno;
	}
	public void setRefno(String refno) {
		this.refno = refno;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getRateBefore() {
		return rateBefore;
	}
	public void setRateBefore(String rateBefore) {
		this.rateBefore = rateBefore;
	}
	public String getRateAfter() {
		return rateAfter;
	}
	public void setRateAfter(String rateAfter) {
		this.rateAfter = rateAfter;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	
}
