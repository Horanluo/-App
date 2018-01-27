package com.alycloud.modules.search;

import java.math.BigDecimal;

/**
 * 代理商升级费率价格项目
 * @author Moyq5
 * @date 2017年7月14日
 */
public class AgentUpgradeRuleDetailItem {

	private BigDecimal amount;// 费用
	private AgentRateData rateData;// 费率详情
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public AgentRateData getRateData() {
		return rateData;
	}
	public void setRateData(AgentRateData rateData) {
		this.rateData = rateData;
	}
}
