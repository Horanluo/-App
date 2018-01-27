package com.alycloud.financial.mapper;

import com.alycloud.modules.entity.AgentVirtualRecharge;
import com.alycloud.modules.search.AgentVirtualRecharge4Search;

/**
 * 代理商虚拟账户充值流水
 * @author Moyq5
 * @date 2017年11月5日
 */
public interface AgentVirtualRechargeMapper {

	/**
	 * 添加充值记录
	 * @author Moyq5
	 * @date 2017年11月6日
	 * @param data
	 */
	public void add(AgentVirtualRecharge data);

	/**
	 * 查询记录数
	 * @author Moyq5
	 * @date 2017年11月6日
	 * @param recharge4s
	 * @return
	 */
	public Integer count(AgentVirtualRecharge4Search recharge4s);

}
