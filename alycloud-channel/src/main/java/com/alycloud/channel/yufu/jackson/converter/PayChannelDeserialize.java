package com.alycloud.channel.yufu.jackson.converter;

import com.alycloud.channel.yufu.enums.PayChannel;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * 
 * @author Moyq5
 * @date 2017年8月2日
 */
public class PayChannelDeserialize extends StdConverter<String, PayChannel> {

	@Override
	public PayChannel convert(String value) {
		PayChannel[] channels = PayChannel.values();
		for (PayChannel channel: channels) {
			if (channel.getValue().equals(value)) {
				return channel;
			}
		}
		return null;
	}

}
