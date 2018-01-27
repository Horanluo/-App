package com.alycloud.modules.entity; // 

import java.io.Serializable;
import java.math.BigDecimal; // 

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 二维码渠道商户路由
 * @author Moyq5
 * @date 2017年6月19日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class QrcodeRoute implements Serializable {
	private Integer id; // 
	private String routeName; // 路由名称
	private String branchno;// 机构号
	private String agentno;// 代理商号
	private String merchno;// 商户号
	private Integer status;// 启动状态
	private Integer interType;// 接口类型, 多选
	private Integer scanType;// 扫码类型，多选
	private Integer payType;// 支付方式，多选
	private Integer settleType;// 结算类型，多选
	private BigDecimal amtStart;// 起始金额
	private BigDecimal amtEnd;// 截止金额
	private String timeStart;// 起始时间
	private String timeEnd;// 截止时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getInterType() {
		return interType;
	}
	public void setInterType(Integer interType) {
		this.interType = interType;
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
	public BigDecimal getAmtStart() {
		return amtStart;
	}
	public void setAmtStart(BigDecimal amtStart) {
		this.amtStart = amtStart;
	}
	public BigDecimal getAmtEnd() {
		return amtEnd;
	}
	public void setAmtEnd(BigDecimal amtEnd) {
		this.amtEnd = amtEnd;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	
}
