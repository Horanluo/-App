package com.alycloud.channel.payeco.jackson.converter;

import com.alycloud.channel.payeco.enums.OrderStatus;
import com.fasterxml.jackson.databind.util.StdConverter;

public class OrderStatusDeserialize extends StdConverter<String, OrderStatus> {

	@Override
	public OrderStatus convert(String arg0) {
		return OrderStatus.values()[Integer.parseInt(arg0)];
	}

}
