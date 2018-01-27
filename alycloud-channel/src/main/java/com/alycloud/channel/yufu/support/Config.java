package com.alycloud.channel.yufu.support;

/**
 * 御付交易渠道配置参数
 * @author Moyq5
 * @date 2017年8月2日
 */
public interface Config {

	/**
	 * 服务地址
	 * @author Moyq5
	 * @date 2017年8月2日
	 * @return
	 */
	String getServerPath();

	/**
	 * 支付地址
	 * @author Moyq5
	 * @date 2017年8月2日
	 * @return
	 */
	String getPayPath();

	/**
	 * 密钥
	 * @author Moyq5
	 * @date 2017年8月2日
	 * @return
	 */
	String getKey();
	
}
