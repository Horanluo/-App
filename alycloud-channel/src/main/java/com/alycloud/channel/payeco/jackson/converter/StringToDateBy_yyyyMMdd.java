package com.alycloud.channel.payeco.jackson.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

public class StringToDateBy_yyyyMMdd extends StdConverter<String, Date> {

	@Override
	public Date convert(String arg0) {
		Date date = null;
		if (null != arg0 && !arg0.equals("null")) {
			try {
				date = new SimpleDateFormat("yyyyMMdd").parse(arg0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

}
