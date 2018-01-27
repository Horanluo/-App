package com.alycloud.channel.yufu.qrcode;

import com.alycloud.channel.yufu.qrcode.support.Client;
import com.alycloud.channel.yufu.qrcode.support.client.NotifyCallback;
import com.alycloud.channel.yufu.qrcode.support.client.OfficalOrder;
import com.alycloud.channel.yufu.qrcode.support.client.OrderQuery;
import com.alycloud.channel.yufu.qrcode.support.client.Refund;
import com.alycloud.channel.yufu.qrcode.support.client.ScanAsyncOrder;
import com.alycloud.channel.yufu.qrcode.support.client.ScanSyncOrder;
import com.alycloud.channel.yufu.qrcode.support.client.UnifiedOrder;
import com.alycloud.channel.yufu.qrcode.support.client.WeixinAppOrder;

/**
 * 御付支付－接口类型
 * @author Moyq5
 * @date 2017年8月2日
 */
public enum YufuQrcodeApiType {
	/**
	 * 预统一下单接口：UnifiedOrder
	 */
	UNIFIED_ORDER(UnifiedOrder.class),
	/**
	 * 扫码收款（结果同步返回）接口：ScanSyncOrder
	 */
	SCAN_SYNC_ORDER(ScanSyncOrder.class),
	/**
	 * 扫码收款（结果异步返回）接口：ScanAsyncOrder
	 */
	SCAN_ASYNC_ORDER(ScanAsyncOrder.class),
	/**
	 * 微信公众号支付、小程序支付：OfficalOrder
	 */
	OFFICAL_ORDER(OfficalOrder.class),
	/**
	 * 查询接口：OrderQuery
	 */
	ORDER_QUERY(OrderQuery.class),
	/**
	 * 退款接口：Refund
	 */
	REFUND(Refund.class),
	/**
	 * 异步通知处理：NotifyCallback
	 */
	NOTIFY_CALLBACK(NotifyCallback.class),
	/**
	 * 微信APP支付接口：WeixinAppOrder
	 */
	WEIXIN_APP_ORDER(WeixinAppOrder.class);
	
	private Class<? extends Client<?,?>> clientClass;
	
	YufuQrcodeApiType(Class<? extends Client<?,?>> clientClass) {
		this.clientClass = clientClass;
	}

	public Class<? extends Client<?,?>> getClientClass() {
		return clientClass;
	}

}
