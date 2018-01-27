package com.alycloud.channel.esyto.jackson.converter;

import com.alycloud.channel.esyto.enums.OrderStatus;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author Moyq5
 * @date 2017年9月30日
 */
public class OrderStatusDeserialize extends StdConverter<Integer, OrderStatus> {

	@Override
	public OrderStatus convert(Integer value) {
		if (null == value) {
			return null;
		}
		return OrderStatus.values()[value];
	}

}
