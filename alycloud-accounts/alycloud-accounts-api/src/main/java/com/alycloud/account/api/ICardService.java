package com.alycloud.account.api;

import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.vo.SaveCardVO;

public interface ICardService {

	/**
	 * 银行卡四要素验证
	 * 
	 * @author Moyq5
	 * @date 2017年1月13日
	 * @param id
	 *            身份证号
	 * @param name
	 *            姓名
	 * @param card
	 *            银行卡号
	 * @param mobile
	 *            手机号
	 * @return
	 */
//	JSONObject certifyCardMsg(String id, String name, String card, String mobile,
//			String authKey,String authService)throws ServiceException;
	
	/**
	 * 新增待审核商户结算卡信息
	 * @param changeMerchInfoBean
	 * @return
	 */
	SingleResult<String> addChangeMerchAccount(RequestBean<SaveCardVO> reqData)throws ApiException;
}
