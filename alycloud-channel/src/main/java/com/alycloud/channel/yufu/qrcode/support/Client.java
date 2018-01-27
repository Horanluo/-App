package com.alycloud.channel.yufu.qrcode.support;

import com.alycloud.channel.yufu.qrcode.bean.AbstractResult;

/**
 * 接口API操作类
 * @author Moyq5
 * @date 2017年8月28日
 */
public interface Client<D, R extends AbstractResult> {

	R execute(D data);

}