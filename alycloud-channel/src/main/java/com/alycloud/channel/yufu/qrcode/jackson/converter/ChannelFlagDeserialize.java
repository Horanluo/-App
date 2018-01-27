package com.alycloud.channel.yufu.qrcode.jackson.converter;

import com.alycloud.channel.yufu.qrcode.enums.ChannelFlag;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author Moyq5
 * @date 2017年8月29日
 */
public class ChannelFlagDeserialize extends StdConverter<String, ChannelFlag> {

	@Override
	public ChannelFlag convert(String value) {
		if (null == value) {
			return null;
		}
		return ChannelFlag.values()[Integer.parseInt(value)];
	}

}
