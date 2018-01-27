package com.alycloud.financial.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代理商分润概览-响应参数
 * @author Moyq5
 * @date 2017年10月22日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentTransOverviewResult {

	private String todayAmount;// 今日收益(元)
	private String yesterdayAmount;// 昨日收益(元)
	private String drawAmount;// 可提现分润(元)
	private String totalAmount;// 累计分润(元)
}
