package com.alycloud.account.mapper;

import java.util.List;

import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.search.AgentInfo4Search;


/**
 * 代理商相关的操作类
 * @author Moyq5
 * @date 2017年5月23日
 */
public interface AgentInfoMapper {

	public List<AgentInfo> listByPage(AgentInfo4Search agentInfo4s);

	public AgentInfo getByAgentno(String agentno);
	
	public String getMaxAgentnoByAgentno(String agentno);
	
	public int mod(AgentInfo agent);

	public void add(AgentInfo agentInfo);

	AgentInfo getByMerchInfo(MerchInfo merchInfo);
}
