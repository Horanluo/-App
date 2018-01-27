package com.alycloud.channel.payeco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.common.RequestClient;
import com.alycloud.channel.payeco.support.client.AbstractClient;

/**
 * 易联支付接口工具类，相关配置和接口调用从此类开始
 * @author Moyq5
 * @date 2017年4月14日
 */
public abstract class PayecoFactory {

	private static Logger log = LoggerFactory.getLogger(PayecoFactory.class);
	private static PayecoContext context = new PayecoContext();
	private static Config config;
	public static void config(Config config, RequestClient requestClient) {
		PayecoFactory.config = config;
		context.setConfig(config);
		context.setRequestClient(requestClient);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends AbstractClient<?,?> > T getClient(PayecoType type) {
		T t = null;
		try {
			t = (T)type.getClientClass().newInstance();
			t.setContext(context);
		} catch (Exception e) {
			log.error("获取易联支付接口实例失败", e);
		}
		return t;
	}

	public static Config getConfig() {
		return config;
	}
}
