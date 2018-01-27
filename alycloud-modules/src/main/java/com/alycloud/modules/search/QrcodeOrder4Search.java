package com.alycloud.modules.search;

import com.alycloud.modules.entity.QrcodeOrder;

/**
 * 二维码支付订单查询条件对象
 * @author Moyq5
 * @date 2017年6月8日
 */
public class QrcodeOrder4Search extends QrcodeOrder {
	
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
	private String startDateTime;
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	
}
