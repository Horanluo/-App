/**
 * 
 */
package com.alycloud.channel.linkpay.bean;

/**
 * 支付(或代付)状态查询接口－请求参数<br>
 * 二维码支付、网关支付、代付等订单状态查询参数。
 * @author Moyq5
 * @date 2017年4月10日
 */
public class QueryData {

	/**
	 * 平台商户编号，必填
	 */
	public String merchantCode;
	/**
	 * 合作商订单号，全局唯一，必填
	 */
	public String orderNum;
	
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	@Override
	public String toString() {
		return "QueryData [merchantCode=" + merchantCode + ", orderNum=" + orderNum + "]";
	}
}
