package com.alycloud.pay.support.channel.api.impl;

import com.alycloud.channel.pingan.support.Config;
import com.alycloud.modules.entity.Channel;

/**
 * 平安API交易配置参数
 * @author Moyq5
 * @date 2017年10月12日
 */
public class PinganConfig implements Config {

	private Channel channel;
	
	public PinganConfig(Channel channel) {
		this.channel = channel;
	}
	
	@Override
	public String getServerPath() {
		return channel.getPayUrl();
	}

	@Override
	public String getPublicKey() {
		return channel.getKeyPublic();
	}
	
}
