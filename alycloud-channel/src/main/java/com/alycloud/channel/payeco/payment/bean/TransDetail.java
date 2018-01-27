package com.alycloud.channel.payeco.payment.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.alycloud.channel.payeco.payment.enums.Cny;
import com.alycloud.channel.payeco.payment.enums.IdType;
import com.alycloud.channel.payeco.payment.enums.PayState;
import com.alycloud.channel.payeco.payment.enums.UserLevel;
import com.alycloud.channel.payeco.payment.jackson.converter.DateConverte_yyyyMMdd;
import com.alycloud.channel.payeco.payment.jackson.converter.PayStateDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "TRANS_DETAIL")
public class TransDetail {

	@JacksonXmlProperty(localName = "SN")
	private String sn;
	@JacksonXmlProperty(localName = "BANK_CODE")
	private String bankCode;
	@JacksonXmlProperty(localName = "ACC_NO")
	private String accNo;
	@JacksonXmlProperty(localName = "ACC_NAME")
	private String accName;
	@JacksonXmlProperty(localName = "ACC_PROVINCE")
	private String accProvince;
	@JacksonXmlProperty(localName = "ACC_CITY")
	private String accCity;
	@JacksonXmlProperty(localName = "AMOUNT")
	private BigDecimal amount;
	@JacksonXmlProperty(localName = "MOBILE_NO")
	private String mobileNo;
	@JacksonXmlProperty(localName = "PAY_STATE")
	@JsonDeserialize(converter = PayStateDeserialize.class)
	private PayState payState;
	@JacksonXmlProperty(localName = "BANK_NO")
	private Long bankNo;
	@JacksonXmlProperty(localName = "BANK_NAME")
	private String bankName;
	@JacksonXmlProperty(localName = "ACC_TYPE")
	private String accType;
	@JacksonXmlProperty(localName = "ACC_PROP")
	private String accProp;
	@JacksonXmlProperty(localName = "ID_TYPE")
	private IdType idType;
	@JacksonXmlProperty(localName = "ID_NO")
	private String idNo;
	@JacksonXmlProperty(localName = "CNY")
	private Cny cny;
	@JacksonXmlProperty(localName = "EXCHANGE_RATE")
	private BigDecimal exchangeRate;
	@JacksonXmlProperty(localName = "SETT_AMOUNT")
	private BigDecimal settAmount;
	@JacksonXmlProperty(localName = "USER_LEVEL")
	private UserLevel userLevel;
	@JsonDeserialize(converter = DateConverte_yyyyMMdd.class)
	@JacksonXmlProperty(localName = "SETT_DATE")
	private Date settDate;
	@JacksonXmlProperty(localName = "REMARK")
	private String remark;
	@JacksonXmlProperty(localName = "RESERVE")
	private String reserve;
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getAccProvince() {
		return accProvince;
	}
	public void setAccProvince(String accProvince) {
		this.accProvince = accProvince;
	}
	public String getAccCity() {
		return accCity;
	}
	public void setAccCity(String accCity) {
		this.accCity = accCity;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public PayState getPayState() {
		return payState;
	}
	public void setPayState(PayState payState) {
		this.payState = payState;
	}
	public Long getBankNo() {
		return bankNo;
	}
	public void setBankNo(Long bankNo) {
		this.bankNo = bankNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getAccProp() {
		return accProp;
	}
	public void setAccProp(String accProp) {
		this.accProp = accProp;
	}
	public IdType getIdType() {
		return idType;
	}
	public void setIdType(IdType idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Cny getCny() {
		return cny;
	}
	public void setCny(Cny cny) {
		this.cny = cny;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public BigDecimal getSettAmount() {
		return settAmount;
	}
	public void setSettAmount(BigDecimal settAmount) {
		this.settAmount = settAmount;
	}
	public UserLevel getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}
	public Date getSettDate() {
		return settDate;
	}
	public void setSettDate(Date settDate) {
		this.settDate = settDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	@Override
	public String toString() {
		return "TransDetail [sn=" + sn + ", bankCode=" + bankCode + ", accNo=" + accNo + ", accName=" + accName
				+ ", accProvince=" + accProvince + ", accCity=" + accCity + ", amount=" + amount + ", mobileNo="
				+ mobileNo + ", payState=" + payState + ", bankNo=" + bankNo + ", bankName=" + bankName + ", accType="
				+ accType + ", accProp=" + accProp + ", idType=" + idType + ", idNo=" + idNo + ", cny=" + cny
				+ ", exchangeRate=" + exchangeRate + ", settAmount=" + settAmount + ", userLevel=" + userLevel
				+ ", settDate=" + settDate + ", remark=" + remark + ", reserve=" + reserve + "]";
	}
	
}
