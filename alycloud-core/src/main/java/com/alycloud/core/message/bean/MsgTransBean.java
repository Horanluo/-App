package com.alycloud.core.message.bean;

/**
 * 短信发送流水信息
 * 
 * @author 李安国
 * @date 2016-11-14 下午5:59:11
 * @project posp-api
 */
public class MsgTransBean {

	private int id;
	/**
	 * 短信类型(1-注册验证码 2-修改密码验证码)
	 */
	private int msgType;
	/**
	 * 发送日期
	 */
	private String sendDate;
	/**
	 * 发送时间
	 */
	private String sendTime;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 模板ID
	 */
	private String tempId;
	/**
	 * 参数值
	 */
	private String paramValue;
	/**
	 * 响应码
	 */
	private String respCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	@Override
	public String toString() {
		return "MsgTransBean [id=" + id + ", msgType=" + msgType + ", sendDate=" + sendDate + ", sendTime=" + sendTime + ", mobile=" + mobile + ", tempId="
				+ tempId + ", paramValue=" + paramValue + ", respCode=" + respCode + "]";
	}
}
