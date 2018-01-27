package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 代理商操作员
 * @author Moyq5
 * @date 2017年7月11日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class AgentUser implements Serializable {
	
	private Integer id;
	private Integer rolerId;// 角色Id
	private String branchno;// 机构编码
	private String agentno;// 代理商编号
	private String loginName;// 登陆的用户名
	private String trueName;// 真实姓名
	private String password;// 密码
	private String openId;// 绑定的微信公众号OPENID
	private String mobile;// 手机号码
	private String telephone;// 电话
	private String address;// 地址
	private String email;// 邮箱地址
	private Integer errorCount;// 当天登陆错误次数
	private String lastLogin;// 上次登陆时间
	private String loginErrorDate;// 登陆错误日期
	private Integer status;// 用户状态 1-正常 2-冻结 3-锁定
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRolerId() {
		return rolerId;
	}
	public void setRolerId(Integer rolerId) {
		this.rolerId = rolerId;
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
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLoginErrorDate() {
		return loginErrorDate;
	}
	public void setLoginErrorDate(String loginErrorDate) {
		this.loginErrorDate = loginErrorDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AgentUser [id=" + id + ", rolerId=" + rolerId + ", branchno=" + branchno + ", agentno=" + agentno
				+ ", loginName=" + loginName + ", trueName=" + trueName + ", password=" + password + ", openId="
				+ openId + ", mobile=" + mobile + ", telephone=" + telephone + ", address=" + address + ", email="
				+ email + ", errorCount=" + errorCount + ", lastLogin=" + lastLogin + ", loginErrorDate="
				+ loginErrorDate + ", status=" + status + "]";
	}
	
}
