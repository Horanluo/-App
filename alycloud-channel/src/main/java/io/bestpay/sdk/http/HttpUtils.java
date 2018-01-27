/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.bestpay.framework.base.SpecificRecordBase;
import io.bestpay.framework.base.SpecificRecordUtils;
import io.bestpay.sdk.base.RequestData;
import io.bestpay.sdk.base.RequestParams;
import io.bestpay.sdk.base.ResponseDataWrapper;
import io.bestpay.sdk.sign.RsaDataEncrypt;
import io.bestpay.sdk.sign.SignUtils;

public class HttpUtils {
	
	public static final String SIGN_PARAMETER = "sign";
	public static final String CHARSET = "utf-8";
	
	public static String postJson(String url, Map<String, String> map) {
		return post(url, map, ContentType.APPLICATION_JSON);
	}
	
	/**
	 * json提交
	 * @param url
	 * @param jsonObject
	 * @return
	 */
	public static String post(String url, Object jsonObject) {
		CloseableHttpClient client = HttpClientFactory.getHttpClient();
		try {
			RequestBuilder rb = RequestBuilder.post(url);
			rb.setHeader("Content-Type", ContentType.APPLICATION_JSON.withCharset(CHARSET).toString());
			rb.setEntity(new StringEntity((new ObjectMapper()).writeValueAsString(jsonObject), CHARSET));
			
			HttpUriRequest post = rb.build();
			HttpResponse resp = client.execute(post);
			if (null != resp.getStatusLine()) {
				StatusLine sl = resp.getStatusLine();
				if (sl.getStatusCode() <200 || sl.getStatusCode() >= 300) {
					throw new HttpException(String.format("%s:%s", sl.getStatusCode(), sl.getReasonPhrase()));
				}
			}
			return EntityUtils.toString(resp.getEntity(), CHARSET);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(client);
		}
	}
	
	/**
	 * post请求
	 * @param url
	 * @param map
	 * @return
	 */
	public static String post(String url, Map<String, String> map, ContentType contentType) {
		CloseableHttpClient client = HttpClientFactory.getHttpClient();
		try {
			RequestBuilder rb = RequestBuilder.post(url);
			rb.setHeader("Content-Type", contentType.withCharset("UTF-8").toString());
			if (contentType.equals(ContentType.APPLICATION_FORM_URLENCODED)) {
				List<NameValuePair> names = new ArrayList<NameValuePair>();
				for (String key : map.keySet()) {
					Object value = map.get(key);
					if (null != value) {
						names.add(new BasicNameValuePair(key, String.valueOf(value)));
					}
				}
				rb.setEntity(new UrlEncodedFormEntity(names, CHARSET));
			} else if (contentType.equals(ContentType.APPLICATION_JSON)) {
				rb.setEntity(new StringEntity((new ObjectMapper()).writeValueAsString(map), CHARSET));
			} else {
				throw new UnsupportedOperationException("不支持的协议类型:" + contentType.getMimeType());
			}
			
			HttpUriRequest post = rb.build();
			HttpResponse resp = client.execute(post);
			if (null != resp.getStatusLine()) {
				StatusLine sl = resp.getStatusLine();
				if (sl.getStatusCode() <200 || sl.getStatusCode() >= 300) {
					throw new HttpException(String.format("%s:%s", sl.getStatusCode(), sl.getReasonPhrase()));
				}
			}
			return EntityUtils.toString(resp.getEntity(), CHARSET);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(client);
		}
	}
	
	/**
	 * get请求
	 * @param url
	 * @param map
	 * @return
	 */
	public static String get(String url, Map<String, String> map) {
		CloseableHttpClient client = HttpClientFactory.getHttpClient();
		try {
			RequestBuilder rb = RequestBuilder.get(url);
			List<NameValuePair> names = new ArrayList<NameValuePair>();
			for (String key : map.keySet()) {
				Object value = map.get(key);
				if (null != value) {
					names.add(new BasicNameValuePair(key, String.valueOf(value)));
				}
			}
			rb.setEntity(new UrlEncodedFormEntity(names, CHARSET));
			
			HttpUriRequest request = rb.build();
			HttpResponse resp = client.execute(request);
			if (null != resp.getStatusLine()) {
				StatusLine sl = resp.getStatusLine();
				if (sl.getStatusCode() <200 || sl.getStatusCode() >= 300) {
					throw new HttpException(String.format("%s:%s", sl.getStatusCode(), sl.getReasonPhrase()));
				}
			}
			return EntityUtils.toString(resp.getEntity(), CHARSET);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(client);
		}
	}
	
	/**
	 * 获取json数据
	 * @param url
	 * @param map
	 * @return
	 */
	public static Map<String, ?> getAsMap(String url, Map<String, String> map) {
		String response = get(url, map);
		ObjectMapper om = new ObjectMapper();
		try { 
			return om.readValue(response, Map.class);
		} catch (Exception e) {
			throw new RuntimeException("解析json错误", e);
		}
	}
	
	/**
	 * post请求
	 * @param url
	 * @param record
	 * @return
	 */
	public static String post(String url, SpecificRecordBase record) {
		return post(url, SpecificRecordUtils.toMap(record), ContentType.APPLICATION_JSON);
	}
	
	/**
	 * 提交并且签名
	 * @param url
	 * @param data
	 * @param signable
	 * @return
	 */
	public static String post(String url, RequestParams requestParams, Map<String, String> data, DataSignable<Map<String, String>> signable) {
		String sign = signable.sign(requestParams, data);
		data.put(SIGN_PARAMETER, sign);
		return post(url, data, ContentType.APPLICATION_FORM_URLENCODED);
	}
	
	/**
	 * 提交并且签名
	 * @param url
	 * @param data
	 * @param signable
	 * @return
	 */
	public static String post(String url, RequestParams requestParams, SpecificRecordBase record, DataSignable<SpecificRecordBase> signable) {
		Map<String, String> map = SpecificRecordUtils.toMap(record);
		Map<String, String> baseMap = SpecificRecordUtils.toMap(requestParams);
		map.putAll(baseMap);
		String sign = signable.sign(requestParams, record);
		map.put(SIGN_PARAMETER, sign);
		return post(url, map, ContentType.APPLICATION_FORM_URLENCODED);
	}
	
	/**
	 * 请求加密并且签名
	 * @param url
	 * @param requestData
	 * @param data
	 * @param requestDataEncrypt, RsaDataEncrypt re
	 * @return
	 */
	public static ResponseDataWrapper post(String url, RequestData requestData, SpecificRecordBase data, RsaDataEncrypt requestDataEncrypt, RsaDataEncrypt responseDataVerify) {
		RequestData.Builder builder = RequestData.newBuilder(requestData);
		RequestData tmp = builder.setSign("").setData("").build();
		
		try {
			if (null != data) {
				byte[] encryptData = requestDataEncrypt.encrypt(data);
				tmp.put("data", new String(encryptData, SignUtils.DEFAULT_CHARSET));
			}
			byte[] signData = SignUtils.getSortString(tmp);
			String sign = requestDataEncrypt.sign(signData);
			tmp.put("sign", sign);
			
			String response = post(url, tmp);
			
			return ResponseDataWrapper.parse(response, responseDataVerify);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
