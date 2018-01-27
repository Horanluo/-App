package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 机构（分公司）
 * @author Moyq5
 * @date 2017年7月17日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class BranchInfo  implements Serializable {
	private Integer id;
	private String branchno;// 分公司编码
	private String branchName;// 分公司名称
	private BigDecimal fastPayRateT0;// 快捷T0费率
	private BigDecimal fastPayRateT1;// 快捷T1费率
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
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public BigDecimal getFastPayRateT0() {
		return fastPayRateT0;
	}
	public void setFastPayRateT0(BigDecimal fastPayRateT0) {
		this.fastPayRateT0 = fastPayRateT0;
	}
	public BigDecimal getFastPayRateT1() {
		return fastPayRateT1;
	}
	public void setFastPayRateT1(BigDecimal fastPayRateT1) {
		this.fastPayRateT1 = fastPayRateT1;
	}
	
}
