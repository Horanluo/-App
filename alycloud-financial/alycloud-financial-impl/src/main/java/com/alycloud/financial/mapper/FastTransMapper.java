package com.alycloud.financial.mapper;

import java.util.List;

import com.alycloud.modules.entity.FastTrans;
import com.alycloud.modules.search.FastTrans4Search;

/**
 * 快捷支付交易流水（已付款订单）
 * @author Moyq5
 * @date 2017年4月20日
 */
public interface FastTransMapper {

	public int add(FastTrans param);

	public int mod(FastTrans param);

	public List<FastTrans> listByPage(FastTrans4Search trans4s);

}
