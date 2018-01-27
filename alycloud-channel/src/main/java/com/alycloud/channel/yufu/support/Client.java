package com.alycloud.channel.yufu.support;

import com.alycloud.channel.yufu.bean.AbstractResult;

/**
 * 接口API操作类
 * @author Moyq5
 * @date 2017年8月2日
 */
public interface Client<D, R extends AbstractResult> {

	R execute(D data);

}