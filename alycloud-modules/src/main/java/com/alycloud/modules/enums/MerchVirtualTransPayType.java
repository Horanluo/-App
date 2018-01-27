package com.alycloud.modules.enums;

/**
 * 商户虚拟账户提现操作来源
 * 
 * @author Moyq5
 * @date 2017年3月17日
 */
public enum MerchVirtualTransPayType {

	/**
	 * 手机APP
	 */
	APP("手机APP"),
	/**
	 * 接口
	 */
	API("接口"),
	/**
	 * 后台
	 */
	SYS("后台");

	private String text;

	MerchVirtualTransPayType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
