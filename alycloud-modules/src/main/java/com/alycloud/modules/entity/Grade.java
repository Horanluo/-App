package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 商户等级规则
 * @author Moyq5
 * @date 2017年10月30日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class Grade implements Serializable {
	private Integer id;
	private Integer gradeType;//  等级
	private String gradeName;// 等级名称
	private Integer quantity;// 升级需要推荐的人数
	private BigDecimal amount;// 升级需要支付的费用，单位：元
	private Integer percent;// 升级分成百分比
	private String remark;// 等级说明（规则，优惠等），多条语句用“|”分开
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGradeType() {
		return gradeType;
	}
	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getPercent() {
		return percent;
	}
	public void setPercent(Integer percent) {
		this.percent = percent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
