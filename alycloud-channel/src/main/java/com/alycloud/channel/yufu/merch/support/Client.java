package com.alycloud.channel.yufu.merch.support;

import com.alycloud.channel.yufu.merch.bean.ResultAbstract;

/**
 * 接口API操作类
 * @author Moyq5
 * @date 2017年8月1日
 * @param <D> 请求参数对象
 * @param <R> 响应参数对象
 */
public interface Client<D, R extends ResultAbstract> {

	R post(D data);

}