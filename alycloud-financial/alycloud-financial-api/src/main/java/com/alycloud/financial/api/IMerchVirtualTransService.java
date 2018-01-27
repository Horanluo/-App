package com.alycloud.financial.api;

import com.alycloud.modules.entity.MerchVirtualTrans;

/**
 * 商户提现流水Service
 * @author Moyq5
 * @date 2017年11月13日
 */
public interface IMerchVirtualTransService {

	/**
	 * 生成流水号
	 * @author Moyq5
	 * @date 2017年11月13日
	 * @return
	 * @throws Exception
	 */
	String genRefno() throws Exception;

	/**
	 * 添加
	 * @author Moyq5
	 * @date 2017年11月13日
	 * @param trans
	 * @return
	 */
	MerchVirtualTrans add(MerchVirtualTrans trans);

}
