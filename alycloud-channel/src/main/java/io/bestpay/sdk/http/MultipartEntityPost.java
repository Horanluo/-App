/*
 *  @Copyright 2017 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.http;

import java.io.File;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class MultipartEntityPost {

	public static final String CHARSET_UTF8 = "UTF-8";
	private final MultipartEntityBuilder builder;
	private final String url;
	private String charset = CHARSET_UTF8;
	private String key;
	
	public MultipartEntityPost(String url) {
		super();
		this.url = url;
		this.builder = MultipartEntityBuilder.create();
	}
	
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public MultipartEntityPost add(Map<String, String> map) {
		if (null != map) {
			for (String name : map.keySet()) {
				this.builder.addTextBody(name, map.get(name));
			}
		}
		return this;
	}
	
	public MultipartEntityPost add(String name, File file) {
		if (null != file && null != name) {
			this.builder.addBinaryBody(name, file);
		}
		return this;
	}
	
	public String post() throws Exception {
		CloseableHttpClient client = HttpClientFactory.getHttpClient();
		RequestBuilder rb = RequestBuilder.post(this.url);
		HttpEntity entity = builder.build();
		rb.setEntity(entity);
		this.createAuthorization(rb);
		HttpUriRequest request = rb.build();
		CloseableHttpResponse resp = client.execute(request);
		if (null != resp.getStatusLine()) {
			StatusLine sl = resp.getStatusLine();
			if (sl.getStatusCode() < 200 || sl.getStatusCode() >= 300) {
				throw new HttpException(String.format("%s:%s", sl.toString(), sl.getReasonPhrase()));
			}
		}
		String respData = EntityUtils.toString(resp.getEntity(), this.getCharset());
		return respData;
	}
	
	protected void createAuthorization(RequestBuilder rb) {
		
	}
}
