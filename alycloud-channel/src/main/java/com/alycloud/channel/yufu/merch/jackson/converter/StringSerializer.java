package com.alycloud.channel.yufu.merch.jackson.converter;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class StringSerializer extends StdSerializer<Object> {

	protected StringSerializer() {
		super(Object.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		StringWriter sw = new StringWriter();
		JsonGenerator jg = gen.getCodec().getFactory().createGenerator(sw);
		jg.writeObject(value);
		gen.writeString(sw.getBuffer().toString());
	}


}
