package com.alycloud.channel.yufu.merch.bean;

import com.alycloud.channel.yufu.merch.enums.MerchState;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商户审核状态查询接口响应参数对象
 * @author Moyq5
 * @date 2017年7月31日
 */
public class MerchQueryResult extends ResultAbstract {
	
	/**
	 * 状态，必填
	 */
	private MerchState state;
	/**
	 * 分配的商户号
	 */
	@JsonProperty("merId")
	private String merchId;
	/**
	 * 终端号
	 */
	private String termId;
	/**
	 * 拒绝原因
	 */
	private String remark;
	/**
	 * 一码付地址
	 */
	private String oneCodeUrl;
	/**
	 * 快捷支付key
	 */
	@JsonProperty("kj_key")
	private String kjKey;
	public MerchState getState() {
		return state;
	}
	public void setState(MerchState state) {
		this.state = state;
	}
	public String getMerchId() {
		return merchId;
	}
	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOneCodeUrl() {
		return oneCodeUrl;
	}
	public void setOneCodeUrl(String oneCodeUrl) {
		this.oneCodeUrl = oneCodeUrl;
	}
	public String getKjKey() {
		return kjKey;
	}
	public void setKjKey(String kjKey) {
		this.kjKey = kjKey;
	}
	@Override
	public String toString() {
		return "MerchQueryResult [state=" + state + ", merchId=" + merchId + ", termId=" + termId + ", remark=" + remark
				+ ", oneCodeUrl=" + oneCodeUrl + ", kjKey=" + kjKey + ", toString()=" + super.toString() + "]";
	}
	
}
