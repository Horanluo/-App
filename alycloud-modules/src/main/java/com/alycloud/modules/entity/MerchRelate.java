package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 商户代理商层级关系
 * @author Moyq5
 * @date 2017年5月23日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class MerchRelate implements Serializable {
	
	private Integer id;
	private Integer merchId;
	private String agentno;
	private String agentName;
	private Integer agentLevel;
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
	public Integer getAgentLevel() {
		return agentLevel;
	}
	public void setAgentLevel(Integer agentLevel) {
		this.agentLevel = agentLevel;
	}
	@Override
	public String toString() {
		return "MerchRelateBean [id=" + id + ", merchId=" + merchId
				+ ", agentno=" + agentno + ", agentName=" + agentName
				+ ", agentLevel=" + agentLevel + "]";
	}

}
