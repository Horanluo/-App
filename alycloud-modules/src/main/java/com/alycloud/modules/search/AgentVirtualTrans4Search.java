package com.alycloud.modules.search;

import com.alycloud.modules.entity.AgentVirtualTrans;

/**
 * 代理商提现信息
 * @author Moyq5
 * @date 2017年10月25日
 */
public class AgentVirtualTrans4Search extends AgentVirtualTrans {
	
	private Integer pageSize = SystemIC.PAGE_SIZE;
	private Integer page = 1;
	private String startDate;// 起始日期
	private String endDate;// 截止日期
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	// 在这里扩展查询条件属性
	
}
