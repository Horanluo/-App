package com.alycloud.account.mapper;

import com.alycloud.modules.entity.MerchJudg;

public interface MerchJudgMapper {

	/**
	 * 新增终审记录
	 * @param record
	 * @return
	 */
    int insert(MerchJudg record);
}