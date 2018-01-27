package com.alycloud.channel.esyto;

import com.alycloud.channel.esyto.support.Client;
import com.alycloud.channel.esyto.support.Config;
import com.alycloud.channel.esyto.support.Factory;
import com.alycloud.channel.esyto.support.HttpClient;

/**
 * 易商付支付接口工具类，相关配置和接口调用从此类开始
 * @author Moyq5
 * @date 2017年9月29日
 */
public abstract class EsytoApiFactory {

	public static void config(Config config, HttpClient httpClient) {
		Factory.setConfig(config);
		Factory.setHttpClient(httpClient);
	}
	
	public static Client<?, ?> getClient(EsytoApiType type) {
		return Factory.getClient(type);
	}

	public static Config getConfig() {
		return Factory.getConfig();
	}
}
