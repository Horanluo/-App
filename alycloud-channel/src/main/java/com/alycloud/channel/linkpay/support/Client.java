package com.alycloud.channel.linkpay.support;

import com.alycloud.channel.linkpay.bean.Result;

public interface Client<D, R> {

	Result<R> post(D data);

}