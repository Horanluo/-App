package com.alycloud.channel.yufu.qrcode;

import com.alycloud.channel.yufu.qrcode.support.Client;
import com.alycloud.channel.yufu.qrcode.support.Config;
import com.alycloud.channel.yufu.qrcode.support.Factory;
import com.alycloud.channel.yufu.qrcode.support.HttpClient;

/**
 * 御付支付接口工具类，相关配置和接口调用从此类开始
 * @author Moyq5
 * @date 2017年8月29日
 */
public abstract class YufuQrcodeApiFactory {

	public static void config(Config config, HttpClient httpClient) {
		Factory.setConfig(config);
		Factory.setHttpClient(httpClient);
	}
	
	public static Client<?, ?> getClient(YufuQrcodeApiType type) {
		return Factory.getClient(type);
	}

	public static Config getConfig() {
		return Factory.getConfig();
	}
}
