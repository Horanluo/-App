package com.alycloud.modules.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 代理商层级关系
 * 
 * @author Moyq5
 * @date 2017年6月6日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class AgentRelate implements Serializable {

	/**
	 * 代理商编号
	 */
	private String agentno;

	/**
	 * 上级代理商编号
	 */
	private String superAgentno;

	/**
	 * 上级代理商名称
	 */
	private String superAgentName;

	/**
	 * 代理商级别
	 */
	private Integer agentLevel;

	public String getAgentno() {
		return agentno;
	}

	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}

	public String getSuperAgentno() {
		return superAgentno;
	}

	public void setSuperAgentno(String superAgentno) {
		this.superAgentno = superAgentno;
	}

	public String getSuperAgentName() {
		return superAgentName;
	}

	public void setSuperAgentName(String superAgentName) {
		this.superAgentName = superAgentName;
	}

	public Integer getAgentLevel() {
		return agentLevel;
	}

	public void setAgentLevel(Integer agentLevel) {
		this.agentLevel = agentLevel;
	}

}
