package com.alycloud.financial.support.agent;

import java.util.List;

import com.alycloud.modules.entity.AgentTrans;


/**
 * 代理商分润实现
 * @author Moyq5
 * @date 2017年11月6日
 */
public interface TransBuilder {

	/**
	 * @author Moyq5
	 * @date 2017年11月6日
	 * @return
	 * @throws TransException
	 */
	List<AgentTrans> build() throws TransException;
}
