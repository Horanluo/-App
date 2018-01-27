package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.AgentPayData;
import com.alycloud.channel.linkpay.bean.AgentPayResult;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;

/**
 * 垫资代付接口
 * @author Moyq5
 * @date 2017年5月9日
 */
public class AgentPayByFund extends AbstractClient<AgentPayData, AgentPayResult> {

	public AgentPayByFund() {
		super(Service.DF001, AgentPayResult.class);
	}

}
