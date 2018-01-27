package com.alycloud.channel.support.config;

/**
 * @author Moyq5
 * @date 2017年8月18日
 */
public class PinganConfig implements com.alycloud.channel.pingan.support.Config {

	private int type;

	public PinganConfig() {
		type = Config.getInt("pingan-type");
	}
	
	@Override
	public String getServerPath() {
		return Config.getString("pingan-serverPath-" + type);
	}

	@Override
	public String getPublicKey() {
		return Config.getString("pingan-publicKey-" + type);
	}
	
}
