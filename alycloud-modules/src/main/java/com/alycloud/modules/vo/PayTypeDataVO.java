package com.alycloud.modules.vo;

import com.alycloud.modules.enums.QrcodePayType;

/**
 * 获取支付渠道-请求参数
 * @author Moyq5
 * @date 2017年10月20日
 */
public class PayTypeDataVO {

	/**
	 * @see {@link QrcodePayType}
	 */
	private Integer payType;// 支付方式
	private String amount; // 交易金额(元)
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
