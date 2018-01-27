package com.alycloud.financial.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.alycloud.modules.entity.AgentTrans;
import com.alycloud.modules.search.AgentTrans4Search;

/**
 * 代理商分润信息
 * @author Moyq5
 * @date 2017年6月9日
 */
public interface AgentTransMapper {

	public int addList(List<AgentTrans> list);

	public List<AgentTrans> listByPage(AgentTrans4Search trans4s);

	public int countRecord(AgentTrans4Search trans4s);
	
	public List<AgentTrans> listByGroupByDate(AgentTrans4Search trans4s);

	public BigDecimal sum(AgentTrans4Search trans4s);
}
