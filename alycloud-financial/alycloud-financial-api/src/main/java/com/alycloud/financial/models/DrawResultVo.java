package com.alycloud.financial.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 代理商提现-响应参数
 * @author Moyq5
 * @date 2017年10月22日
 */
@NoArgsConstructor
@AllArgsConstructor
public class DrawResultVo {

	private String accountName;// 账户名称
	private String mobile;// 账号（手机号）
	private String transAmount;// 提现金额
	private String transFee;// 手续费
	private String payAmount;// 到账金额
	private String accountno;// 到账卡号
	private String transTime;// 提现时间
	public String getAccountName() {
		String a = accountName;
		return a.substring(0, 1) + (a.substring(1).replaceAll("\\S", "*"));
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getMobile() {
		String a = mobile;
		return a.substring(0, 3) + (a.substring(3, a.length() - 4).replaceAll("\\d", "*")) + (a.substring(a.length() - 4));
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}
	public String getTransFee() {
		return transFee;
	}
	public void setTransFee(String transFee) {
		this.transFee = transFee;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getAccountno() {
		String a = accountno;
		return a.substring(0, 4) + (a.substring(4, a.length() - 4).replaceAll("\\d", "*")) + (a.substring(a.length() - 4));
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
}
