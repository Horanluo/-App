package com.alycloud.modules.search;

import com.alycloud.modules.entity.FastOrder;

/**
 * 快捷支付订单查询条件对象
 * @author Moyq5
 * @date 2017年4月21日
 */
public class FastOrder4Search extends FastOrder {
	
	private Integer pageSize = SystemIC.PAGE_SIZE;
	private Integer page = 1;
	private String beginAddTime;// 下单时间（查询起始时间）
	
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
	public String getBeginAddTime() {
		return beginAddTime;
	}
	public void setBeginAddTime(String beginAddTime) {
		this.beginAddTime = beginAddTime;
	}
	
	// 在这里扩展查询条件属性
	
}
