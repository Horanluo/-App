package com.alycloud.channel.payeco.jackson.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

public class StringToDateBy_yyyyMMddHHmmss extends StdConverter<String, Date> {

	@Override
	public Date convert(String arg0) {
		Date date = null;
		if (null != arg0) {
			try {
				date = new SimpleDateFormat("yyyyMMddHHmmss").parse(arg0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

}
