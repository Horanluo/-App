package com.alycloud.channel.linkpay.bean;

/**
 * 支付接口－下单接口－请求参数<br>
 * 微信二维码、QQ二维码支付下单请求参数。
 * @author Moyq5
 * @date 2017年4月10日
 */
public class PayQrcodeDataWithLimit extends PayQrcodeData {

	/**
	 * 是否可以使用信用卡支付，填写no_credit表示不能使用信用卡，不填表示可以使用信用卡
	 */
	private String limitPay;

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	@Override
	public String toString() {
		return "PayQrcodeDataWithLimit [limitPay=" + limitPay + ", toString()=" + super.toString() + "]";
	}

}
