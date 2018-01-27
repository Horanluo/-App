package com.alycloud.modules.enums;

/**
 * 注册来源，枚举顺序不可变
 * @author Moyq5
 * @date 2017年9月24日
 */
public enum SysRegisterType {

	/**
	 * 管理员添加
	 */
	ADMIN_CREATE("管理员添加"),
	/**
	 * 代理商推广注册商户
	 */
	AGENT_PROMOTE("代理商推广"),
	/**
	 * 商户分享注册商户
	 */
	MERCH_SHARE("商户分享"),
	/**
	 * 固定码绑定注册
	 */
	QRCODE_BIND("固定码绑定");
	
	private String text;

	SysRegisterType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
