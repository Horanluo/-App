package com.alycloud.channel.linkpay.support.client;

import com.alycloud.channel.linkpay.bean.PayGatewayData;
import com.alycloud.channel.linkpay.bean.PayResult;
import com.alycloud.channel.linkpay.enums.Service;
import com.alycloud.channel.linkpay.support.AbstractClient;

/**
 * 网关支付接口（H5）
 * @author Moyq5
 * @date 2017年6月7日
 */
public class PayGatewayH5 extends AbstractClient<PayGatewayData, PayResult> {

	public PayGatewayH5() {
		super(Service.WGZF006, PayResult.class);
	}

}
