package com.alycloud.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.account.api.IAgentRelateService;
import com.alycloud.account.mapper.AgentRelateMapper;
import com.alycloud.modules.entity.AgentRelate;
import com.alycloud.modules.search.AgentRelate4Search;

/**
 * 代理商层级关系
 * @author Moyq5
 * @date 2017年10月27日
 */
@Service
public class AgentRelateService implements IAgentRelateService {

	@Autowired
	private AgentRelateMapper agentRelateMapper;
	
	@Override
	public List<AgentRelate> listByPage(AgentRelate4Search agentRelate4s) {
		return agentRelateMapper.listByPage(agentRelate4s);
	}
	
	@Override
	public void batchAdd(List<AgentRelate> relates) {
		agentRelateMapper.batchAdd(relates);
	}

}
