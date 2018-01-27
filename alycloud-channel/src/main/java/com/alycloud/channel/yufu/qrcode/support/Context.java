package com.alycloud.channel.yufu.qrcode.support;

/**
 * 交易接口－上下文管理类
 * @author Moyq5
 * @date 2017年8月29日
 */
public class Context {

	private Config config;
	private HttpClient httpClient;
	public Config getConfig() {
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
	}
	public HttpClient getHttpClient() {
		return httpClient;
	}
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
}
