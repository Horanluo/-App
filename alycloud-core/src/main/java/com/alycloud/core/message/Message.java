package com.alycloud.core.message;

import com.alycloud.core.modules.SingleResult;

public interface Message {
	
	/**
	 * 发送普通验证码
	 */
	public static final int TYPE_VALID_CODE = 0;
	
	/**
	 * 用户注册时，发送验证码
	 */
	public static final int TYPE_VALID_CODE_REGISTER = 1;
	
	/**
	 * 重置密码时，发送验证码
	 */
	public static final int TYPE_VALID_CODE_RESET_PWD = 2;
	
	/**
	 * 用户注册成功后，发送密码通知
	 */
	public static final int TYPE_NOTIFY_PWD = 3;

	/**
	 * 四要素交易鉴权时，发送验证码
	 */
	public static final int TYPE_VALID_CODE_TRANS = 4;
	
	/**
	 * 初审拒绝时，发送验证码
	 */
	public static final int TYPE_AUDIT_REFUSE = 5;
	/**
	 * 终审拒绝时，发送验证码
	 */
	public static final int TYPE_JUDG_REFUSE = 6;
	/**
	 * 终审通过时，发送验证码
	 */
	public static final int TYPE_JUDG_OK = 7;
	/**
	 * 提现验证码
	 */
	public static final int TYPE_IN_CASE = 8;
	
	/**
	 * 根据模板和参数发送短信内容
	 * @param mobile
	 * @param templateId
	 * @param param
	 * @return
	 */
	public SingleResult<String> send(String mobile, int msgType, String param);

}
