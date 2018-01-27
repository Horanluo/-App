package com.alycloud.channel.support.config;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonHttpClient {
	
	protected Logger log = LoggerFactory.getLogger(CommonHttpClient.class);
	
	private static final String CHARSET = "UTF-8";
	
	public String post(String serverPath, String postBody) {
		log.debug("请求地址：{}", serverPath);
		log.debug("请求内容：{}", postBody);
		Header header = new BasicHeader("Content-Type", getContentType().toString());
		HttpUriRequest httpUriRequest = RequestBuilder
				.post()
				.setHeader(header)
				.setUri(serverPath)
				.setEntity(new StringEntity(postBody, CHARSET))
				.build();
		CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
		CloseableHttpResponse response;
		try {
			response = httpClient.execute(httpUriRequest);
			int status = response.getStatusLine().getStatusCode();
			log.debug("远程接口返回状态：{}", response.getStatusLine());
	        if (status == 200) {
	        	String resBody = EntityUtils.toString(response.getEntity(), CHARSET);
	        	log.debug("响应内容：{}", resBody);
				return resBody;
	        } 
		} catch (Exception e) {
			log.error("远程接口调用失败", e);
		} 
		return null;
	}

	protected ContentType getContentType() {
		return ContentType.APPLICATION_FORM_URLENCODED;
	}

}
