package com.alycloud.account.mapper;

import java.util.List;

import com.alycloud.modules.entity.AgentRelate;
import com.alycloud.modules.search.AgentRelate4Search;


/**
 * 代理商层级关系相关的操作类
 * @author Moyq5
 * @date 2017年6月6日
 */
public interface AgentRelateMapper {

	public List<AgentRelate> listByPage(AgentRelate4Search agentRelate4s);

	public void batchAdd(List<AgentRelate> relates);

}
