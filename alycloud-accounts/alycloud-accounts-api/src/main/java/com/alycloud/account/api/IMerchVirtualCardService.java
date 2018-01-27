package com.alycloud.account.api;

import com.alycloud.modules.entity.MerchVirtualCard;

/**
 * 商户虚拟账户Service
 * @author Horanluo
 * @date 2017年11月20日
 */
public interface IMerchVirtualCardService {

	/**
	 * 创建虚拟账户
	 * @author Horanluo
	 * @date 2017年11月20日
	 * @param merchno
	 * @return
	 * @throws Exception 
	 */
	MerchVirtualCard add4Merch(String merchno) throws Exception;

}
