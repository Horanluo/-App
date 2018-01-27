package com.alycloud.financial.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.alycloud.modules.entity.AgentVirtualTrans;
import com.alycloud.modules.search.AgentVirtualTrans4Search;

/**
 * 代理商虚拟账户提现流水
 * @author Moyq5
 * @date 2017年7月11日
 */
public interface AgentVirtualTransMapper {

	public String genRefno();

	public void add(AgentVirtualTrans data);

	public AgentVirtualTrans getByTraceno(String traceno);

	public List<AgentVirtualTrans> listByPage(AgentVirtualTrans4Search trans4s);
	
	public List<AgentVirtualTrans> listByGroupByDate(AgentVirtualTrans4Search trans4s);

	public BigDecimal sum(AgentVirtualTrans4Search trans4s);
	
	public Integer modifyRecord(AgentVirtualTrans agentVirtualTrans);
}
