package com.alycloud.channel.yufu.bean;

import com.alycloud.channel.yufu.jackson.converter.BooleanSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 商户订单查询请求参数对象
 * @author Moyq5
 * @date 2017年8月8日
 */
public class MerchOrderQueryData extends AbstractData {

	/**
	 * 商户订单号
	 */
	@JsonProperty("mer_order_no")
	private String merchOrderNo;
	
	/**
	 * 是否查询子订单
	 */
	@JsonSerialize(converter = BooleanSerialize.class)
	@JsonProperty("is_order_list")
	private Boolean isOrderList;
	
	/**
	 * 页码值，查询子订单的页码值，默认为第1页。初期暂时不考虑分页情况，该字段不参与签名
	 */
	@JsonProperty("page_num")
	private String pageNum;

	public String getMerchOrderNo() {
		return merchOrderNo;
	}

	public void setMerchOrderNo(String merchOrderNo) {
		this.merchOrderNo = merchOrderNo;
	}

	public Boolean getIsOrderList() {
		return isOrderList;
	}

	public void setIsOrderList(Boolean isOrderList) {
		this.isOrderList = isOrderList;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "MerchOrderQueryData [merchOrderNo=" + merchOrderNo + ", isOrderList=" + isOrderList + ", pageNum="
				+ pageNum + ", toString()=" + super.toString() + "]";
	}
	
}
