package com.alycloud.pay.mapper;

import com.alycloud.modules.entity.SystemParam;


/**
 * 系统参数
 * @author Moyq5
 * @date 2017年10月30日
 */
public interface SystemParamMapper {
	
	public SystemParam getByCode(String code);
}
