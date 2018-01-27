package com.alycloud.channel.yufu.qrcode.support;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.yufu.qrcode.support.utils.MD5;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class Tool {
	
	private static Logger log = LoggerFactory.getLogger(Tool.class);
	
	private static final String CHARSET = "UTF-8";
	
	public static String sign(String jsonString, String key) throws Exception {
		Map<String, String> dataMap = getSortedFields(jsonString, false);
		return sign(dataMap, key);
	}
	
	public static String sign(Map<String, String> dataMap, String key) throws Exception {
		List<String> ignoreFields = new ArrayList<String>();
		ignoreFields.add("signIn");
		ignoreFields.add("signOut");
		String queryString = toQueryString(dataMap, ignoreFields, true);
		queryString = queryString + key;
		log.debug("签名内容：{}", queryString);
		String sign = MD5.MD5Encode(queryString).toUpperCase();
		log.debug("签名值：{}", sign);
		return sign;
	}
	
	public static String toQueryString(Map<String, String> data, List<String> ignoreFields, boolean ignoreNull) throws JsonParseException, IOException {
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
				if (ignoreNull && (null == value || value.contentEquals("null") || value.isEmpty())) {
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
	
	public static Map<String, String> getSortedFields(String jsonString, boolean encodeUrl) throws JsonParseException, IOException {
		Map<String, String> fields = new TreeMap<String, String>();
		JsonFactory jsonFactory = new JsonFactory();
		JsonParser jp = jsonFactory.createParser(jsonString);
		jp.nextToken();
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			String fieldName = jp.getCurrentName();
			jp.nextToken();
			String value = jp.getValueAsString();
			if (null != value && encodeUrl) {
				value = URLEncoder.encode(value, CHARSET);
			}
			fields.put(fieldName, value);
		}
		jp.close();
		return fields;
	}

}