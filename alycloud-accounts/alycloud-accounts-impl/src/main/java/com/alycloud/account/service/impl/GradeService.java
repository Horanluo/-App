package com.alycloud.account.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.account.api.IGradeService;
import com.alycloud.account.mapper.GradeMapper;
import com.alycloud.modules.entity.Grade;

/**
 * 商户等级配置Service
 * @author Moyq5
 * @date 2017年10月31日
 */
@Service
public class GradeService implements IGradeService {

	@Autowired
	private GradeMapper gradeMapper;

	@Override
	public Grade getMaxByLessThan(BigDecimal maxAmount) {
		return gradeMapper.getMaxByLessThanAmount(maxAmount);
	}

	@Override
	public Grade getMaxByLessThan(Integer maxQuantity) {
		return gradeMapper.getMaxByLessThanQuantity(maxQuantity);
	}

	@Override
	public Grade getByGradeType(Integer gradeType) {
		return gradeMapper.getByGradeType(gradeType);
	}

	@Override
	public List<Grade> listAll() {
		return gradeMapper.listAll();
	}
	
}
