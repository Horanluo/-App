package com.alycloud.channel.pingan.support.client;

import com.alycloud.channel.pingan.bean.PayRefundData;
import com.alycloud.channel.pingan.bean.PayRefundResult;
import com.alycloud.channel.pingan.support.AbstractClient;

/**
 * 订单退款接口
 * @author Moyq5
 * @date 2017年7月10日
 */
public class PayRefund extends AbstractClient<PayRefundData, PayRefundResult> {

	@Override
	protected Class<PayRefundResult> getDetailClass() {
		return PayRefundResult.class;
	}

	@Override
	protected String getServerPath() {
		return super.getServerPath() + "payrefund";
	}

}
