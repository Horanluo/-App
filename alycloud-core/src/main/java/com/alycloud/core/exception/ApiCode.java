package com.alycloud.core.exception;

/**
 * 开放接口错误码
 * @author Moyq5
 * @date 2017年9月27日
 */
public enum ApiCode {
	
	C0000("执行成功"),
	C9999("系统繁忙...请稍候再试"),
	C0001("商户号不能为空"),
	C0002("商户不存在"),
	C0003("签名验证失败"),
	C0004("商户未开通此业务或者业务权限不足"),
	C0005("订单号重复"),
	C0006("交易不存在"),
	C0007("费率参数有误"),
	C0008("不支持该支付方式"),
	C0009("找不到可用渠道"),
	C0010("资料正在审核,不能重复提交"),
	C0011("账户余额不足"),
	C0012("当前时间不可提现");
	
	private String text;
	ApiCode(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
