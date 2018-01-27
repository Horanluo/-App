package com.alycloud.account.mapper;

import com.alycloud.modules.entity.OfficalInfo;

/**
 * 公众号
 * @author Moyq5
 * @date 2017年2月10日
 */
public interface OfficalInfoMapper {

	public OfficalInfo getByBranchno(String branchno);

	public int mod(OfficalInfo officalInfo);

}
