package com.alycloud.core.controller;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

	/**
	 * 交易预处理
	 * 
	 * @param request
	 * @param msg
	 * @param respMap
	 * @param param
	 * @throws Exception
	 */
	protected SingleResult<String> pretreatment(RequestBean<Object> reqData,String param[]) throws Exception {
		SingleResult<String> singleResult = new SingleResult<String>();
		return singleResult;
		//String verifyStr = requestParamVerify(param);
//		if (StringUtils.isNotBlank(verifyStr)) {
//			respMap.put("respCode", ResponseEnum.API_ERROE_CODE_0001.getRespCode());
//			respMap.put("respDesc", "参数" + verifyStr + "不能为空");
//			log.info("请求参数:" + verifyStr + "为空");
//			return singleResult;
//		}
	}
}
