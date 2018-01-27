package com.alycloud.financial.mapper;

import com.alycloud.modules.entity.MerchVirtualRecharge;

/**
 * 商户虚拟账户充值记录的持久层
 * @author Moyq5
 * @date 2017年3月15日
 */
public interface MerchVirtualRechargeMapper {

	public int insert(MerchVirtualRecharge trans);

	public MerchVirtualRecharge getByRefno(String refno);
	
}
