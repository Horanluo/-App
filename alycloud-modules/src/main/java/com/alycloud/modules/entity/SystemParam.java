package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 系统参数
 * @author Moyq5
 * @date 2017年7月13日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class SystemParam implements Serializable {
	
	private Integer id;
	private Integer type;// 参数类型
	private String code;// 参数编码
	private String value;// 参数值
	private String memo;// 参数说明
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
