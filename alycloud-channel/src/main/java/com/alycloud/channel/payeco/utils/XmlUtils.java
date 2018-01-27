package com.alycloud.channel.payeco.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public abstract class XmlUtils {
	
	private static Logger log = LoggerFactory.getLogger(XmlUtils.class);
	
	private static ObjectMapper om = new XmlMapper();
	
	static {
		om.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	public static <T> T toObject(String src, Class<T> clazz) {
		try {
			return om.readValue(src, clazz);
		} catch (IOException e) {
			log.error("xml解析失败", e);
		}
		return null;
	}
	
}
