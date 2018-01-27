package com.alycloud.channel.pingan.jackson.converter;

import com.alycloud.channel.pingan.enums.RefundStatus;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年7月10日
 */
public class RefundStatusDeserialize extends StdConverter<Integer, RefundStatus> {

	@Override
	public RefundStatus convert(Integer value) {
		if (value == 1) {
			return RefundStatus.SUCCESS;
		}
		return RefundStatus.FAIL;
	}

}
