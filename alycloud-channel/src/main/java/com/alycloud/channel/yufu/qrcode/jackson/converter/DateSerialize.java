package com.alycloud.channel.yufu.qrcode.jackson.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author Moyq5
 * @date 2017年8月29日
 */
public class DateSerialize extends StdConverter<Date, String> {

	@Override
	public String convert(Date value) {
		return new SimpleDateFormat("yyyMMddHHmmss").format(value);
	}

}
