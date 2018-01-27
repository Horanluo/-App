package com.alycloud.channel.yufu.qrcode.support.client;

import com.alycloud.channel.yufu.qrcode.bean.RefundData;
import com.alycloud.channel.yufu.qrcode.bean.RefundResult;
import com.alycloud.channel.yufu.qrcode.support.AbstractClient;
import com.alycloud.channel.yufu.qrcode.support.Factory;

/**
 * 退款接口
 * @author Moyq5
 * @date 2017年8月30日
 */
public class Refund extends AbstractClient<RefundData, RefundResult> {

	@Override
	protected String getServerPath() {
		return Factory.getConfig().getServerPath() + "refund20";
	}

	@Override
	protected Class<RefundResult> getResultClass() {
		return RefundResult.class;
	}

}
