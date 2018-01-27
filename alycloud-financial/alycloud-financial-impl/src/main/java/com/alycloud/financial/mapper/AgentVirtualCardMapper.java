package com.alycloud.financial.mapper;

import com.alycloud.modules.entity.AgentVirtualCard;

/**
 * 代理虚拟账户
 * @author Moyq5
 * @date 2017年3月12日
 */
public interface AgentVirtualCardMapper {

	public int add(AgentVirtualCard card);

	/**
	 * 增加或者减少可提现金额。
	 * 本方法只用到两个参数：id,availAmount;<br>
	 * 当availAmount为正数时，可提现金额会在当前提可提现金额基础上增加该值，否则在当前提可提现金额基础上减去该值。
	 * @author Moyq5
	 * @date 2017年10月25日
	 * @param card
	 */
	public void addAvailAmount(AgentVirtualCard card);

	/**
	 * 增加或者减少在途金额。
	 * 本方法只用到两个参数：id,transitAmount;<br>
	 * 当transitAmount为正数时，在途金额会在当前在途金额基础上增加该值，否则在当前在途金额基础上减去该值。
	 * @author Moyq5
	 * @date 2017年11月5日
	 * @param card
	 */
	public void addTransitAmount(AgentVirtualCard card);
	
	public AgentVirtualCard getByAgentno(String agentno);

	public String getMaxCardo();


}
