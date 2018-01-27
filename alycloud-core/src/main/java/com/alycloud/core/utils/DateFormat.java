package com.alycloud.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间格式化
 * @author Moyq5
 * @date 2017年9月28日
 */
public enum DateFormat {

	DATE("yyyy-MM-dd"),
	TIME("HH:mm:ss"),
	DATE_TIME("yyyy-MM-dd HH:mm:ss");
	
	private String pattern;

	DateFormat(String pattern) {
		this.pattern = pattern;
	}

	public String format(Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public String format() {
		return new SimpleDateFormat(pattern).format(new Date());
	}
	
	public Date parse(String source) {
		try {
			return new SimpleDateFormat(pattern).parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
