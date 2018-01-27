package com.alycloud.channel.pingan.support;

import com.alycloud.channel.pingan.bean.Result;

/**
 * 接口API操作类
 * @author Moyq5
 * @date 2017年6月15日
 * @param <D> 请求参数data对象
 * @param <R> 响应参数data对象
 */
public interface Client<D, R> {

	void setMerch(Merch merch);
	Result<R> post(D data);

}