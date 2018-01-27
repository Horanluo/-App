package com.alycloud.channel.yufu.support.client;

import com.alycloud.channel.yufu.bean.OrderQueryData;
import com.alycloud.channel.yufu.bean.OrderQueryResult;
import com.alycloud.channel.yufu.support.AbstractClient;

/**
 * 订单查询接口
 * @author Moyq5
 * @date 2017年8月1日
 */
public class OrderQuery extends AbstractClient<OrderQueryData, OrderQueryResult> {

	public OrderQuery() {
		super("orderQuery", OrderQueryResult.class);
	}

}
