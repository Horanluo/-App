package com.alycloud.pay.api;

import com.alycloud.modules.entity.SystemParam;

/**
 * 系统配置参数
 * @author Moyq5
 * @date 2017年10月30日
 */
public interface ISystemParamService {

	/**
	 * 获取配置参数
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param code
	 * @return
	 */
	SystemParam getByCode(String code);
}
