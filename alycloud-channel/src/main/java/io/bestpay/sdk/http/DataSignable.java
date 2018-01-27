/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.http;

import io.bestpay.sdk.base.RequestParams;

/**
 * 
 * <pre>
 * 	数据签名
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年9月29日 
 *
 */
public interface DataSignable<T> {
	/**
	 * 通过该数据生成签名
	 * @param data
	 * @return
	 */
	String sign(RequestParams requestParams, T data);
	
	/**
	 * 验证
	 * @param data
	 * @return
	 */
	boolean verify(RequestParams requestParams, T data, String sign);
}
