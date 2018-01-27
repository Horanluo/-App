package com.alycloud.channel.yufu.merch.bean;

import com.alycloud.channel.yufu.merch.YufuMerchApiFactory;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 请求参数公众部分
 * @author Moyq5
 * @date 2017年7月31日
 */
public abstract class DataAbstract {

	/**
	 * 机构号，必填
	 */
	@JsonProperty("brhId")
	private String branchNo;
	
	/**
	 * 接口名称，必填
	 */
	private String service;
	/**
	 * 流水号，必填，16
	 */
	private String serialNo;
	public DataAbstract(String service) {
		this.service = service;
		this.branchNo = YufuMerchApiFactory.getConfig().getBranchNo();
	}
	public String getBranchNo() {
		return branchNo;
	}
	public String getService() {
		return service;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	@Override
	public String toString() {
		return "DataAbstract [branchNo=" + branchNo + ", service=" + service + ", serialNo=" + serialNo + "]";
	}
	
}