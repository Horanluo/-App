package com.alycloud.account.api;

import java.util.List;
import java.util.Map;

import com.alycloud.modules.entity.GradeFee;
import com.alycloud.modules.search.GradeFee4Search;

/**
 * 商户等级费率
 * @author Moyq5
 * @date 2017年10月29日
 */
public interface IGradeFeeService {

	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月29日
	 * @param fee4s
	 * @return
	 */
	List<GradeFee> list(GradeFee4Search fee4s);

	/**
	 * 按渠道分组查询
	 * @author Moyq5
	 * @date 2017年11月10日
	 * @param fee4s
	 * @return
	 */
	public List<Map<String,Object>> listGroup4Channel(GradeFee4Search fee4s);
	
	/**
	 * 查询商户等级费率，计算提现手续费
	 * @author Horanluo
	 * @date 2017年12月11日
	 * @param fee4s
	 * @return
	 */
	List<GradeFee> queryGradeInfo(String merchno);
}
