package com.alycloud.channel.payeco.payment.bean;

import com.alycloud.channel.payeco.payment.enums.MsgType;
import com.alycloud.channel.payeco.payment.enums.TransState;
import com.alycloud.channel.payeco.payment.jackson.converter.MsgTypeDeserialize;
import com.alycloud.channel.payeco.payment.jackson.converter.MsgTypeSerialize;
import com.alycloud.channel.payeco.payment.jackson.converter.TransStateConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 易联代付参数
 * @author Moyq5
 * @date 2017年4月25日
 */
@JacksonXmlRootElement(localName = "MSGBEAN")
public class Payment {
	@JacksonXmlProperty(localName = "VERSION")
	private String version;
	@JsonSerialize(using = MsgTypeSerialize.class)
	@JsonDeserialize(converter = MsgTypeDeserialize.class)
	@JacksonXmlProperty(localName = "MSG_TYPE")
	private MsgType msgType;
	@JacksonXmlProperty(localName = "BATCH_NO")
	private String batchNo;
	@JacksonXmlProperty(localName = "USER_NAME")
	private String userName;
	@JsonDeserialize(converter = TransStateConverter.class)
	@JacksonXmlProperty(localName = "TRANS_STATE")
	private TransState transState;
	@JacksonXmlProperty(localName = "MSG_SIGN")
	private String msgSign;
	@JacksonXmlProperty(localName = "TRANS_DETAILS")
	private TransDetails transDetails;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public MsgType getMsgType() {
		return msgType;
	}
	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public TransState getTransState() {
		return transState;
	}
	public void setTransState(TransState transState) {
		this.transState = transState;
	}
	public String getMsgSign() {
		return msgSign;
	}
	public void setMsgSign(String msgSign) {
		this.msgSign = msgSign;
	}
	public TransDetails getTransDetails() {
		return transDetails;
	}
	public void setTransDetails(TransDetails transDetails) {
		this.transDetails = transDetails;
	}
	@Override
	public String toString() {
		return "Payment [version=" + version + ", msgType=" + msgType + ", batchNo=" + batchNo + ", userName="
				+ userName + ", transState=" + transState + ", msgSign=" + msgSign + ", transDetails=" + transDetails
				+ "]";
	}
	
}
