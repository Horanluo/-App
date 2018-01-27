package com.alycloud.channel.support.config;

/**
 * 御付（二维码支付）渠道配置
 * @author Moyq5
 * @date 2017年8月30日
 */
public class YufuQrcodePayConfig implements com.alycloud.channel.yufu.qrcode.support.Config {

	private int type;

	public YufuQrcodePayConfig() {
		type = Config.getInt("yufu-qrcode-type");
	}
	
	@Override
	public String getServerPath() {
		return Config.getString("yufu-qrcode-pay-serverPath-" + type);
	}

	@Override
	public String getKey() {
		return Config.getString("yufu-qrcode-pay-key-" + type);
	}
}
