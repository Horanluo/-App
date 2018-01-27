package com.alycloud.channel.esyto.bean;

/**
 * 查询接口－请求参数
 * @author Moyq5
 * @date 2017年9月30日
 */
public class OrderQueryData {

	/**
	 * 客户单号，选填
	 */
	private String customOrderNo;
	/**
	 * 平台单号，选填
	 */
	private String orderNo;
	public String getCustomOrderNo() {
		return customOrderNo;
	}
	public void setCustomOrderNo(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
