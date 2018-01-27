package com.alycloud.channel.linkpay.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.linkpay.LinkpayType;

/**
 * 江苏电子支付接口工具类，相关配置和接口调用从此类开始
 * @author Moyq5
 * @date 2017年5月9日
 */
public abstract class Factory {
	
	private static Logger log = LoggerFactory.getLogger(Factory.class);
	
	public static Client<?, ?> getClient(LinkpayType type, Context context) {
		AbstractClient<?,?> t = null;
		try {
			t = (AbstractClient<?,?>)type.getClientClass().newInstance();
			t.setContext(context);
		} catch (Exception e) {
			log.error("获取江苏电子支付接口实例失败", e);
		}
		return t;
	}

}
