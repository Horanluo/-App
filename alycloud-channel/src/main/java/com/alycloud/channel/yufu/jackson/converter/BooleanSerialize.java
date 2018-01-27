package com.alycloud.channel.yufu.jackson.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年6月24日
 */
public class BooleanSerialize extends StdConverter<Boolean, String> {

	@Override
	public String convert(Boolean value) {
		if (value) {
			return "1";
		}
		return "0";
	}

}
