package com.alycloud.channel.esyto.support;

/**
 * 交易配置参数
 * @author Moyq5
 * @date 2017年9月29日
 */
public interface Config {

	/**
	 * 服务地址
	 * @author Moyq5
	 * @date 2017年9月29日
	 * @return
	 */
	String getServerPath();

	/**
	 * 密钥
	 * @author Moyq5
	 * @date 2017年9月29日
	 * @return
	 */
	String getKey();

	/**
	 * 商户号
	 * @author Moyq5
	 * @date 2017年9月29日
	 * @return
	 */
	String getMerchNo();
	
}
