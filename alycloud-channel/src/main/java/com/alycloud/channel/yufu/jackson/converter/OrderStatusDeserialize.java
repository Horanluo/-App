package com.alycloud.channel.yufu.jackson.converter;

import com.alycloud.channel.yufu.enums.OrderStatus;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年8月2日
 */
public class OrderStatusDeserialize extends StdConverter<Integer, OrderStatus> {

	@Override
	public OrderStatus convert(Integer value) {
		return OrderStatus.values()[value];
	}

}
