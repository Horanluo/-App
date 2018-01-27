package com.alycloud.account.mapper;

import java.util.List;
import java.util.Map;

import com.alycloud.modules.entity.GradeFee;
import com.alycloud.modules.search.GradeFee4Search;

/**
 * 升级规则
 * @author Moyq5
 * @date 2017年10月29日
 */
public interface GradeFeeMapper {

	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月29日
	 * @param fee4s
	 * @return
	 */
	List<GradeFee> list(GradeFee4Search fee4s);
	
	/**
	 * 查询商户等级费率，计算提现手续费
	 * @param merchno
	 * @return
	 */
	List<GradeFee> queryGradeInfo(Map<String,String> params);
}
