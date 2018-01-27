package com.alycloud.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.modules.entity.SystemParam;
import com.alycloud.pay.api.ISystemParamService;
import com.alycloud.pay.mapper.SystemParamMapper;

/**
 * 系统参数
 * @author Moyq5
 * @date 2017年10月30日
 */
@Service
public class SystemParamService implements ISystemParamService {

	@Autowired
	public SystemParamMapper systemParamMapper;

	@Override
	public SystemParam getByCode(String code) {
		return systemParamMapper.getByCode(code);
	}

}
