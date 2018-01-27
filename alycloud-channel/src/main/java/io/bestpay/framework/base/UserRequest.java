/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.base;

import java.io.Serializable;
import java.util.Map;

/**
 * 用户请求上下文
 * <pre>
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年10月19日 
 *
 */
public interface UserRequest extends Serializable {

	public static final String USER_REQUEST_CONTEXT_KEY = "io.bestpay.rpc.context.USER_REQUEST";
	/**
	 * 获取用户名
	 * @return
	 */
	String getPrincipal();
	
	/**
	 * 是否授权
	 * @return
	 */
	boolean isAuthorized();
	
	/**
	 * 获取参数
	 * @param name
	 * @return
	 */
	Object getParameter(String name);
	
	/**
	 * 获取字符串
	 * @param name
	 * @return
	 */
	String getString(String name);
	
	/**
	 * 获取列表
	 * @param name
	 * @return
	 */
	String[] getStrings(String name);
	
	/**
	 * 获取所有参数
	 * @return
	 */
	Map<String, Object> getParameters();
}
