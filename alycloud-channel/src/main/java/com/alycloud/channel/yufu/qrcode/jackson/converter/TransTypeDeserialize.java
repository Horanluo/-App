package com.alycloud.channel.yufu.qrcode.jackson.converter;

import com.alycloud.channel.yufu.qrcode.enums.TransType;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author Moyq5
 * @date 2017年8月29日
 */
public class TransTypeDeserialize extends StdConverter<String, TransType> {

	@Override
	public TransType convert(String value) {
		if (null == value) {
			return null;
		}
		return TransType.values()[Integer.parseInt(value) - 1];
	}

}
