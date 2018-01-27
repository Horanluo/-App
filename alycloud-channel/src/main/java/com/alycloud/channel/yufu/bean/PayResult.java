package com.alycloud.channel.yufu.bean;

/**
 * 支付接口响应参数对象
 * @author Moyq5
 * @date 2017年8月2日
 */
public class PayResult extends AbstractResult {
	
	/**
	 * 支付地址
	 */
	private String payUrl;

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	@Override
	public String toString() {
		return "PayResult [payUrl=" + payUrl + ", toString()=" + super.toString() + "]";
	}
	
}
