package com.alycloud.channel.linkpay.jackson.converter;

import com.alycloud.channel.linkpay.enums.PayState;
import com.fasterxml.jackson.databind.util.StdConverter;

public class PayStateDeserialize extends StdConverter<Integer, PayState> {

	@Override
	public PayState convert(Integer value) {
		PayState[] types = PayState.values();
		for (PayState type : types) {
			if (type.getValue().intValue() == value) {
				return type;
			}
		}
		return null;
	}

}
