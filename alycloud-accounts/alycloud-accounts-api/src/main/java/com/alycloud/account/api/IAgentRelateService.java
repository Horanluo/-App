package com.alycloud.account.api;

import java.util.List;

import com.alycloud.modules.entity.AgentRelate;
import com.alycloud.modules.search.AgentRelate4Search;

/**
 * 代理商层级关系
 * @author Moyq5
 * @date 2017年10月27日
 */
public interface IAgentRelateService {

	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月27日
	 * @param agentRelate4s
	 * @return
	 */
	List<AgentRelate> listByPage(AgentRelate4Search agentRelate4s);

	/**
	 * 批量添加
	 * @author Moyq5
	 * @date 2017年10月27日
	 * @param relates
	 * @return
	 */
	void batchAdd(List<AgentRelate> relates);

}
