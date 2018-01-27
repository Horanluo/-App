package com.alycloud.channel.payeco.payment.jackson.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

public class DateConverte_yyyyMMdd extends StdConverter<String, Date> {

	@Override
	public Date convert(String arg0) {
		Date date = null;
		if (null != arg0) {
			try {
				date = new SimpleDateFormat("yyyyMMdd").parse(arg0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

}
