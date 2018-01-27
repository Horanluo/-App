package com.alycloud.channel.payeco.payment.jackson.converter;

import com.alycloud.channel.payeco.payment.enums.PayState;
import com.fasterxml.jackson.databind.util.StdConverter;

public class PayStateDeserialize extends StdConverter<String, PayState> {

	@Override
	public PayState convert(String payState) {
		PayState[] states = PayState.values();
		for (PayState state : states) {
			if (state.getValue().equals(payState)) {
				return state;
			}
		}
		return null;
	}

}
