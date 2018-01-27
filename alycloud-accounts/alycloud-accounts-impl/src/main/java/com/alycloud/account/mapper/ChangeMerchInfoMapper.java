package com.alycloud.account.mapper;

import java.util.List;

import com.alycloud.modules.entity.ChangeMerchInfo;

/**
 * 商户基本信息变更
 * @author Moyq5
 * @date 2017年9月24日
 */
public interface ChangeMerchInfoMapper {

	/**
	 * 新增商户待审核的信息
	 * @param info
	 * @return
	 */
	int add(ChangeMerchInfo info);
	
	/**
	 * 修改商户待审核的信息
	 * @param info
	 * @return
	 */
	int mod(ChangeMerchInfo info);
	
	ChangeMerchInfo getChangeMerchInfo(ChangeMerchInfo info);
	
	/**
	 * 获取商户信息更改记录   判断是否是第一次申请待审核的操作
	 * @param merchMobile
	 * @param loginName
	 * @return
	 */
	List<ChangeMerchInfo> list(ChangeMerchInfo info4s);
	
	/**
	 * 更新待审核的信息，新增手持身份证
	 * @param info
	 * @return
	 * @author Horanluo
	 */
	int updateToAuditInfo(ChangeMerchInfo cmi);
	
	/**
	 * 查询最新待审核的信息，新增手持身份证
	 * @param merchno
	 * @return
	 */
	ChangeMerchInfo getChangeMerchInfo(String merchno);
}
