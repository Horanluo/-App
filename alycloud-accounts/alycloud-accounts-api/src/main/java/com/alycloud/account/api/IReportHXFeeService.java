package com.alycloud.account.api;

import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.MerchInfo;

public interface IReportHXFeeService {

	/**
	 * 申报汇享渠道商户费率
	 * @param merchno
	 * @param channelCode
	 * @return
	 * @throws Exception
	 */
	public SingleResult<String> reportHXFee(String merchno, MerchInfo merch4Mod,String channelCode) throws Exception;
	
	/**
	 * 修改汇享商户费率
	 * @param merchInfo
	 * @param channelCode
	 * @return
	 * @throws Exception
	 */
	public SingleResult<String> modifyHXFee(MerchInfo merchInfo, String channelCode,ChannelSubmerchInfo newRecord)throws Exception;
}
