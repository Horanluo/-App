package com.alycloud.financial.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alycloud.modules.entity.AgentTrans;
import com.alycloud.modules.search.AgentTrans4Search;

/**
 * 代理商分润Service
 * @author Moyq5
 * @date 2017年11月4日
 */
public interface IAgentTransService {

	/**
	 * 统计分润金额
	 * @author Moyq5
	 * @date 2017年11月4日
	 * @param trans4s
	 * @return
	 */
	BigDecimal sum(AgentTrans4Search trans4s);

	/**
	 * 按日分润统计列表
	 * @author Moyq5
	 * @date 2017年11月4日
	 * @param trans4s
	 * @return
	 */
	List<AgentTrans> listByGroupByDate(AgentTrans4Search trans4s);

	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年11月4日
	 * @param trans4s
	 * @return
	 */
	List<AgentTrans> listByPage(AgentTrans4Search trans4s);

	/**
	 * 查询记录数
	 * @author Moyq5
	 * @date 2017年11月7日
	 * @param trans4s
	 * @return
	 */
	Integer countRecord(AgentTrans4Search trans4s);

	/**
	 * 升级费分润
	 * @author Moyq5
	 * @date 2017年11月4日
	 * @param data
	 * @throws Exception 
	 */
	void addByGradeOrderId(Integer data) throws Exception;

	/**
	 * 二维码交易分润
	 * @author Moyq5
	 * @throws Exception 
	 * @date 2017年11月6日
	 */
	void addByQrcodeOrderno(String orderno) throws Exception;

	/**
	 * 快捷交易分润
	 * @author Moyq5
	 * @throws Exception 
	 * @date 2017年11月6日
	 */
	void addByFastOrderno(String orderno) throws Exception;

	/**
	 * 分润明细列表
	 * @author Moyq5
	 * @date 2017年11月12日
	 */
	List<Map<String, Object>> listDetail(AgentTrans4Search trans4s);

}
