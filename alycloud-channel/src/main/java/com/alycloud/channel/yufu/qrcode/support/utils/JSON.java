package com.alycloud.channel.yufu.qrcode.support.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Moyq5
 * @date 2017年6月15日
 */
public abstract class JSON {
	
	private static Logger log = LoggerFactory.getLogger(JSON.class);
	
	private static ObjectMapper om = new ObjectMapper();
	
	static {
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		om.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
	}
	
	public static <T> T toObject(String src, Class<T> clazz) {
		try {
			return om.readValue(src, clazz);
		} catch (IOException e) {
			log.error("json解析失败", e);
		}
		return null;
	}
	
	public static String toString(Object obj) {
		try {
			return om.writeValueAsString(obj);
		} catch (IOException e) {
			log.error("json解析失败", e);
		}
		return null;
	}
	
}
