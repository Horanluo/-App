/*
 *  @Copyright 2017 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class AvroUtils {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	/**
	 * 获取值
	 * @param map
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Map<String, Object> map, String key, String defaultValue) {
		if (map.containsKey(key)) {
			Object value = map.get(key);
			if (null != value) {
				return String.valueOf(value);
			}
		}
		return defaultValue;
	}
	
	public static Boolean getBoolean(Map<String, Object> map, String key, Boolean defaultValue) {
		String value = getString(map, key, null);
		try {
			if (null != value) {
				return Boolean.parseBoolean(value);
			}
		} catch (Exception e) {
		}
		return defaultValue;
	}
	
	public static Long getLong(Map<String, Object> map, String key, Long defaultValue) {
		String value = getString(map, key, null);
		try {
			if (null != value) {
				return Long.parseLong(value);
			}
		} catch (NumberFormatException e) {
		}
		return defaultValue;
	}
	
	public static Integer getInt(Map<String, Object> map, String key, Integer defaultValue) {
		String value = getString(map, key, null);
		try {
			if (null != value) {
				return Integer.parseInt(value);
			}
		} catch (NumberFormatException e) {
		}
		return defaultValue;
	}
	
	public static Double getDouble(Map<String, Object> map, String key, Double defaultValue) {
		String value = getString(map, key, null);
		try {
			if (null != value) {
				return Double.parseDouble(value);
			}
		} catch (NumberFormatException e) {
		}
		return defaultValue;
	}
	
	public static Float getFloat(Map<String, Object> map, String key, Float defaultValue) {
		String value = getString(map, key, null);
		try {
			if (null != value) {
				return Float.parseFloat(value);
			}
		} catch (NumberFormatException e) {
		}
		return defaultValue;
	}
	
	public static Boolean getBoolean(Object value, boolean defaultValue) {
		try {
			return Boolean.parseBoolean(value.toString());
		} catch (Exception e) {
		}
		return defaultValue;
	}
	
	public static Integer getInt(Object value, Integer defaultValue) {
		try {
			return Integer.parseInt(value.toString());
		} catch (Exception e) {
		}
		return defaultValue;
	}
	
	public static Double getDouble(Object value, Double defaultValue) {
		try {
			return Double.parseDouble(value.toString());
		} catch (Exception e) {
		}
		return defaultValue;
	}
	
	public static Long getLong(Object value, Long defaultValue) {
		try {
			return Long.parseLong(value.toString());
		} catch (Exception e) {
		}
		return defaultValue;
	}
	
	public static String getString(Object value, String defaultValue) {
		if (null == value) {
			return defaultValue;
		}
		return value.toString();
	}
	
	public static String getString(Object value) {
		return getString(value, "");
	}
	
	public static byte[] toBytes(Object data) {
		try {
			if (null == data) {
				return new byte[0];
			}
			if (data instanceof String) {
				return String.valueOf(data).getBytes();
			}
			
			if (data instanceof Throwable) {
				Throwable throwable = (Throwable)data;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				PrintWriter pw = new PrintWriter(out);
				throwable.printStackTrace(pw);
				return out.toByteArray();
			}
			
			return OBJECT_MAPPER.writeValueAsBytes(data);
		} catch (Exception e) {
		}
		return new byte[0];
	}
	
	public static ByteBuffer toByteBuffer(Object data) {
		return ByteBuffer.wrap(toBytes(data));
	}
}
