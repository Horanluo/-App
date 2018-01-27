package com.alycloud.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.account.api.IAgentUserService;
import com.alycloud.account.mapper.AgentUserMapper;
import com.alycloud.modules.entity.AgentUser;

/**
 * 代理商操作员
 * @author Moyq5
 * @date 2017年10月27日
 */
@Service
public class AgentUserService implements IAgentUserService {

	@Autowired
	private AgentUserMapper agentUserMapper;
	
	@Override
	public AgentUser add(AgentUser agentUser) {
		agentUserMapper.add(agentUser);
		return agentUser;
	}

}
