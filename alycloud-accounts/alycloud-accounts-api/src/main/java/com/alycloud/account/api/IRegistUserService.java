package com.alycloud.account.api;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.vo.RegistUserVO;

public interface IRegistUserService {

	/**
	 * @param reqData
	 * @param refer 推荐人信息
	 * @return
	 * @throws Exception
	 */
	public boolean RegistUser(RequestBean<RegistUserVO> reqData,MerchUser refer);
}
