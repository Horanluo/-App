package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 卡BIN信息
 * @author Moyq5
 * @date 2017年10月21日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class SystemCardBin implements Serializable {
	private Integer id;
	private String cardBin;
	private String bankCode;
	private String bankName;
	private String cardName;
	private Integer cardType;
	private Integer cardLength;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardBin() {
		return cardBin;
	}
	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
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
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public Integer getCardLength() {
		return cardLength;
	}
	public void setCardLength(Integer cardLength) {
		this.cardLength = cardLength;
	}
}
