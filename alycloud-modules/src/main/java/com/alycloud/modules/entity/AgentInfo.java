package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 代理商信息
 * @author Moyq5
 * @date 2017年5月19日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class AgentInfo implements Serializable {

	private Integer id;
	/**
	 * 代理商编号(4位地区代码+6位顺序号)
	 */
	private String agentno;
	/**
	 * 代理商简称
	 */
	private String agentName;
	/**
	 * 代理商全称
	 */
	private String fullName;
	/**
	 * 代理商在运营系统的分成比例(范围:0-100)
	 */
	private Integer divide;
	/**
	 * 法人姓名
	 */
	private String legalName;
	/**
	 * 法人身份证
	 */
	private String identityCard;
	/**
	 * 联系人
	 */
	private String linkMan;
	/**
	 * 联系人电子邮箱
	 */
	private String email;
	/**
	 * 联系人固定电话
	 */
	private String telephone;
	/**
	 * 联系人手机号码
	 */
	private String mobile;
	/**
	 * 加盟费
	 */
	private BigDecimal memberFee;
	/**
	 * 保证金
	 */
	private BigDecimal assuranceFee;
	/**
	 * 地区代码
	 */
	private String areaCode;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 上级代理商  
	 */
	private String superAgent;
	/**
	 * 开通状态：AgentStatus
	 */
	private Integer status;
	/**
	 * 开通时间
	 */
	private String openTime;
	/**
	 * 审核状态：AgentAuditStatus
	 */
	private Integer auditStatus;
	/**
	 * 审核对象
	 */
	private String auditAgentno;
	/**
	 * 收款账号
	 */
	private String accountno;
	/**
	 * 帐户类型：SysBankAccountType
	 */
	private Integer accountType;
	/**
	 * 帐户户名
	 */
	private String accountName;
	/**
	 * 开户行行号
	 */
	private String bankno;
	/**
	 * 开户行行名
	 */
	private String bankName;
	/**
	 * 身份证照片地址
	 */
	private String identityCardImg;
	/**
	 * 合同文件地址
	 */
	private String contractImg;
	/**
	 * 其它文件地址
	 */
	private String otherFile;
	/**
	 * 录入时间
	 */
	private String addTime;
	/**
	 * 代理商级别[动态]
	 */
	private Integer agentLevel;
	/**
	 * 代理商类型：AgentAgentType
	 */
	private Integer agentType;
	/**
	 * 机构编码
	 */
	private String branchno;
	/**
	 * 一级代理商编码
	 */
	private String agentnoFirst;
	/**
	 * 业务开通类型：1&lt;&lt;AgentBizType & 1&lt;&lt;AgentBizType ...
	 */
	private Integer bizType;
	/**
	 * 银行卡照片地址
	 */
	private String bankCardImg;
	
	private Integer userId;//所属业务员Id
	/**
	 * 提现费
	 */
	private BigDecimal withdrawalFee;
	/**
	 * T0分成比例
	 */
	private Integer t0Divide;
	
	private BigDecimal posDebitRate; //借记卡pos费率
	private BigDecimal posDebitFixed; //借记卡pos封顶
	private BigDecimal posCreditRate; //贷记卡pos费率
	private BigDecimal appDebitRate; //借记卡app费率
	private BigDecimal appDebitFixed; //借记卡app封顶
	private BigDecimal appCreditRate; //贷记卡app费率
	private BigDecimal posT0Rate; //pos t+0 增量费率
	private BigDecimal appT0Rate; //app t+0增量费率
	private BigDecimal onlineRate; //线上费率
	
	private BigDecimal gatewayFixed;//网关T1封顶金额
	private BigDecimal gatewayRateT0;//网关T0费率
	private BigDecimal fastpayRateT0; // 快捷支付T0费率
	private BigDecimal fastpayRateT1;// 快捷支付T1费率
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getDivide() {
		return divide;
	}
	public void setDivide(Integer divide) {
		this.divide = divide;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public BigDecimal getMemberFee() {
		return memberFee;
	}
	public void setMemberFee(BigDecimal memberFee) {
		this.memberFee = memberFee;
	}
	public BigDecimal getAssuranceFee() {
		return assuranceFee;
	}
	public void setAssuranceFee(BigDecimal assuranceFee) {
		this.assuranceFee = assuranceFee;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSuperAgent() {
		return superAgent;
	}
	public void setSuperAgent(String superAgent) {
		this.superAgent = superAgent;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditAgentno() {
		return auditAgentno;
	}
	public void setAuditAgentno(String auditAgentno) {
		this.auditAgentno = auditAgentno;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankno() {
		return bankno;
	}
	public void setBankno(String bankno) {
		this.bankno = bankno;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIdentityCardImg() {
		return identityCardImg;
	}
	public void setIdentityCardImg(String identityCardImg) {
		this.identityCardImg = identityCardImg;
	}
	public String getContractImg() {
		return contractImg;
	}
	public void setContractImg(String contractImg) {
		this.contractImg = contractImg;
	}
	public String getOtherFile() {
		return otherFile;
	}
	public void setOtherFile(String otherFile) {
		this.otherFile = otherFile;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getAgentLevel() {
		return agentLevel;
	}
	public void setAgentLevel(Integer agentLevel) {
		this.agentLevel = agentLevel;
	}
	public Integer getAgentType() {
		return agentType;
	}
	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}
	public String getBranchno() {
		return branchno;
	}
	public void setBranchno(String branchno) {
		this.branchno = branchno;
	}
	public String getAgentnoFirst() {
		return agentnoFirst;
	}
	public void setAgentnoFirst(String agentnoFirst) {
		this.agentnoFirst = agentnoFirst;
	}
	public Integer getBizType() {
		return bizType;
	}
	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}
	public String getBankCardImg() {
		return bankCardImg;
	}
	public void setBankCardImg(String bankCardImg) {
		this.bankCardImg = bankCardImg;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getWithdrawalFee() {
		return withdrawalFee;
	}
	public void setWithdrawalFee(BigDecimal withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}
	public Integer getT0Divide() {
		return t0Divide;
	}
	public void setT0Divide(Integer t0Divide) {
		this.t0Divide = t0Divide;
	}
	public BigDecimal getPosDebitRate() {
		return posDebitRate;
	}
	public void setPosDebitRate(BigDecimal posDebitRate) {
		this.posDebitRate = posDebitRate;
	}
	public BigDecimal getPosDebitFixed() {
		return posDebitFixed;
	}
	public void setPosDebitFixed(BigDecimal posDebitFixed) {
		this.posDebitFixed = posDebitFixed;
	}
	public BigDecimal getPosCreditRate() {
		return posCreditRate;
	}
	public void setPosCreditRate(BigDecimal posCreditRate) {
		this.posCreditRate = posCreditRate;
	}
	public BigDecimal getAppDebitRate() {
		return appDebitRate;
	}
	public void setAppDebitRate(BigDecimal appDebitRate) {
		this.appDebitRate = appDebitRate;
	}
	public BigDecimal getAppDebitFixed() {
		return appDebitFixed;
	}
	public void setAppDebitFixed(BigDecimal appDebitFixed) {
		this.appDebitFixed = appDebitFixed;
	}
	public BigDecimal getAppCreditRate() {
		return appCreditRate;
	}
	public void setAppCreditRate(BigDecimal appCreditRate) {
		this.appCreditRate = appCreditRate;
	}
	public BigDecimal getPosT0Rate() {
		return posT0Rate;
	}
	public void setPosT0Rate(BigDecimal posT0Rate) {
		this.posT0Rate = posT0Rate;
	}
	public BigDecimal getAppT0Rate() {
		return appT0Rate;
	}
	public void setAppT0Rate(BigDecimal appT0Rate) {
		this.appT0Rate = appT0Rate;
	}
	public BigDecimal getOnlineRate() {
		return onlineRate;
	}
	public void setOnlineRate(BigDecimal onlineRate) {
		this.onlineRate = onlineRate;
	}
	public BigDecimal getGatewayFixed() {
		return gatewayFixed;
	}
	public void setGatewayFixed(BigDecimal gatewayFixed) {
		this.gatewayFixed = gatewayFixed;
	}
	public BigDecimal getGatewayRateT0() {
		return gatewayRateT0;
	}
	public void setGatewayRateT0(BigDecimal gatewayRateT0) {
		this.gatewayRateT0 = gatewayRateT0;
	}
	public BigDecimal getFastpayRateT0() {
		return fastpayRateT0;
	}
	public void setFastpayRateT0(BigDecimal fastpayRateT0) {
		this.fastpayRateT0 = fastpayRateT0;
	}
	public BigDecimal getFastpayRateT1() {
		return fastpayRateT1;
	}
	public void setFastpayRateT1(BigDecimal fastpayRateT1) {
		this.fastpayRateT1 = fastpayRateT1;
	}

}
