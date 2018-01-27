package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.AgentPayResult;
import com.alycloud.channel.linkpay.bean.QueryData;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;
import com.alycloud.channel.linkpay.support.Config;

/**
 * 代付结果查询接口
 * @author Moyq5
 * @date 2017年5月9日
 */
public class AgentPayQuery extends AbstractClient<QueryData, AgentPayResult> {

	public AgentPayQuery() {
		super(Service.DF003, AgentPayResult.class);
	}

	@Override
	protected String getServerPath() {
		Config cfg = getContext().getConfig();
		return cfg.getQueryPath();
	}

}
