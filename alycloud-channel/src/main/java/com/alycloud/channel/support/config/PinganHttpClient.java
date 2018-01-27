package com.alycloud.channel.support.config;

import org.slf4j.LoggerFactory;

import com.alycloud.channel.pingan.support.HttpClient;

/**
 * @author Moyq5
 * @date 2017年8月18日
 */
public class PinganHttpClient extends CommonHttpClient implements HttpClient {
	
	public PinganHttpClient() {
		log = LoggerFactory.getLogger(PinganHttpClient.class);
	}
	
	@Override
	public String post(String serverPath, String postBody) {
		return super.post(serverPath + "?" + postBody, postBody);
	}
	
}
