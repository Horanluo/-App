package com.alycloud.channel.pingan.jackson.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

public class DateDeserialize extends StdConverter<String, Date> {

	@Override
	public Date convert(String arg0) {
		Date date = null;
		if (null != arg0 && !arg0.isEmpty()) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(arg0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

}
