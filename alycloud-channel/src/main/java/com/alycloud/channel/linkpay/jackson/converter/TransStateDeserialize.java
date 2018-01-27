package com.alycloud.channel.linkpay.jackson.converter;

import com.alycloud.channel.linkpay.enums.TransState;
import com.fasterxml.jackson.databind.util.StdConverter;

public class TransStateDeserialize extends StdConverter<Integer, TransState> {

	@Override
	public TransState convert(Integer value) {
		TransState[] types = TransState.values();
		for (TransState type : types) {
			if (type.getValue().intValue() == value) {
				return type;
			}
		}
		return null;
	}

}
