package com.alycloud.modules.enums;

/**
 * 系统参数编码
 * 
 * @author Moyq5
 * @date 2017年7月17日
 */
public enum SystemParamCode {

	/**
	 * 商户升级费用分成比例
	 */
	MERCH_UPGRADE_DIVIDE("商户升级费用分成比例"),
	/**
	 * 系统收款商户号
	 */
	SYSTEM_PAYEE_MERCHNO("系统收款商户号");

	private String text;

	SystemParamCode(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
