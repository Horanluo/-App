/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtils {
	public static String getFormatPattern(long value, long minLength) {
		if (value < 0) {
			throw new IllegalArgumentException("格式值必须大于或等于0");
		}
		if (minLength <= 0) {
			throw new IllegalArgumentException("最小长度必须大于0");
		}
		String str = String.valueOf(value);
		long length = minLength;
		if (str.length() > length) {
			length = str.length();
		}
		StringBuffer sb = new StringBuffer();
		for (long i=0; i<length; i++) {
			sb.append("0");
		}
		return sb.toString();
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 格式化时间 yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date) {
		return format(date, "yyyyMMddHHmmss");
	}
	/**
	 * 格式化日期  yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return format(date, "yyyyMMdd");
	}
	
	/**
	 * 转换成uuid
	 * @param strings
	 * @return
	 */
	public static String getUUID(String ...strings) {
		if (null == strings || strings.length == 0) {
			new IllegalArgumentException("参数个数必须大于等于1");
		}
		try {
			StringBuffer sb = new StringBuffer();
			for (String s : strings) {
				sb.append(s).append(".");
			}
			return UUID.nameUUIDFromBytes(sb.toString().getBytes("utf-8")).toString().replaceAll("-", "");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
