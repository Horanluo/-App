package com.alycloud.channel.yufu.merch.support;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.yufu.merch.bean.ResultAbstract;
import com.alycloud.channel.yufu.merch.support.utils.JSON;
import com.alycloud.channel.yufu.merch.support.utils.MD5;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * 接口API操作类抽象类
 * @author Moyq5
 * @date 2017年8月1日
 * @param <D>
 * @param <R>
 */
public abstract class AbstractClient<D, R extends ResultAbstract> implements Client<D, R> {

	private static Logger log = LoggerFactory.getLogger(AbstractClient.class);
	private static final String CHARSET = "UTF-8";
	private Context context;
	private Class<R> resultClass;
	protected AbstractClient(Class<R> resultClass) {
		this.resultClass = resultClass;
	}
	
	@Override
	public R post(D data) {
		R resResult = null;
		try {
			Config cfg = context.getConfig();
			HttpClient client = context.getHttpClient();
			String reqBody = getReqBody(data);
			log.info("请求御付渠道地址:"+cfg.getServerPath());
			String resBody = client.post(cfg.getServerPath(), reqBody);
			String sign = sign(resBody);
			resResult = JSON.toObject(resBody, resultClass);
			if (!sign.equals(resResult.getSign())) {
				throw new Exception("签名失败");
			}
		} catch (Exception e) {
			log.error("接口调用失败", e);
			try {
				resResult = resultClass.newInstance();
				resResult.setResultCode("9999");
				resResult.setResultMessage(e.getMessage());
			} catch (Exception e1) {
				log.error("接口调用失败", e1);
			}
		}
		return resResult;
	}
	
	protected Context getContext() {
		return context;
	}

	protected void setContext(Context context) {
		this.context = context;
	}
	
	private String getReqBody(D data) throws Exception {
		String jsonString = JSON.toString(data);
		Map<String, String> dataMap = getSortedFields(jsonString);
		String sign = sign(dataMap);
		dataMap = getSortedFields(jsonString, false);
		dataMap.put("sign", sign);
		return toQueryString(dataMap);
	}
	
	private String sign(String jsonString) throws Exception {
		Map<String, String> dataMap = getSortedFields(jsonString);
		return sign(dataMap);
	}
	
	private String sign(Map<String, String> dataMap) throws Exception {
		List<String> ignoreFields = new ArrayList<String>();
		ignoreFields.add("sign");
		String queryString = toQueryString(dataMap, ignoreFields);
		queryString = queryString + context.getConfig().getKey();
		log.debug("签名内容：{}", queryString);
		return MD5.MD5Encode(queryString).toUpperCase();
	}
	
	private static String toQueryString(Map<String, String> data) throws Exception {
		return toQueryString(data, null);
	}
	
	private static String toQueryString(Map<String, String> data, List<String> ignoreFields) throws JsonParseException, IOException {
		StringBuffer sb = new StringBuffer();
		String value;
		String fieldName;
		for(Map.Entry<String, String> entry : data.entrySet()) {
			try {
				fieldName = entry.getKey();
				if (null != ignoreFields && ignoreFields.contains(fieldName)) {
					continue;
				}
				value = entry.getValue();
				if (null == value || value.contentEquals("null") || value.isEmpty()) {
					continue;
				}
				sb.append(fieldName + "=" + value + "&");
			} catch(Exception e) {
				log.warn("数据转换失败：{}", e.getMessage());
			}
		}
		if (data.size() > 0) {
			sb.delete(sb.length()-1, sb.length());// 去掉最后的“&”
		}
		log.debug("组装结果：{}", sb.toString());
		return sb.toString();
	}
	
	private static Map<String, String> getSortedFields(String jsonString) throws JsonParseException, IOException {
		return getSortedFields(jsonString, null, false);
	}
	
	private static Map<String, String> getSortedFields(String jsonString, Boolean encodeUrl) throws JsonParseException, IOException {
		return getSortedFields(jsonString, null, encodeUrl);
	}
	
	private static Map<String, String> getSortedFields(String jsonString, List<String> ignoreFields, Boolean encodeUrl) throws JsonParseException, IOException {
		Map<String, String> fields = new TreeMap<String, String>();
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser jp = jsonFactory.createParser(jsonString);
		jp.nextToken();
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			String fieldName = jp.getCurrentName();
			jp.nextToken();
			if (null == ignoreFields || !ignoreFields.contains(fieldName)) {
				String value = jp.getValueAsString();
				if (null != value) {
					if (null != encodeUrl && encodeUrl) {
						value = URLEncoder.encode(value, CHARSET);
					}
					fields.put(fieldName, value);
				}
				
			}
		}
		jp.close();
		return fields;
	}
	
}
