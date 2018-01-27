package com.alycloud.channel.payeco.payment;

/**
 * 易联支付代付接口－上下文管理类
 * @author Moyq5
 * @date 2017年4月25日
 */
public class PayecoContext {

	private PayecoConfig config;
	private PayecoHttpClient httpClient;
	public PayecoConfig getConfig() {
		return config;
	}
	public void setConfig(PayecoConfig config) {
		this.config = config;
	}
	public PayecoHttpClient getHttpClient() {
		return httpClient;
	}
	public void setHttpClient(PayecoHttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
}
