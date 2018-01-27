package com.alycloud.channel.yufu.merch;

import com.alycloud.channel.yufu.merch.support.AbstractClient;
import com.alycloud.channel.yufu.merch.support.client.MerchApply;
import com.alycloud.channel.yufu.merch.support.client.MerchQuery;

/**
 * 御付商户入驻－接口类型
 * @author Moyq5
 * @date 2017年8月1日
 */
public enum YufuMerchApiType {

	/**
	 * 商户入驻接口：MerchApply
	 */
	MERCH_APPLY(MerchApply.class),
	/**
	 * 商户审核结果查询接口：MerchQuery
	 */
	MERCH_QUERY(MerchQuery.class);
	
	private Class<? extends AbstractClient<?,?>> clientClass;
	
	YufuMerchApiType(Class<? extends AbstractClient<?,?>> clientClass) {
		this.clientClass = clientClass;
	}

	public Class<? extends AbstractClient<?,?>> getClientClass() {
		return clientClass;
	}

}
