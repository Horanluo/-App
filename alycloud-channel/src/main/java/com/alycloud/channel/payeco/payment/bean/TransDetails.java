package com.alycloud.channel.payeco.payment.bean;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TransDetails {

	@JacksonXmlProperty(localName = "TRANS_DETAIL")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<TransDetail> transDetails;

	public List<TransDetail> getTransDetails() {
		return transDetails;
	}

	public void setTransDetails(List<TransDetail> transDetails) {
		this.transDetails = transDetails;
	}

	@Override
	public String toString() {
		return "TransDetails [transDetails=" + transDetails + "]";
	}

}
