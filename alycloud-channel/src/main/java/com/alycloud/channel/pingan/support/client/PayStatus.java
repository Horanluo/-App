package com.alycloud.channel.pingan.support.client;

import com.alycloud.channel.pingan.bean.PayStatusData;
import com.alycloud.channel.pingan.bean.PayStatusResult;
import com.alycloud.channel.pingan.support.AbstractClient;

/**
 * 查询付款状态接口
 * @author Moyq5
 * @date 2017年6月19日
 */
public class PayStatus extends AbstractClient<PayStatusData, PayStatusResult> {

	@Override
	protected Class<PayStatusResult> getDetailClass() {
		return PayStatusResult.class;
	}

	@Override
	protected String getServerPath() {
		return super.getServerPath() + "paystatus";
	}

}
