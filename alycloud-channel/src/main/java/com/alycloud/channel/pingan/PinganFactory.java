package com.alycloud.channel.pingan;

import com.alycloud.channel.pingan.support.Client;
import com.alycloud.channel.pingan.support.Config;
import com.alycloud.channel.pingan.support.Factory;
import com.alycloud.channel.pingan.support.HttpClient;
import com.alycloud.channel.pingan.support.Merch;

/**
 * 平安支付接口工具类，相关配置和接口调用从此类开始
 * @author Moyq5
 * @date 2017年6月16日
 */
public abstract class PinganFactory {

	public static void config(Config config, HttpClient httpClient) {
		Factory.setConfig(config);
		Factory.setHttpClient(httpClient);
	}
	
	public static Client<?, ?> getClient(PinganType type, Merch info) {
		return Factory.getClient(type, info);
	}

}
