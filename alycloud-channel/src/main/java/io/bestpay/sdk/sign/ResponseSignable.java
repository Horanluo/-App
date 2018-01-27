/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import io.bestpay.framework.base.SpecificRecord;
import io.bestpay.sdk.base.ResponseData;

/**
 * 返回签名
 * <pre>
 *	@param <T>
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年11月5日 
 *
 */
public interface ResponseSignable<T extends SpecificRecord> {
	
	/**
	 * 返回验签
	 * @param responseData
	 * @return
	 */
	ResponseData sign(ResponseData responseData);
}
