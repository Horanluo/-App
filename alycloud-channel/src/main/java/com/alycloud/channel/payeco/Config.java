package com.alycloud.channel.payeco;

/**
 * 易联支付配置参数
 * @author Moyq5
 * @date 2017年4月12日
 */
public interface Config {

	public String getPrivateKey();

	public String getPublicKey();
	
	public String getDataEncode();

	public String getServer();
	
	public String getMerchantId();
	
	public String getNotifyUrl();

}
