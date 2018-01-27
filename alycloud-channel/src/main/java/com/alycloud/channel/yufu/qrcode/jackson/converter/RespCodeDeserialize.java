package com.alycloud.channel.yufu.qrcode.jackson.converter;

import com.alycloud.channel.yufu.qrcode.enums.RespCode;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年8月28日
 */
public class RespCodeDeserialize extends StdConverter<String, RespCode> {

	@Override
	public RespCode convert(String value) {
		RespCode code;
		if (value.equals("0000")) {
			code = RespCode.SUCCESS;
		} else {
			code = RespCode.FAIL;
		}
		code.setValue(value);
		return code;
	}

}
