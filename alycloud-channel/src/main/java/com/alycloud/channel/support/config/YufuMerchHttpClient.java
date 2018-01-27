package com.alycloud.channel.support.config;

import org.slf4j.LoggerFactory;

import com.alycloud.channel.yufu.merch.support.HttpClient;

/**
 * 御付（快捷支付商户进件）渠道HTTP实现
 * @author Moyq5
 * @date 2017年8月30日
 */
public class YufuMerchHttpClient extends CommonHttpClient implements HttpClient {
	
	public YufuMerchHttpClient() {
		log = LoggerFactory.getLogger(YufuMerchHttpClient.class);
	}
}
