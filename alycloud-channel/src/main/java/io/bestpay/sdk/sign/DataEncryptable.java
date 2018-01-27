/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.sdk.sign;

import io.bestpay.framework.base.CodeException;

/**
 * 数据加解密
 * <pre>
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年11月5日 
 *
 */
public interface DataEncryptable<T> {
	
	/**
	 * 加密
	 * @param specificRecord
	 * @return
	 */
	byte[] encrypt(T data) throws CodeException;
	
	/**
	 * 解密
	 * @param data
	 * @return
	 * @throws CodeException 
	 */
	T decrypt(String data) throws CodeException;
	
	/**
	 * 签名
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String sign(byte[] data) throws Exception;
}
