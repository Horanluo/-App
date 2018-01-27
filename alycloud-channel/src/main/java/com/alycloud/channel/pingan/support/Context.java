package com.alycloud.channel.pingan.support;

/**
 * 平安银行支付接口－上下文管理类
 * @author Moyq5
 * @date 2017年6月15日
 */
public class Context {

	private HttpClient httpClient;

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
}
