package com.alycloud.channel.yufu.qrcode.support.client;

import com.alycloud.channel.yufu.qrcode.bean.ScanAsyncOrderData;
import com.alycloud.channel.yufu.qrcode.bean.ScanAsyncOrderResult;
import com.alycloud.channel.yufu.qrcode.support.AbstractClient;
import com.alycloud.channel.yufu.qrcode.support.Factory;

/**
 * 扫码收款（结果异步返回）接口
 * @author Moyq5
 * @date 2017年8月29日
 */
public class ScanAsyncOrder extends AbstractClient<ScanAsyncOrderData, ScanAsyncOrderResult> {

	@Override
	protected String getServerPath() {
		return Factory.getConfig().getServerPath() + "unScanCodePay21";
	}

	@Override
	protected Class<ScanAsyncOrderResult> getResultClass() {
		return ScanAsyncOrderResult.class;
	}

}
