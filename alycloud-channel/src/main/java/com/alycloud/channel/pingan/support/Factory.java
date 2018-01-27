package com.alycloud.channel.pingan.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.pingan.PinganType;

/**
 * 平安银行支付接口工具类
 * @author Moyq5
 * @date 2017年6月16日
 */
public abstract class Factory {
	
	private static Logger log = LoggerFactory.getLogger(Factory.class);
	
	private static HttpClient httpClient;
	private static Config config;
	
	public static Client<?, ?> getClient(PinganType type, Merch merch) {
		AbstractClient<?,?> t = null;
		try {
			t = (AbstractClient<?,?>)type.getClientClass().newInstance();
			t.setMerch(merch);
		} catch (Exception e) {
			log.error("获取支付接口实例失败", e);
		}
		return t;
	}

	public static HttpClient getHttpClient() {
		return httpClient;
	}

	public static void setHttpClient(HttpClient httpClient) {
		Factory.httpClient = httpClient;
	}
	
	public static Config getConfig() {
		return config;
	}

	public static void setConfig(Config config) {
		Factory.config = config;
	}

	
}
