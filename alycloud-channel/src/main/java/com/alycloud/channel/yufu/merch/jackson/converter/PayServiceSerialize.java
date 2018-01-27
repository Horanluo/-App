package com.alycloud.channel.yufu.merch.jackson.converter;

import com.alycloud.channel.yufu.merch.enums.PayService;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年7月31日
 */
public class PayServiceSerialize extends StdConverter<PayService, String> {

	@Override
	public String convert(PayService service) {
		return service.name();
	}

}
