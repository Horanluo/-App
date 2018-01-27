package com.alycloud.financial.api;

import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentVirtualCard;
import com.alycloud.modules.search.AgentTrans4Search;

/**
 * 代理商虚拟账户Service
 * @author Moyq5
 * @date 2017年10月24日
 */
public interface IAgentVirtualCardService {


	/**
	 * 为代理商创建虚拟账户
	 * @author Moyq5
	 * @date 2017年11月6日
	 * @param agentno
	 * @return
	 * @throws Exception 
	 */
	AgentVirtualCard add4Agent(String agentno) throws Exception;

	/**
	 * 提现
	 * @author Moyq5
	 * @date 2017年10月24日
	 * @param agent 提现代理商
	 * @param amount 提现金额(元)
	 * @throws Exception 
	 */
	SingleResult<String> draw(AgentInfo agent, String amount) throws Exception;

	/**
	 * 获取虚拟账户
	 * @author Moyq5
	 * @date 2017年11月5日
	 * @param agentno
	 * @return
	 */
	AgentVirtualCard getByAgentno(String agentno);

	/**
	 * 根据分润流水充值
	 * @author Moyq5
	 * @date 2017年11月5日
	 * @param data
	 */
	void recharge(AgentTrans4Search data);

	void addAvailAmount(AgentVirtualCard card4AvailAmount);

}
