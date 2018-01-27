package com.alycloud.channel.linkpay.bean;

/**
 * 支付参数抽象类－请求参数
 * @author Moyq5
 * @date 2017年6月7日
 */
public abstract class AbstractPayData {

	/**
	 * 平台商户编号，必填
	 */
	private String merchantCode;
	/**
	 * 合作商订单号，全局唯一，必填
	 */
	private String orderNum;
	/**
	 * 交易金额，单位分，必填
	 */
	private String transMoney;
	/**
	 * 支付结果异步通知地址，必填
	 */
	private String notifyUrl;
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
	public String getTransMoney() {
		return transMoney;
	}
	public void setTransMoney(String transMoney) {
		this.transMoney = transMoney;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	@Override
	public String toString() {
		return "AbstractPayData [merchantCode=" + merchantCode + ", orderNum=" + orderNum + ", transMoney=" + transMoney
				+ ", notifyUrl=" + notifyUrl + "]";
	}

}