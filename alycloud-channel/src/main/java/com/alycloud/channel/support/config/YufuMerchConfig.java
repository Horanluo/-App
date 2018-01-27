package com.alycloud.channel.support.config;

/**
 * 御付（快捷支付商户进件）渠道配置
 * @author Moyq5
 * @date 2017年8月30日
 */
public class YufuMerchConfig implements com.alycloud.channel.yufu.merch.support.Config {

	private int type;

	public YufuMerchConfig() {
		type = Config.getInt("yufu-fast-type");
	}

	@Override
	public String getBranchNo() {
		return Config.getString("yufu-merch-branchNo-" + type);
	}

	@Override
	public String getKey() {
		return Config.getString("yufu-merch-key-" + type);
	}

	@Override
	public String getServerPath() {
		return Config.getString("yufu-merch-serverPath-" + type);
	}
	
}
