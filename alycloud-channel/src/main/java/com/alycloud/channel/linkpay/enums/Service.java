package com.alycloud.channel.linkpay.enums;

/**
 * 江苏电子支付接口业务代码
 * 
 * @author Moyq5
 * @date 2017年4月10日
 */
public enum Service {

	/**
	 * 商户入驻
	 */
	SMZF001("商户入驻"),
	/**
	 * 商户查询
	 */
	SMZF002("商户查询"),
	/**
	 * 商户修改
	 */
	SMZF003("商户修改"),
	/**
	 * 微信支付
	 */
	SMZF004("微信支付"),
	/**
	 * 支付宝支付
	 */
	SMZF005("支付宝支付"),
	/**
	 * 支付结果查询
	 */
	SMZF006("支付结果查询"),
	/**
	 * 支付结果异步通知
	 */
	SMZF007("支付结果异步通知"),
	/**
	 * 额度代付
	 */
	SMZF008("额度代付"),
	/**
	 * 普通代付
	 */
	SMZF009("普通代付"),
	/**
	 * 代付结果查询
	 */
	SMZF0010("代付结果查询"),
	/**
	 * 微信公众号支付模式一
	 */
	SMZF014("微信公众号支付模式一"),
	/**
	 * 微信公众号支付模式二
	 */
	SMZF015("微信公众号支付模式二"),
	/**
	 * QQ支付
	 */
	SMZF016("QQ支付"), 
	/**
	 * 额度代付
	 */
	DF001("额度代付"),
	/**
	 * 普通代付
	 */
	DF002("普通代付"),
	/**
	 * 代付结果查询
	 */
	DF003("代付结果查询"), 
	/**
	 * "网关支付－H5"
	 */
	WGZF006("网关支付－H5"), 
	/**
	 * 网关支付－借记卡
	 */
	WGZF001("网关支付－借记卡"), 
	/**
	 * 网关支付－贷记卡
	 */
	WGZF002("网关支付－贷记卡"), 
	/**
	 * 网关支付－借贷综合
	 */
	WGZF003("网关支付－借贷综合"), 
	/**
	 * 支付通知
	 */
	ZFTZ001("支付通知");
	
	private String text;
	
	Service(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
