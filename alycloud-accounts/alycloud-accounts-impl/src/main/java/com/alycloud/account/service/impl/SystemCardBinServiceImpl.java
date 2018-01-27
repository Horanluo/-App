package com.alycloud.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.account.api.ISystemCardBin;
import com.alycloud.account.mapper.SystemCardBinMapper;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.modules.entity.SystemCardBin;

@Service
public class SystemCardBinServiceImpl implements ISystemCardBin{

	@Autowired
	private SystemCardBinMapper cardBinMapper;
	
	@Override
	@ServiceLogAnnotation(moduleName="查询卡bin信息")
	public List<SystemCardBin> getCardBin(String cardBin) {
		return cardBinMapper.getCardBinInfo(cardBin);
	}
}
