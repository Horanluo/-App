package com.alycloud.modules.search;

import java.math.BigDecimal;

/**
 * 二维码交易－渠道商户查询条件对象
 * @author Moyq5
 * @date 2017年6月20日
 */
public class QrcodeMerch4Search4Pay {
	
	private Integer payType;
	private Integer scanType;
	private Integer settleType;
	private BigDecimal amount;
	private String time;
	private String merchno;
	private String agentno;
	private String branchno;
	private String channelCode;
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMerchno() {
		return merchno;
	}
	public void setMerchno(String merchno) {
		this.merchno = merchno;
	}
	public String getAgentno() {
		return agentno;
	}
	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}
	public String getBranchno() {
		return branchno;
	}
	public void setBranchno(String branchno) {
		this.branchno = branchno;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
}
