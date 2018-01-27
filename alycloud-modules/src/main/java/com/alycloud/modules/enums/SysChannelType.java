package com.alycloud.modules.enums;

/**
 * 支付渠道类型，注意枚举顺序不可变
 * @author Moyq5
 * @date 2017年4月20日
 */
public enum SysChannelType {

	/**
	 * 江苏电子
	 */
	JSDZ("JSDZ","江苏电子"),
	/**
	 * 易联支付
	 */
	PAYECO("PAYECO","易联支付"),
	/**
	 * 江苏电子2.0（领贝）
	 */
	LINKPAY("LINKPAY","江苏电子2.0"),
	/**
	 * 御付（快捷）
	 */
	YUFU("YUFU","银联快捷大额A"),
	/**
     * 汇享天成
     */
    HXTC("HXTC","银联快捷大额B"),
	/**
     * 平安
     */
    PINGAN("PINGAN","平安"),
	/**
     * 御付（二维码）
     */
    YUFU_QRCODE("YUFU_QRCODE","御付（二维码）"),
	
	/**
     * 汇享天成
     */
    HXTC_JF("HXTC_JF","银联快捷大额C（带积分）"),
	
	/**
     * 扫呗-扫码
     */
    SAO_BEI("SAO_BEI","扫呗二维码");
	
	private String text;
	private String code;

	SysChannelType(String code, String text) {
		this.code = code;
		this.text = text;
		
	}

	public String getText() {
		return text;
	}

	public String getCode() {
		return code;
	}
	
	public static SysChannelType getByCode(String code) {
		SysChannelType channelType = null;
		for (SysChannelType item: SysChannelType.values()) {
			if (item.getCode().equals(code)) {
				channelType = item;
				break;
			}
		}
		return channelType;
	}
	
}
