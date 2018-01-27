package com.alycloud.channel.linkpay.jackson.converter;

import com.alycloud.channel.linkpay.enums.Service;
import com.fasterxml.jackson.databind.util.StdConverter;

public class ServiceDeserialize extends StdConverter<String, Service> {

	@Override
	public Service convert(String value) {
		Service[] types = Service.values();
		for (Service type : types) {
			if (type.name().contentEquals(value)) {
				return type;
			}
		}
		return null;
	}

}
