package com.alycloud.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.account.api.IAgentInfoService;
import com.alycloud.account.mapper.AgentInfoMapper;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.search.AgentInfo4Search;

/**
 * 代理商
 * @author Moyq5
 * @date 2017年10月27日
 */
@Service
public class AgentInfoService implements IAgentInfoService {

	@Autowired
	private AgentInfoMapper agentInfoMapper;
	
	@Override
	public AgentInfo getByAgentno(String agentno) {
		return agentInfoMapper.getByAgentno(agentno);
	}

	@Override
	public List<AgentInfo> listByPage(AgentInfo4Search agent4s) {
		return agentInfoMapper.listByPage(agent4s);
	}

	@Override
	public String getMaxAgentnoByAgentno(String agentno) {
		return agentInfoMapper.getMaxAgentnoByAgentno(agentno);
	}

	@Override
	public AgentInfo add(AgentInfo agentInfo) {
		agentInfoMapper.add(agentInfo);
		return agentInfo;
	}

	@Override
	public void mod(AgentInfo agentInfo) {
		agentInfoMapper.mod(agentInfo);
	}

}
