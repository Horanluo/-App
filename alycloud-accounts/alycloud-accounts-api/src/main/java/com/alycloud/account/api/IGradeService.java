package com.alycloud.account.api;

import java.math.BigDecimal;
import java.util.List;

import com.alycloud.modules.entity.Grade;

/**
 * 商户等级配置
 * @author Moyq5
 * @date 2017年10月31日
 */
public interface IGradeService {

	/**
	 * 获取不大于且最靠近指定金额的等级信息
	 * @author Moyq5
	 * @date 2017年10月31日
	 * @param maxAmount
	 * @return
	 */
	Grade getMaxByLessThan(BigDecimal maxAmount);

	/**
	 * 获取不大于且最靠近指定推荐人安适的等级信息
	 * @author Moyq5
	 * @date 2017年11月4日
	 * @param maxQuantity
	 * @return
	 */
	Grade getMaxByLessThan(Integer maxQuantity);

	/**
	 * 获取等级信息
	 * @author Moyq5
	 * @date 2017年11月5日
	 * @param data
	 * @return
	 */
	Grade getByGradeType(Integer data);

	/**
	 * 获取等级列表
	 * @author Moyq5
	 * @date 2017年11月10日
	 * @return
	 */
	List<Grade> listAll();

}
