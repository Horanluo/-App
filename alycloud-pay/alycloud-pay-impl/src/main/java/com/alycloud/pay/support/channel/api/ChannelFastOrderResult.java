package com.alycloud.pay.support.channel.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 快捷支付网关接口结果
 * @author Moyq5
 * @date 2017年10月19日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelFastOrderResult {

	private String channelOrderNo;// 渠道订单号
	private String tradeOrderNo;// 机构订单号（渠道上游订单号）
	private String payHtml;// 渠道支付HTML页面内容
	private String errorMes;//渠道返回error信息
	private String errorCode;//渠道返回errorCode;
	private boolean isSuccess;//请求渠道页面成功标识
}
