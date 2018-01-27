package com.alycloud.channel.payeco.jackson.converter;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.util.StdConverter;

public class BigDecimalConverter extends StdConverter<String, BigDecimal> {

	@Override
	public BigDecimal convert(String arg0) {
		return new BigDecimal(arg0);
	}

}
