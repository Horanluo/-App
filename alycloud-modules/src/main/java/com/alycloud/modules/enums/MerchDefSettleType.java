package com.alycloud.modules.enums;

/**
 * 商户二维码默认结算方式
 * 注意，枚举顺序不可变动
 * @author Moyq5
 * @date 2017年3月31日
 */
public enum MerchDefSettleType {

	/**
	 * 实时到账
	 */
	T0("实时到账"),
	/**
	 * 虚拟账户充值
	 */
	T1("虚拟账户充值");
	
	private String text;

	MerchDefSettleType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
