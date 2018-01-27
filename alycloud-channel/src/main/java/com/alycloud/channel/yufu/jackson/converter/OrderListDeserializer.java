package com.alycloud.channel.yufu.jackson.converter;

import java.io.IOException;

import com.alycloud.channel.yufu.bean.OrderList;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class OrderListDeserializer extends StdDeserializer<OrderList> {

	private static final long serialVersionUID = 1L;

	protected OrderListDeserializer() {
		super(String.class);
	}

	@Override
	public OrderList deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Object value = p.getInputSource();
		if (null == value || value instanceof String) {
			return null;
		}
		return p.readValueAs(OrderList.class);
	}

}
