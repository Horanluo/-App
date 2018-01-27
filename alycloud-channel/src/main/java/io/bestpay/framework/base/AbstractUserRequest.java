/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 抽像的用户请求
 * <pre>
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年10月19日 
 *
 */
public abstract class AbstractUserRequest implements UserRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean authorized;
	private String principal;
	private Map<String, Object> parameters = new HashMap<String, Object>();
	private Map<Object, Object> userContext = new HashMap<Object, Object>();
	public AbstractUserRequest(boolean authorized, String principal, Map<String, Object> parameters) {
		super();
		this.authorized = authorized;
		this.principal = principal;
		if (null != parameters) {
			this.parameters.putAll(parameters);
		}
	}
	public boolean isAuthorized() {
		return authorized;
	}
	public String getPrincipal() {
		return principal;
	}
	@Override
	public Object getParameter(String name) {
		return this.parameters.get(name);
	}
	@Override
	public String getString(String name) {
		Object value = this.getParameter(name);
		if (null == value) {
			return null;
		}
		return String.valueOf(value);
	}
	@Override
	public String[] getStrings(String name) {
		String value = this.getString(name);
		if (null != value) {
			return value.split(",");
		}
		return null;
	}
	
	/**
	 * 获取值
	 * @param key
	 * @return
	 */
	public Object get(Object key) {
		return this.userContext.get(key);
	}
	
	/**
	 * 设置值
	 * @param key
	 * @param value
	 * @return
	 */
	public AbstractUserRequest set(Object key, Object value) {
		this.userContext.put(key, value);
		return this;
	}
	
	/**
	 * 清空上下文信息
	 * @return
	 */
	public AbstractUserRequest clear() {
		this.userContext.clear();
		return this;
	}
	
	@Override
	public Map<String, Object> getParameters() {
		return Collections.unmodifiableMap(this.parameters);
	}
	public Map<Object, Object> getUserContext() {
		return userContext;
	}
	public void setUserContext(Map<Object, Object> userContext) {
		this.userContext = userContext;
	}
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public void setParameters(Map<String, Object> parameters) {
		this.addParameters(parameters);
	}
	
	public void addParameters(Map<String, Object> parameters) {
		if (null == parameters || parameters.isEmpty()) {
			return;
		}
		this.parameters.putAll(parameters);
	}
	
	public AbstractUserRequest() {
		super();
	}
}
