package com.alycloud.channel.esyto.jackson.converter;

import com.alycloud.channel.esyto.enums.PayType;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author Moyq5
 * @date 2017年9月30日
 */
public class PayTypeDeserialize extends StdConverter<Integer, PayType> {

	@Override
	public PayType convert(Integer value) {
		if (null == value) {
			return null;
		}
		return PayType.values()[value];
	}

}
