package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.AgentPayData;
import com.alycloud.channel.linkpay.bean.AgentPayResult;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;

/**
 * 余额代付接口
 * @author Moyq5
 * @date 2017年5月9日
 */
public class AgentPayByBalance extends AbstractClient<AgentPayData, AgentPayResult> {

	public AgentPayByBalance() {
		super(Service.DF002, AgentPayResult.class);
	}

}
