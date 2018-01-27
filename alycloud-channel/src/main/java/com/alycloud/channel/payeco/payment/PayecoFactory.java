package com.alycloud.channel.payeco.payment;

import com.alycloud.channel.payeco.payment.support.client.PaymentClient;

/**
 * 易联代付接口工具类，相关配置和接口调用从此类开始
 * @author Moyq5
 * @date 2017年4月26日
 */
public abstract class PayecoFactory {

	private static PayecoContext context = new PayecoContext();
	private static PayecoConfig config;
	public static void config(PayecoConfig config, PayecoHttpClient httpClient) {
		PayecoFactory.config = config;
		context.setConfig(config);
		context.setHttpClient(httpClient);
	}
	
	public static PaymentClient getApiClient() {
		PaymentClient client = new PaymentClient();
		client.setContext(context);
		return client;
	}

	public static PayecoConfig getConfig() {
		return config;
	}
}
