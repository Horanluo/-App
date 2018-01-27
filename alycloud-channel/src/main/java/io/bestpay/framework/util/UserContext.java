/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.util;

import io.bestpay.framework.base.User;

public class UserContext {
	static ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<User>();
	
	/**
	 * 获取用户
	 * @return
	 */
	public static User get() {
		return USER_THREAD_LOCAL.get();
	}
	
	/**
	 * 设置用户
	 * @param user
	 */
	public static void set(User user) {
		USER_THREAD_LOCAL.set(user);
	}
}
