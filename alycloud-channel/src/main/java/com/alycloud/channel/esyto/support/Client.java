package com.alycloud.channel.esyto.support;

import com.alycloud.channel.esyto.bean.Result;

/**
 * 接口API操作类
 * @author Moyq5
 * @date 2017年9月29日
 */
public interface Client<D, R> {

	Result<R> execute(D data);

}