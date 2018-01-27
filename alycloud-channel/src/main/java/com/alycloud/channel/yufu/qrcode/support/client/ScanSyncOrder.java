package com.alycloud.channel.yufu.qrcode.support.client;

import com.alycloud.channel.yufu.qrcode.bean.ScanSyncOrderData;
import com.alycloud.channel.yufu.qrcode.bean.ScanSyncOrderResult;
import com.alycloud.channel.yufu.qrcode.support.AbstractClient;
import com.alycloud.channel.yufu.qrcode.support.Factory;

/**
 * 扫码收款（结果同步返回）接口
 * @author Moyq5
 * @date 2017年8月29日
 */
public class ScanSyncOrder extends AbstractClient<ScanSyncOrderData, ScanSyncOrderResult> {

	@Override
	protected String getServerPath() {
		return Factory.getConfig().getServerPath() + "unScanCodePay20";
	}

	@Override
	protected Class<ScanSyncOrderResult> getResultClass() {
		return ScanSyncOrderResult.class;
	}

}
