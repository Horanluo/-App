package com.alycloud.channel.linkpay.jackson.converter;

import com.alycloud.channel.linkpay.enums.Service;
import com.fasterxml.jackson.databind.util.StdConverter;

public class ServiceSerialize extends StdConverter<Service, String> {

	@Override
	public String convert(Service service) {
		return service.name();
	}

}
