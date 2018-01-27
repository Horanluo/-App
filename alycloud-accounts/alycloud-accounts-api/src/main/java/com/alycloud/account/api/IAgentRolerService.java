package com.alycloud.account.api;

import java.util.List;

import com.alycloud.modules.entity.AgentRoler;
import com.alycloud.modules.search.AgentRoler4Search;

/**
 * 代理商操作员角色
 * @author Moyq5
 * @date 2017年10月27日
 */
public interface IAgentRolerService {

	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月27日
	 * @param agentRoler4s
	 * @return
	 */
	List<AgentRoler> listByPage(AgentRoler4Search agentRoler4s);

}
