/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.base;

import java.io.IOException;

/**
 * 序列化接口
 * <pre>
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年11月3日 
 *
 */
public interface ISerializable<T> {

	/**
	 * 转码
	 * @param entity
	 * @return
	 */
	byte[] encode(T datum) throws IOException;
	
	/**
	 * 解析
	 * @param data
	 * @return
	 */
	T decode(byte[] data) throws IOException;
}
