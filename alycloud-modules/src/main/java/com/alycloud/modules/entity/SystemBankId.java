package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 银行总行
 * @author Moyq5
 * @date 2017年9月11日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class SystemBankId implements Serializable {

	private String bankId;// 总行联行号
	private String bankName;// 总行名称
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
