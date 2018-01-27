package com.alycloud.channel.pingan.support;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tool {

	private static Logger log = LoggerFactory.getLogger(Tool.class);
			
	public static String toQueryString(Object obj) {
		return toQueryString(obj, null);
	}

	public static String toQueryString(Object obj, List<String> ignoreFields) {
		log.debug("将对象组装成get参数格式字符串");
		log.debug("源对象：{}", obj.getClass().getName());
		Map<String, String> names = getSortedFields(obj.getClass());
		StringBuffer sb = new StringBuffer();
		String value;
		Method method;
		String fieldName;
		for (Map.Entry<String, String> entry : names.entrySet()) {
			try {
				fieldName = entry.getValue();
				if (null != ignoreFields && ignoreFields.contains(fieldName)) {
					continue;
				}
				method = obj.getClass()
						.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
				value = String.valueOf(method.invoke(obj)).trim();
				if (value.contentEquals("null")) {
					value = "";
				}
				sb.append(entry.getKey() + "=" + value + "&");
			} catch (Exception e) {
				log.warn("数据转换失败：{}", e.getMessage());
			}
		}
		if (names.size() > 0) {
			sb.delete(sb.length() - 1, sb.length());// 去掉最后的“&”
		}
		log.debug("组装结果：{}", sb.toString());
		return sb.toString();
	}

	private static Map<String, String> getSortedFields(Class<?> clazz) {
		return getSortedFields(null, clazz);
	}

	private static Map<String, String> getSortedFields(TreeMap<String, String> names, Class<?> clazz) {
		if (null == names) {
			names = new TreeMap<String, String>();
		}
		if (clazz == Object.class) {
			return names;
		}
		Field[] fields = clazz.getDeclaredFields();
		JsonProperty prop;
		for (Field field : fields) {
			prop = field.getAnnotation(JsonProperty.class);
			String paramName;
			if (null != prop) {
				paramName = prop.value();
			} else {
				paramName = field.getName();
			}
			names.put(paramName, field.getName());
		}
		return getSortedFields(names, clazz.getSuperclass());
	}

}
