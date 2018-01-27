package com.alycloud.channel.yufu.merch.bean;

import java.util.List;

import com.alycloud.channel.yufu.merch.jackson.converter.StringSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 商户入驻请求参数对象
 * @author Moyq5
 * @date 2017年7月31日
 */
public class MerchApplyData extends DataAbstract {

	/**
	 * 商户名称，必填，全局唯一性判重
	 */
	@JsonProperty("merName")
	private String merchName;
	
	/**
	 * 商户简称，必填
	 */
	@JsonProperty("merShortName")
	private String merchShortName;
	
	/**
	 * 商户地址，必填
	 */
	@JsonProperty("merAddr")
	private String merchAddress;
	
	/**
	 * 开户类型，必填，1
	 */
	private Integer openType;
	
	/**
	 * 省份（城市编码），必填，快捷可默认填写29
	 */
	private Integer province;
	
	/**
	 * 城市（城市编码），必填，快捷可默认填写：2904
	 */
	private Integer city;
	
	/**
	 * 身份证号，必填
	 */
	private String idCard;
	
	/**
	 * 账户类型，0-民生对公、1-民生对私、2-非民生对公、3-非民生对私(快捷可默认填写：1)<br>
	 * 必填
	 */
	private Integer accountType;
	
	/**
	 * 账号，必填
	 */
	private String accountNo;
	
	/**
	 * 账户名，必填
	 */
	private String accountName;
	
	/**
	 * 开户行号，必填，快捷可默认填写：310290000013
	 */
	private String bankCode;
	
	/**
	 * 开户行名，必填，联行号，精确到总行和bankCode传递一样的值（快捷可默认填写：310290000013）
	 */
	private String bankName;
	
	/**
	 * 联行号，精确到支行网点非民生对公必填
	 */
	@JsonProperty("openBranch")
	private String bankBranchName;
	
	/**
	 * 联系人，必填
	 */
	@JsonProperty("merConsacts")
	private String merchContacts;
	
	/**
	 * 联系人电话，必填，全局唯一性判重
	 */
	private String phone;
	
	/**
	 * 支付服务
	 */
	@JsonSerialize(using = StringSerializer.class)
	private List<MerchApplyPayService> payServices;
	
	/**
	 * 商户开通结果通知的url，必填
	 */
	private String notifyUrl;
	
	public MerchApplyData() {
		super("MER_APPLY2");
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

	public String getMerchAddress() {
		return merchAddress;
	}

	public void setMerchAddress(String merchAddress) {
		this.merchAddress = merchAddress;
	}

	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
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

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
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

	public List<MerchApplyPayService> getPayServices() {
		return payServices;
	}

	public void setPayServices(List<MerchApplyPayService> payServices) {
		this.payServices = payServices;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	@Override
	public String toString() {
		return "MerchApplyData [merchName=" + merchName + ", merchShortName=" + merchShortName + ", merchAddress="
				+ merchAddress + ", openType=" + openType + ", province=" + province + ", city=" + city + ", idCard="
				+ idCard + ", accountType=" + accountType + ", accountNo=" + accountNo + ", accountName=" + accountName
				+ ", bankCode=" + bankCode + ", bankName=" + bankName + ", bankBranchName=" + bankBranchName
				+ ", merchContacts=" + merchContacts + ", phone=" + phone + ", payServices=" + payServices
				+ ", notifyUrl=" + notifyUrl + ", toString()=" + super.toString() + "]";
	}

}
