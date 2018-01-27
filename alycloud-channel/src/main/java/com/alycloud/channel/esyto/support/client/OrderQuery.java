package com.alycloud.channel.esyto.support.client;

import com.alycloud.channel.esyto.bean.OrderQueryData;
import com.alycloud.channel.esyto.bean.OrderQueryResult;
import com.alycloud.channel.esyto.support.AbstractClient;

/**
 * 查询接口
 * @author Moyq5
 * @date 2017年9月30日
 */
public class OrderQuery extends AbstractClient<OrderQueryData, OrderQueryResult> {

	@Override
	protected String getServerPath() {
		return super.getServerPath() + "orderQuery";
	}

	@Override
	protected Class<OrderQueryResult> getResultClass() {
		return OrderQueryResult.class;
	}

}
