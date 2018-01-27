package com.alycloud.account.api;

import java.math.BigDecimal;
import java.util.List;

import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.MerchInfo;

public interface IChannelSubMerchInfoService {
    
	/**
	 * 查询商户渠道费率，结算，单笔限额
	 * @param merchno  商户号
	 * @param payType  支付方式
	 * @return  商户费率列表
	 */
	List<ChannelSubmerchInfo> getChannelMerchInfoList(String merchno,String payType);
	
	/**
	 * 更新商户费率
	 * @param merchno 商户号
	 * @param payType 支付类型
	 * @param scanType 支付方式
	 * @param settleType 费率类型
	 * @param payRate 费率值
	 * @return 
	 */
	SingleResult<String> modifyMerchFee(String merchno, BigDecimal modifyRate,String channelCode)throws ApiException;
	
	/**
	 * 上报商户费率
	 * @param merchno 商户号
	 * @param channelCode 渠道code
	 * @return
	 * @author Administrator 罗根恒
	 */
	SingleResult<String> reportMerchFee(String merchno,MerchInfo merch4Mod,String channelCode)throws Exception;
	
	/**
	 * 查询商户渠道信息
	 * @param csmi  商户号
	 * @return  商户费率列表
	 */
	List<ChannelSubmerchInfo> getChannelMerchInfoList(ChannelSubmerchInfo csmi);
	
	/**
	 * 更新记录
	 * @param csmi
	 * @return
	 */
	int updateRecord(ChannelSubmerchInfo csmi);
}