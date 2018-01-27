package com.alycloud.channel.linkpay;

import com.alycloud.channel.linkpay.support.Client;
import com.alycloud.channel.linkpay.support.Config;
import com.alycloud.channel.linkpay.support.Context;
import com.alycloud.channel.linkpay.support.Factory;
import com.alycloud.channel.linkpay.support.HttpClient;

/**
 * 江苏电子支付接口工具类，相关配置和接口调用从此类开始
 * @author Moyq5
 * @date 2017年5月9日
 */
public abstract class LinkpayFactory {

	private static Context context = new Context();
	private static Config config;
	public static void config(Config config, HttpClient httpClient) {
		LinkpayFactory.config = config;
		context.setConfig(config);
		context.setHttpClient(httpClient);
	}
	
	public static Client<?, ?> getClient(LinkpayType type) {
		return Factory.getClient(type, context);
	}

	public static Config getConfig() {
		return config;
	}
}
