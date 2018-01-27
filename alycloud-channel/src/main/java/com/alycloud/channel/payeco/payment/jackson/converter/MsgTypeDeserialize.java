package com.alycloud.channel.payeco.payment.jackson.converter;

import com.alycloud.channel.payeco.payment.enums.MsgType;
import com.fasterxml.jackson.databind.util.StdConverter;

public class MsgTypeDeserialize extends StdConverter<Integer, MsgType> {

	@Override
	public MsgType convert(Integer msgType) {
		MsgType[] types = MsgType.values();
		for (MsgType type : types) {
			if (type.getValue() == msgType) {
				return type;
			}
		}
		return null;
	}

}
