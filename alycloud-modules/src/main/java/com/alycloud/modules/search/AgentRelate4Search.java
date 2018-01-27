package com.alycloud.modules.search;

import com.alycloud.modules.entity.AgentRelate;

/**
 * 代理商关系查询条件
 * @author Moyq5
 * @date 2017年6月6日
 */
public class AgentRelate4Search extends AgentRelate {
	
	private Integer pageSize = SystemIC.PAGE_SIZE;
	private Integer page = 1;
	private Integer gtAgentLevel;// 级别大于gtAgentLevel
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getStartIndex() {
		return (page - 1) * pageSize;
	}
	public Integer getGtAgentLevel() {
		return gtAgentLevel;
	}
	public void setGtAgentLevel(Integer gtAgentLevel) {
		this.gtAgentLevel = gtAgentLevel;
	}
	
	// 在这里扩展查询条件属性
	
}
