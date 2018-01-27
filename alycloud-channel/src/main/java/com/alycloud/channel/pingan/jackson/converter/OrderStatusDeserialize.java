package com.alycloud.channel.pingan.jackson.converter;

import com.alycloud.channel.pingan.enums.OrderStatus;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年6月14日
 */
public class OrderStatusDeserialize extends StdConverter<Integer, OrderStatus> {

	@Override
	public OrderStatus convert(Integer value) {
		if (value == 1) {
			return OrderStatus.SUCCESS;
		}
		if (value == 2) {
			return OrderStatus.WAIT_PAY;
		}
		if (value == 4) {
			return OrderStatus.CANCELED;
		}
		if (value == 9) {
			return OrderStatus.WAIT_PWD;
		}
		return null;
	}

}
