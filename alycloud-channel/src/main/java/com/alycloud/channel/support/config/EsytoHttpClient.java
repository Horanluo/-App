package com.alycloud.channel.support.config;

import org.apache.http.entity.ContentType;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.esyto.support.HttpClient;



/**
 * 【易商付】渠道（商户版）HTTP实现
 * @author Moyq5
 * @date 2017年10月8日
 */
public class EsytoHttpClient extends CommonHttpClient implements HttpClient {
	
	public EsytoHttpClient() {
		log = LoggerFactory.getLogger(EsytoHttpClient.class);
	}

	@Override
	protected ContentType getContentType() {
		return ContentType.APPLICATION_JSON;
	}
}
