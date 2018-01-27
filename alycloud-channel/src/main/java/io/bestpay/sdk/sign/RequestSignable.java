/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import io.bestpay.sdk.base.RequestData;

/**
 * 请求签名
 * <pre>
 *	@param <T>
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年11月5日 
 *
 */
public interface RequestSignable {

	RequestData sign(RequestData requestData);
}
