package com.alycloud.channel.support.config;

/**
 * 【易商付】支付渠道（商户版）配置
 * @author Moyq5
 * @date 2017年10月8日
 */
public class EsytoConfig implements com.alycloud.channel.esyto.support.Config {

	@Override
	public String getServerPath() {
		return "http://localhost:8080/posp-api/";
	}

	@Override
	public String getKey() {
		return "918A9EDBECCF2B74AAD63A29928E86A2";
	}

	@Override
	public String getMerchNo() {
		return "200541100000004";
	}
}
