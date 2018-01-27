package com.alycloud.channel.linkpay.bean;

/**
 * 二维码支付接口－请求参数<br>
 * 支付宝、微信、QQ等二维码支付下单请求参数
 * @author Moyq5
 * @date 2017年4月10日
 */
public class PayQrcodeData extends AbstractPayData {

	/**
	 * 平台商户子商户号
	 */
	private String merchantSubCode;
	/**
	 * 平台商户终端编号，必填
	 */
	private String terminalCode;
	/**
	 * 收款商户名称，必填
	 */
	private String merchantName;
	/**
	 * 商品名称（如不填使用收款商户名称）
	 */
	private String commodityName;
	/**
	 * 商户门店编号，必填
	 */
	private String merchantNum;
	/**
	 * 商户机具终端编号，必填
	 */
	private String terminalNum;
	public String getMerchantSubCode() {
		return merchantSubCode;
	}
	public void setMerchantSubCode(String merchantSubCode) {
		this.merchantSubCode = merchantSubCode;
	}
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getMerchantNum() {
		return merchantNum;
	}
	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}
	public String getTerminalNum() {
		return terminalNum;
	}
	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}
	@Override
	public String toString() {
		return "PayQrcodeData [merchantSubCode=" + merchantSubCode + ", terminalCode=" + terminalCode
				+ ", merchantName=" + merchantName + ", commodityName=" + commodityName + ", merchantNum=" + merchantNum
				+ ", terminalNum=" + terminalNum + ", toString()=" + super.toString() + "]";
	}
}
