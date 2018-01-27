package com.alycloud.channel.support.config;

import com.alycloud.channel.common.RequestClient;

/**
 * 易联支付接口HTTP实现类
 * @author Moyq5
 * @date 2017年4月19日
 */
public class PayecoHttpClient extends CommonHttpClient implements RequestClient {

	@Override
	public String request(String server, String queryString) {
		return super.post(server + "/ppi/merchant/itf.do?" + queryString, "");
	}
}
