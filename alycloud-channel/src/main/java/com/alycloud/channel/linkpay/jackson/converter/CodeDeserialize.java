package com.alycloud.channel.linkpay.jackson.converter;

import com.alycloud.channel.linkpay.enums.Code;
import com.fasterxml.jackson.databind.util.StdConverter;

public class CodeDeserialize extends StdConverter<String, Code> {

	@Override
	public Code convert(String value) {
		Code[] types = Code.values();
		for (Code type : types) {
			if (type.name().contentEquals("C" + value)) {
				return type;
			}
		}
		return null;
	}

}
