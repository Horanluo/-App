package com.alycloud.channel.linkpay.support.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JSON {
	
	private static Logger log = LoggerFactory.getLogger(JSON.class);
	
	private static ObjectMapper om = new ObjectMapper();
	
	static {
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	public static <T> T toObject(String src, Class<T> clazz) {
		try {
			return om.readValue(src, clazz);
		} catch (IOException e) {
			log.error("json解析失败", e);
		}
		return null;
	}
	
}
