package com.alycloud.channel.utils.yufu;

import com.alycloud.channel.yufu.merch.YufuMerchApiFactory;
import com.alycloud.channel.yufu.merch.YufuMerchApiType;
import com.alycloud.channel.yufu.merch.bean.MerchApplyData;
import com.alycloud.channel.yufu.merch.bean.MerchApplyResult;
import com.alycloud.channel.yufu.merch.bean.MerchQueryData;
import com.alycloud.channel.yufu.merch.bean.MerchQueryResult;
import com.alycloud.channel.yufu.merch.support.client.MerchApply;
import com.alycloud.channel.yufu.merch.support.client.MerchQuery;

/**
 * 调御付通道工具类
 * @author Administrator 罗根恒
 *
 */
public class CallYufuUtil {

	/**
	 * 更新商户费率
	 * @param merchInfo
	 * @return
	 * @throws Exception 
	 */
	public static MerchApplyResult recordMerchFee(MerchApplyData data) throws Exception{
		MerchApply client = (MerchApply) YufuMerchApiFactory.getClient(YufuMerchApiType.MERCH_APPLY);
		MerchApplyResult result = client.post(data);
		return result;
	}
	
	/**
	 * 查询审核状态
	 * @param merchInfo
	 * @return
	 * @throws Exception 
	 */
	public static MerchQueryResult queryAuditStatus(MerchQueryData data) throws Exception{
		MerchQuery client = (MerchQuery)YufuMerchApiFactory.getClient(YufuMerchApiType.MERCH_QUERY);
		MerchQueryResult result = client.post(data);
		return result;
	}
}
