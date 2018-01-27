package com.alycloud.account.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.alycloud.modules.entity.Grade;

/**
 * 商户等级
 * @author Moyq5
 * @date 2017年10月31日
 */
public interface GradeMapper {

	/**
	 * 获取不大于且最靠近指定金额的等级信息
	 * @author Moyq5
	 * @date 2017年10月31日
	 * @param maxAmount
	 * @return
	 */
	Grade getMaxByLessThanAmount(BigDecimal maxAmount);
	
	/**
	 * 获取不大于且最靠近指定推荐人数的等级信息
	 * @author Moyq5
	 * @date 2017年11月4日
	 * @param maxQuantity
	 * @return
	 */
	Grade getMaxByLessThanQuantity(Integer maxQuantity);

	/**
	 * 获取等级信息
	 * @author Moyq5
	 * @date 2017年11月5日
	 * @param gradeType
	 * @return
	 */
	Grade getByGradeType(Integer gradeType);

	/**
	 * 获取等级列表
	 * @author Moyq5
	 * @date 2017年11月10日
	 * @return
	 */
	List<Grade> listAll();

}
