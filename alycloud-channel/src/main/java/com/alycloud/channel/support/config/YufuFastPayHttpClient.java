package com.alycloud.channel.support.config;

import org.slf4j.LoggerFactory;

import com.alycloud.channel.yufu.support.HttpClient;

/**
 * 御付（快捷支付）渠道HTTP实现
 * @author Moyq5
 * @date 2017年8月30日
 */
public class YufuFastPayHttpClient extends CommonHttpClient implements HttpClient {
	
	public YufuFastPayHttpClient() {
		log = LoggerFactory.getLogger(YufuFastPayHttpClient.class);
	}
}
