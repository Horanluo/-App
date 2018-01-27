package com.alycloud.modules.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 商户虚拟账户充值记录
 * 
 * @author Moyq5
 * @date 2017年3月15日
 */
@JsonInclude(value=Include.NON_NULL)
public class AgentVirtualRecharge {

	private Integer id;
	private String cardno; // 虚拟账户
	private String branchno; // 机构号
	private String branchName; // 机构名称
	private String agentno; // 代理商号
	private String agentName; // 代理商名称
	private Integer type; // 充值类型(0-二维码交易充值)
	private BigDecimal amountBefore; // 充值前余额
	private BigDecimal amount; // 充值金额
	private BigDecimal amountAfter; // 充值后余额
	private Integer status; // 充值状态(0-下单成功 1-处理中 2-充值成功 3-充值失败)
	private String remark; // 备注
	private String refno;// 原交易订单号,多个用“,”号隔开
	private String loginName; // 操作员
	private String addTime; // 创建时间
	private String ip; // 交易地址
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getBranchno() {
		return branchno;
	}
	public void setBranchno(String branchno) {
		this.branchno = branchno;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAgentno() {
		return agentno;
	}
	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public BigDecimal getAmountBefore() {
		return amountBefore;
	}
	public void setAmountBefore(BigDecimal amountBefore) {
		this.amountBefore = amountBefore;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getAmountAfter() {
		return amountAfter;
	}
	public void setAmountAfter(BigDecimal amountAfter) {
		this.amountAfter = amountAfter;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRefno() {
		return refno;
	}
	public void setRefno(String refno) {
		this.refno = refno;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "AgentVirtualRechargeBean [id=" + id + ", cardno=" + cardno + ", branchno=" + branchno + ", branchName="
				+ branchName + ", agentno=" + agentno + ", agentName=" + agentName + ", type=" + type
				+ ", amountBefore=" + amountBefore + ", amount=" + amount + ", amountAfter=" + amountAfter + ", status="
				+ status + ", remark=" + remark + ", refno=" + refno + ", loginName=" + loginName + ", addTime="
				+ addTime + ", ip=" + ip + "]";
	}
	
}
