package com.alycloud.channel.payeco.payment.jackson.converter;

import com.alycloud.channel.payeco.payment.enums.TransState;
import com.fasterxml.jackson.databind.util.StdConverter;

public class TransStateConverter extends StdConverter<String, TransState> {

	@Override
	public TransState convert(String transState) {
		TransState[] states = TransState.values();
		for (TransState state : states) {
			if (state.getValue().equals(transState)) {
				return state;
			}
		}
		return null;
	}

}
