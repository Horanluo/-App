package com.alycloud.channel.linkpay.bean;

/**
 * 江苏电子网关支付接口－请求参数<br>
 * 借记卡接口、贷记卡接口、借贷综合接口、H5接口等网关接口请求参数共用。
 * @author Moyq5
 * @date 2017年6月7日
 */
public class PayGatewayData extends AbstractPayData {

	/**
	 * 平台商户终端编号，必填
	 */
	private String terminalCode;
	/**
	 * 支付成功跳转页面地址，必填
	 */
	private String returnUrl;
	/**
	 * 商品名称，必填
	 */
	private String commodityName;
	/**
	 * 银行编码，必填
	 */
	private String bankCode;
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	@Override
	public String toString() {
		return "PayGatewayData [terminalCode=" + terminalCode + ", returnUrl=" + returnUrl + ", commodityName="
				+ commodityName + ", bankCode=" + bankCode + ", toString()=" + super.toString() + "]";
	}
	
}
