package com.alycloud.pay.support.channel.api.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alycloud.channel.payeco.Config;
import com.alycloud.channel.payeco.PayecoFactory;
import com.alycloud.channel.payeco.PayecoType;
import com.alycloud.channel.payeco.bean.QueryOrderData;
import com.alycloud.channel.payeco.bean.QueryOrderResult;
import com.alycloud.channel.payeco.enums.OrderStatus;
import com.alycloud.channel.payeco.support.client.QueryOrderClient;
import com.alycloud.pay.support.channel.api.ChannelApiAdapter;
import com.alycloud.pay.support.channel.api.ChannelOrderStatus;
import com.alycloud.pay.support.channel.api.ChannelOrderStatusResult;

/**
 * 易联渠道接口
 * @author Moyq5
 * @date 2017年8月2日
 */
public class PayecoApi extends ChannelApiAdapter {

	private static final Logger log = LoggerFactory.getLogger(PayecoApi.class);
	private String customOrderNo;
	public PayecoApi(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}
	@Override
	public ChannelOrderStatusResult query() {
		ChannelOrderStatusResult result = new ChannelOrderStatusResult();
		Config cfg = PayecoFactory.getConfig();
		QueryOrderClient client = PayecoFactory.getClient(PayecoType.QUERY_ORDER);
		QueryOrderData data = new QueryOrderData();
		data.setMerchantId(cfg.getMerchantId());
		data.setMerchOrderId(customOrderNo);
		data.setTradeTime(new Date());
		QueryOrderResult apiResult = client.request(data);
		if (apiResult.getHead().getRetCode().equals("0000")) {
			OrderStatus status = apiResult.getBody().getStatus();
			log.info("易联订单[{}]付款状态：[{}]{}", 
					new Object[]{customOrderNo, status.getValue(), status.getText()});
			
			result.setChannelOrderNo(apiResult.getBody().getOrderId());
    		
			if (status == OrderStatus.PAID) {
				result.setStatus(ChannelOrderStatus.SUCCESS);
			} else {
				result.setStatus(ChannelOrderStatus.FAIL);
				result.setMsg(apiResult.getHead().getRetMsg());
			}
		} else {
			result.setStatus(ChannelOrderStatus.ERROR);
			result.setMsg(apiResult.getHead().getRetMsg());
		}
		return result;
	}
	
}
