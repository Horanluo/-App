package com.alycloud.channel.payeco.payment.jackson.converter;

import java.io.IOException;

import com.alycloud.channel.payeco.payment.enums.MsgType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MsgTypeSerialize extends JsonSerializer<MsgType> {

	@Override
	public void serialize(MsgType value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeNumber(value.getValue());
	}

}
