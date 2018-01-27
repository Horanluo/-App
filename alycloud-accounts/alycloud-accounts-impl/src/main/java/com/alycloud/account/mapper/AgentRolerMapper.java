package com.alycloud.account.mapper;

import java.util.List;

import com.alycloud.modules.entity.AgentRoler;
import com.alycloud.modules.search.AgentRoler4Search;


/**
 * 代理商角色
 * @author Moyq5
 * @date 2017年7月14日
 */
public interface AgentRolerMapper {
	
	public List<AgentRoler> listByPage(AgentRoler4Search agentRoler4s);

}
