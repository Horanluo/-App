package com.alycloud.channel.yufu.merch.jackson.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class StringDeserializer extends StdDeserializer<String> {

	private static final long serialVersionUID = 1L;

	protected StringDeserializer() {
		super(Object.class);
	}

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return p.getCodec().readTree(p).toString();
	}

}
