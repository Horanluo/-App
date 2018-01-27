package com.alycloud.account.mapper;

import com.alycloud.modules.entity.ChannelSubmerchInfo;

public interface ChannelSubMerchInfoHistoryMapper {

	/**
	 * 修改渠道信息之前  更新老记录
	 * @param oldRecord
	 * @return
	 * @author Horanluo
	 */
	int addChannelInfoHistory(ChannelSubmerchInfo oldRecord);
}
