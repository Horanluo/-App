package com.alycloud.channel.support.config;

public class LinkpayConfig implements com.alycloud.channel.linkpay.support.Config {

	private int type;
	
	public LinkpayConfig() {
		type = Config.getInt("linkpay-type");
	}
	
	@Override
	public String getServerPath() {
		return Config.getString("linkpay-serverPath-" + type);
	}

	@Override
	public String getPublicKey() {
		return Config.getString("linkpay-publicKey-" + type);
	}

	@Override
	public String getGroupId() {
		return Config.getString("linkpay-groupId-" + type);
	}

	@Override
	public String getMerchantCode() {
		return Config.getString("linkpay-merchantCode-" + type);
	}

	@Override
	public String getTerminalCode() {
		return Config.getString("linkpay-terminalCode-" + type);
	}

	@Override
	public String getQueryPath() {
		return Config.getString("linkpay-queryPath-" + type);
	}

}
