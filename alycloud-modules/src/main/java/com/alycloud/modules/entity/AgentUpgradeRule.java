package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 代理商升级规则
 * @author Moyq5
 * @date 2017年7月13日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class AgentUpgradeRule implements Serializable {
	
	private String agentno;// 代理商编号
	private Integer status;// 启用状态
	private String detail;// 规则详细，JSON数据
	public String getAgentno() {
		return agentno;
	}
	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
