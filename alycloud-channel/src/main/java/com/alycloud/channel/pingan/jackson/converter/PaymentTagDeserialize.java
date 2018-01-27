package com.alycloud.channel.pingan.jackson.converter;

import com.alycloud.channel.pingan.enums.PaymentTag;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年6月19日
 */
public class PaymentTagDeserialize extends StdConverter<String, PaymentTag> {

	@Override
	public PaymentTag convert(String tag) {
		for (PaymentTag payType: PaymentTag.values()) {
			if (payType.getValue().equals(tag)) {
				return payType;
			}
		}
		return null;
	}

}
