package com.alycloud.channel.yufu;

import com.alycloud.channel.yufu.support.Client;
import com.alycloud.channel.yufu.support.Config;
import com.alycloud.channel.yufu.support.Context;
import com.alycloud.channel.yufu.support.Factory;
import com.alycloud.channel.yufu.support.HttpClient;

/**
 * 御付支付接口工具类，相关配置和接口调用从此类开始
 * @author Moyq5
 * @date 2017年8月2日
 */
public abstract class YufuApiFactory {

	private static Context context = new Context();
	private static Config config;
	public static void config(Config config, HttpClient httpClient) {
		YufuApiFactory.config = config;
		context.setConfig(config);
		context.setHttpClient(httpClient);
	}
	
	public static Client<?, ?> getClient(YufuApiType type) {
		return Factory.getClient(type, context);
	}

	public static Config getConfig() {
		return config;
	}
}
