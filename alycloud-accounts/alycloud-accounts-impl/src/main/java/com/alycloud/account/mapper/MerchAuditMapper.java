package com.alycloud.account.mapper;

import com.alycloud.modules.entity.MerchAudit;

public interface MerchAuditMapper {

	/**
	 * 新增初审记录
	 * @param record
	 * @return
	 */
    int insert(MerchAudit record);
}