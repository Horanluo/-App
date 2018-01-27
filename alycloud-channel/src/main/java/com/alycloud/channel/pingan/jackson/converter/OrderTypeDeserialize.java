package com.alycloud.channel.pingan.jackson.converter;

import com.alycloud.channel.pingan.enums.OrderType;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年6月14日
 */
public class OrderTypeDeserialize extends StdConverter<Integer, OrderType> {

	@Override
	public OrderType convert(Integer value) {
		return null == value ? null : OrderType.values()[value - 1];
	}

}
