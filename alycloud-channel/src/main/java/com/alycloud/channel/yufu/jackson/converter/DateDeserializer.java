package com.alycloud.channel.yufu.jackson.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年8月2日
 */
public class DateDeserializer extends StdConverter<String, String> {

	@Override
	public String convert(String dateString) {
		if (null == dateString || dateString.isEmpty()) {
			return null;
		}
		Date date;
		try {
			date = new SimpleDateFormat("yyyyMMddHHmmss").parse(dateString);
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
