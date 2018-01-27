package com.alycloud.channel.linkpay.support;

/**
 * 江苏电子支付配置参数
 * @author Moyq5
 * @date 2017年5月8日
 */
public interface Config {

	String getServerPath();

	String getPublicKey();

	String getGroupId();

	String getMerchantCode();

	String getTerminalCode();

	String getQueryPath();

}
