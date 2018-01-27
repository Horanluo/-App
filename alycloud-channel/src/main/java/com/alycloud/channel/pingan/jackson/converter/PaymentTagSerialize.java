package com.alycloud.channel.pingan.jackson.converter;

import com.alycloud.channel.pingan.enums.PaymentTag;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年6月14日
 */
public class PaymentTagSerialize extends StdConverter<PaymentTag, String> {

	@Override
	public String convert(PaymentTag type) {
		return type.getValue();
	}

}
