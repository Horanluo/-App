package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.search.FastOrder4Search;

/**
 * 快捷支付订单
 * @author Moyq5
 * @date 2017年4月20日
 */
public interface FastOrderMapper {

	public int add(FastOrder order);

	public int mod(FastOrder param);

	public List<FastOrder> listByPage(FastOrder4Search order4s);
	
	public int del(FastOrder order);
}
