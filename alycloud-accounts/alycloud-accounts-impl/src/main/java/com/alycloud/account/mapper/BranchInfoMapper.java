package com.alycloud.account.mapper;

import com.alycloud.modules.entity.BranchInfo;


/**
 * 机构
 * @author Moyq5
 * @date 2017年7月17日
 */
public interface BranchInfoMapper {
	
	public BranchInfo getByBranchno(String branchno);
}
