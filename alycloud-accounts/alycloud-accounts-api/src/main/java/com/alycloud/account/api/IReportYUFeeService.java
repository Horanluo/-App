package com.alycloud.account.api;

import java.math.BigDecimal;

import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.MerchInfo;

public interface IReportYUFeeService {

	/**
	 * 申报商户费率
	 * @param merchno
	 * @param channelCode
	 * @return
	 * @throws Exception
	 */
	public SingleResult<String> reportYUFee(String merchno,MerchInfo merch4Mod,String channelCode) throws Exception;
	
	/**
	 * 修改商户费率
	 * @param merchInfo
	 * @param channelCode
	 * @return
	 * @throws Exception
	 */
	public SingleResult<String> modifyYUFee(MerchInfo merchInfo, String channelCode,ChannelSubmerchInfo newRecord,BigDecimal modifyRate)throws Exception;
}
