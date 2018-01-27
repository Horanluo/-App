package com.alycloud.modules.search;

import com.alycloud.modules.entity.ChannelSubmerchInfo;

/**
 * 渠道商户信息(新)
 * @author Moyq5
 * @date 2017年10月20日
 */
public class ChannelSubmerchInfo4Search extends ChannelSubmerchInfo {
	
	private Integer pageSize = SystemIC.PAGE_SIZE;
	private Integer page = 1;
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
	
	// 在这里扩展查询条件属性
	
}
