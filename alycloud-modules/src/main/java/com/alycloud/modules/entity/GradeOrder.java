package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alycloud.modules.enums.GradeOrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 商户升级订单
 * @author Moyq5
 * @date 2017年10月30日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class GradeOrder implements Serializable {
	private Integer id;
	private Integer merchId;//  升级商户id
	private BigDecimal amount;// 支付费用，单位：元
	private Integer gradeType;// 升级等级
	private String refno;// 支付单号（同扫码支付订单号）
	/**
	 * @see {@link GradeOrderStatus}
	 */
	private Integer status;// 支付状态
	private String addTime;// 添加时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMerchId() {
		return merchId;
	}
	public void setMerchId(Integer merchId) {
		this.merchId = merchId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRefno() {
		return refno;
	}
	public void setRefno(String refno) {
		this.refno = refno;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getGradeType() {
		return gradeType;
	}
	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}
	
}
