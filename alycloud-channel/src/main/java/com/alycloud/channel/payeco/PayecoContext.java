package com.alycloud.channel.payeco;

import com.alycloud.channel.common.RequestClient;

/**
 * 易联支付接口－上下文管理类
 * @author Moyq5
 * @date 2017年4月13日
 */
public class PayecoContext {

	private Config config;
	private RequestClient requestClient;
	public Config getConfig() {
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
	}
	public RequestClient getRequestClient() {
		return requestClient;
	}
	public void setRequestClient(RequestClient requestClient) {
		this.requestClient = requestClient;
	}
}
