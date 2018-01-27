package com.alycloud.channel.yufu.merch.support.client;

import com.alycloud.channel.yufu.merch.bean.MerchQueryData;
import com.alycloud.channel.yufu.merch.bean.MerchQueryResult;
import com.alycloud.channel.yufu.merch.support.AbstractClient;

/**
 * 商户审核结果查询接口
 * @author Moyq5
 * @date 2017年8月1日
 */
public class MerchQuery extends AbstractClient<MerchQueryData, MerchQueryResult> {

	public MerchQuery() {
		super(MerchQueryResult.class);
	}

}
