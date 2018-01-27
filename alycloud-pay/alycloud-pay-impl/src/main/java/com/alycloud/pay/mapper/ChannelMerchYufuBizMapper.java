package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.ChannelMerchYufuBiz;


/**
 * 御付渠道商户支付业务配置信息
 * @author Moyq5
 * @date 2017年8月5日
 */
public interface ChannelMerchYufuBizMapper {

	
	public int batchAdd(List<ChannelMerchYufuBiz> list);

}
