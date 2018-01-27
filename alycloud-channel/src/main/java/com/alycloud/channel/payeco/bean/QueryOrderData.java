package com.alycloud.channel.payeco.bean;

import java.util.Date;

/**
 * 易联支付－订单查询接口－请求参数
 * @author Moyq5
 * @date 2017年4月11日
 */
public class QueryOrderData extends AbstractData {

	/**
	 * 查询提交时间，必填<br>
	 * 格式：yyyymmddhhmmss
	 */
	private Date tradeTime;
	
	@Override
	public String getTradeCode() {
		return "QueryOrder";
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Override
	public String toString() {
		return "QueryOrderData [tradeTime=" + tradeTime + ", toString()=" + super.toString() + "]";
	}
	
}
