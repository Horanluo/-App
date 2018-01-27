package com.alycloud.account.mapper;

import com.alycloud.modules.entity.SystemBank;

public interface SystemBankMapper {

	/**
	 * 查询银行信息
	 * @param sb  Horanluo
	 * @return
	 */
	SystemBank getSystemBankInfo(SystemBank sb);
}
