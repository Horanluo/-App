package com.alycloud.account.mapper;

import java.util.List;

import com.alycloud.modules.entity.AgentUser;
import com.alycloud.modules.search.AgentUser4Search;


/**
 * 代理商操作员
 * @author Moyq5
 * @date 2017年7月11日
 */
public interface AgentUserMapper {
	
	public List<AgentUser> listByPage(AgentUser4Search agentUser4s);

	public void add(AgentUser agentUser);

}
