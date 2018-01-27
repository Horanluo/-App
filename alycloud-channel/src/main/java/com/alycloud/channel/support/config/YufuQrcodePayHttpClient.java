package com.alycloud.channel.support.config;

import org.slf4j.LoggerFactory;

import com.alycloud.channel.yufu.qrcode.support.HttpClient;

/**
 * 御付（二维码支付）渠道HTTP实现
 * @author Moyq5
 * @date 2017年8月30日
 */
public class YufuQrcodePayHttpClient extends CommonHttpClient implements HttpClient {
	
	public YufuQrcodePayHttpClient() {
		log = LoggerFactory.getLogger(YufuQrcodePayHttpClient.class);
	}
}
