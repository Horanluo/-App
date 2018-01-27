package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 御付渠道商户
 * @author Moyq5
 * @date 2017年8月5日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class ChannelMerchYufu implements Serializable {

	private Long id;
	private String platMerchno;// （平台）商户号
	private String branchId;// （渠道参数）机构号
	private String merchName;// （渠道参数）商户名称
	private String merchShortName;// （渠道参数）商户简称
	private Integer merchLevel;// （渠道参数）商户级别
	private String parentMerchId;// （渠道参数）商户上级商户号
	private Integer openType;// （渠道参数）开启类型：1：商户(对公结算)2：商户(对私结算)3：个体工商户4：个人
	private String gszcName;// （渠道参数）工商注册名
	private String merchAddr;// （渠道参数）商户地址
	private Integer province;// （渠道参数）省份
	private Integer city;// （渠道参数）城市
	private String idCard;// （渠道参数）身份证号
	private String yyzzCode;// （渠道参数）营业执照号
	private String zzjgCode;// （渠道参数）组织机构代码证号
	private String swdjCode;// （渠道参数）税务登记号
	private String khxkCode;// （渠道参数）开户许可证
	private Integer accountType;// （渠道参数）0-民生对公、1-民生对私、2-非民生对公、3-非民生对私
	private String accountNo;// （渠道参数）账号
	private String accountName;// （渠道参数）账户名
	private String bankCode;// （渠道参数）开户行号
	private String bankName;// （渠道参数）开户行名，联行号，精确到总行和bankCode传递一样的值
	private String openBranch;// （渠道参数）开户网点联行号，精确到支行网点非民生对公必填
	private String merchContacts;// （渠道参数）联系人
	private String phone;// （渠道参数）联系人电话，全局唯一性判重
	private String countRole;// （渠道参数）结算主体， 1-本商户
	private Integer state;// （渠道参数）状态0：待审核，1：通过，2：拒绝
	private String merchId;// （渠道参数）商户号
	private String termId;// （渠道参数）终端号
	private String remark;// （渠道参数）拒绝原因
	private String oneCodeUrl;// （渠道参数）一码付地址
	private String kjKey;// （渠道参数）快捷支付key
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlatMerchno() {
		return platMerchno;
	}
	public void setPlatMerchno(String platMerchno) {
		this.platMerchno = platMerchno;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getMerchName() {
		return merchName;
	}
	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}
	public String getMerchShortName() {
		return merchShortName;
	}
	public void setMerchShortName(String merchShortName) {
		this.merchShortName = merchShortName;
	}
	public Integer getMerchLevel() {
		return merchLevel;
	}
	public void setMerchLevel(Integer merchLevel) {
		this.merchLevel = merchLevel;
	}
	public String getParentMerchId() {
		return parentMerchId;
	}
	public void setParentMerchId(String parentMerchId) {
		this.parentMerchId = parentMerchId;
	}
	public Integer getOpenType() {
		return openType;
	}
	public void setOpenType(Integer openType) {
		this.openType = openType;
	}
	public String getGszcName() {
		return gszcName;
	}
	public void setGszcName(String gszcName) {
		this.gszcName = gszcName;
	}
	public String getMerchAddr() {
		return merchAddr;
	}
	public void setMerchAddr(String merchAddr) {
		this.merchAddr = merchAddr;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getYyzzCode() {
		return yyzzCode;
	}
	public void setYyzzCode(String yyzzCode) {
		this.yyzzCode = yyzzCode;
	}
	public String getZzjgCode() {
		return zzjgCode;
	}
	public void setZzjgCode(String zzjgCode) {
		this.zzjgCode = zzjgCode;
	}
	public String getSwdjCode() {
		return swdjCode;
	}
	public void setSwdjCode(String swdjCode) {
		this.swdjCode = swdjCode;
	}
	public String getKhxkCode() {
		return khxkCode;
	}
	public void setKhxkCode(String khxkCode) {
		this.khxkCode = khxkCode;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getOpenBranch() {
		return openBranch;
	}
	public void setOpenBranch(String openBranch) {
		this.openBranch = openBranch;
	}
	public String getMerchContacts() {
		return merchContacts;
	}
	public void setMerchContacts(String merchContacts) {
		this.merchContacts = merchContacts;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountRole() {
		return countRole;
	}
	public void setCountRole(String countRole) {
		this.countRole = countRole;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMerchId() {
		return merchId;
	}
	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOneCodeUrl() {
		return oneCodeUrl;
	}
	public void setOneCodeUrl(String oneCodeUrl) {
		this.oneCodeUrl = oneCodeUrl;
	}
	public String getKjKey() {
		return kjKey;
	}
	public void setKjKey(String kjKey) {
		this.kjKey = kjKey;
	}
	
}
