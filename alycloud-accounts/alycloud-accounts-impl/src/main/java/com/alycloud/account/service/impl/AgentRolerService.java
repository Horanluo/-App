package com.alycloud.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.account.api.IAgentRolerService;
import com.alycloud.account.mapper.AgentRolerMapper;
import com.alycloud.modules.entity.AgentRoler;
import com.alycloud.modules.search.AgentRoler4Search;

/**
 * 代理商操作员角色
 * @author Moyq5
 * @date 2017年10月27日
 */
@Service
public class AgentRolerService implements IAgentRolerService {

	@Autowired
	private AgentRolerMapper agentRolerMapper;
	
	@Override
	public List<AgentRoler> listByPage(AgentRoler4Search agentRoler4s) {
		return agentRolerMapper.listByPage(agentRoler4s);
	}

}
