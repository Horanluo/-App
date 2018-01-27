package com.alycloud.channel.yufu.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.yufu.YufuApiType;

/**
 * 御付支付交易接口工具类
 * @author Moyq5
 * @date 2017年8月2日
 */
public abstract class Factory {
	
	private static Logger log = LoggerFactory.getLogger(Factory.class);
	
	public static Client<?, ?> getClient(YufuApiType type, Context context) {
		AbstractClient<?,?> t = null;
		try {
			t = (AbstractClient<?,?>)type.getClientClass().newInstance();
			t.setContext(context);
		} catch (Exception e) {
			log.error("获取御付商户入驻接口实例失败", e);
		}
		return t;
	}

}
