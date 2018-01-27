package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 代理商角色
 * 
 * @author Moyq5
 * @date 2017年7月14日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class AgentRoler implements Serializable {
	private Integer id;
	private String branchno;
	private String agentno;
	private String name;
	private String memo;
	private String auth;
	private Integer type;
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
	public String getAgentno() {
		return agentno;
	}
	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
