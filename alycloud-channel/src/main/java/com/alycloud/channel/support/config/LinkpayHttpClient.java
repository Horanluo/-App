package com.alycloud.channel.support.config;

import com.alycloud.channel.linkpay.support.HttpClient;

public class LinkpayHttpClient extends CommonHttpClient implements HttpClient {
	

	@Override
	public String post(String serverPath, String postBody) {
		return super.post(serverPath + "?" + postBody, postBody);
	}

}
