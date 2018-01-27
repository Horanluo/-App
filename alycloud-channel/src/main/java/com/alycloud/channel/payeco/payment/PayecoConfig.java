package com.alycloud.channel.payeco.payment;

/**
 * 易联代付配置参数
 * @author Moyq5
 * @date 2017年4月25日
 */
public interface PayecoConfig {

	public String getPfxPath();

	public String getPfxPass();

	public String getPubKey();

	public String getServer();
	
	public String getUserName();

}
