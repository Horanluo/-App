package com.alycloud.modules.search;

import com.alycloud.modules.entity.MerchVirtualCard;

/**
 * 商户虚拟账户查询条件对象
 * @author Moyq5
 * @date 2017年4月24日
 */
public class MerchVirtualCard4Search extends MerchVirtualCard {

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
}
