package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 公众号
 * 
 * @author Moyq5
 * @date 2017年2月10日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
public class OfficalInfo implements Serializable {

	private Integer id;// ID
	private String branchno;// 机构号
	private String appId; // 公众号APPID
	private String appKey;// 公众号APPSECRIT
	private String appName;// 公众号名称
	private String accessToken;
	private String lastTokenTime;// accessToken有效期
	private String msgTemplateIds;// 模板消息ID(s) 用|分开，0收入消息，1代理申请成功消息,2提现成功
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getLastTokenTime() {
		return lastTokenTime;
	}
	public void setLastTokenTime(String lastTokenTime) {
		this.lastTokenTime = lastTokenTime;
	}
	public String getMsgTemplateIds() {
		return msgTemplateIds;
	}
	public void setMsgTemplateIds(String msgTemplateIds) {
		this.msgTemplateIds = msgTemplateIds;
	}

}
