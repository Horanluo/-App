package com.alycloud.channel.yufu.qrcode.jackson.converter;

import com.alycloud.channel.yufu.qrcode.enums.ChannelFlag;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * @author Moyq5
 * @date 2017年8月29日
 */
public class ChannelFlagSerialize extends StdConverter<ChannelFlag, String> {

	@Override
	public String convert(ChannelFlag value) {
		return "0" + value.ordinal();
	}

}
