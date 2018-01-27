package com.alycloud.modules.search;

import com.alycloud.modules.entity.MerchInfo;

/**
 * 商户信息
 * @author Moyq5
 * @date 2017年8月11日
 */
public class MerchInfo4Search extends MerchInfo {
	
	private Integer pageSize = SystemIC.PAGE_SIZE;
	private Integer page = 1;
	private Integer synced;
	private Integer unsynced;
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
	public Integer getSynced() {
		return synced;
	}
	public void setSynced(Integer synced) {
		this.synced = synced;
	}
	public Integer getUnsynced() {
		return unsynced;
	}
	public void setUnsynced(Integer unsynced) {
		this.unsynced = unsynced;
	}
	
	// 在这里扩展查询条件属性
	
}
