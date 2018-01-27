package com.alycloud.account.api;

import com.alycloud.modules.entity.ChangeMerchAccount;

/**
 * 
 * @author Moyq5
 * @date 2017年10月23日
 */
public interface IChangeMerchAccountService {

	/**
	 * 商户添加结算账户变更申请
	 * @author Moyq5
	 * @throws Exception 
	 * @date 2017年10月23日
	 */
	void addByMerchno(String merchno, ChangeMerchAccount account) throws Exception;
}
