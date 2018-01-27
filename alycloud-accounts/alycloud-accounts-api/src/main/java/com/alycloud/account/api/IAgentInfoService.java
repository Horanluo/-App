package com.alycloud.account.api;

import java.util.List;

import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.search.AgentInfo4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月23日
 */
public interface IAgentInfoService {

	/**
	 * 根据代理商号获取代理商
	 * @author Moyq5
	 * @date 2017年10月27日
	 * @param agentno
	 * @return
	 */
	AgentInfo getByAgentno(String agentno);

	/**
	 * 查询代理商
	 * @author Moyq5
	 * @date 2017年10月27日
	 * @param agent4s
	 * @return
	 */
	List<AgentInfo> listByPage(AgentInfo4Search agent4s);

	/**
	 * 获取最大代理商号
	 * @author Moyq5
	 * @date 2017年10月27日
	 * @param agentno
	 * @return
	 */
	String getMaxAgentnoByAgentno(String agentno);

	/**
	 * 添加代理商
	 * @author Moyq5
	 * @date 2017年10月27日
	 * @param agentInfo
	 * @return
	 */
	AgentInfo add(AgentInfo agentInfo);

	/**
	 * 更新代理商
	 * @author Moyq5
	 * @date 2017年11月2日
	 * @param agentInfo
	 */
	void mod(AgentInfo agentInfo);


}
