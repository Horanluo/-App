package com.alycloud.channel.support.config;

/**
 * 御付（快捷支付）渠道配置
 * @author Moyq5
 * @date 2017年8月30日
 */
public class YufuFastPayConfig implements com.alycloud.channel.yufu.support.Config {

	private int type;

	public YufuFastPayConfig() {
		type = Config.getInt("yufu-fast-type");
	}
	
	@Override
	public String getServerPath() {
		return Config.getString("yufu-fast-pay-serverPath-" + type);
	}

	@Override
	public String getPayPath() {
		return Config.getString("yufu-fast-pay-payPath-" + type);
	}

	@Override
	public String getKey() {
		return Config.getString("yufu-fast-pay-key-" + type);
	}


}
