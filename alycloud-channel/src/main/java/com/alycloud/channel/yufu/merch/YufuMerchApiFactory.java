package com.alycloud.channel.yufu.merch;

import com.alycloud.channel.yufu.merch.support.Client;
import com.alycloud.channel.yufu.merch.support.Config;
import com.alycloud.channel.yufu.merch.support.Context;
import com.alycloud.channel.yufu.merch.support.Factory;
import com.alycloud.channel.yufu.merch.support.HttpClient;

/**
 * 御付商户入驻接口工具类，相关配置和接口调用从此类开始
 * @author Moyq5
 * @date 2017年8月1日
 */
public abstract class YufuMerchApiFactory {

	private static Context context = new Context();
	private static Config config;
	public static void config(Config config, HttpClient httpClient) {
		YufuMerchApiFactory.config = config;
		context.setConfig(config);
		context.setHttpClient(httpClient);
	}
	
	public static Client<?, ?> getClient(YufuMerchApiType type) {
		return Factory.getClient(type, context);
	}

	public static Config getConfig() {
		return config;
	}
}
