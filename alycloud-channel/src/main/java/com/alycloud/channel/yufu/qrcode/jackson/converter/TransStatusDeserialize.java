package com.alycloud.channel.yufu.qrcode.jackson.converter;

import com.alycloud.channel.yufu.qrcode.enums.TransStatus;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author Moyq5
 * @date 2017年8月29日
 */
public class TransStatusDeserialize extends StdConverter<String, TransStatus> {

	@Override
	public TransStatus convert(String value) {
		if (null == value) {
			return null;
		}
		return TransStatus.values()[Integer.parseInt(value)];
	}

}
