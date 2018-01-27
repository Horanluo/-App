/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对像
 * <pre>
 *	@param <T>
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年10月11日 
 *
 */
public class SpecificPage<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4560275961099368343L;
	private List<T> content = new ArrayList<T>();
	private int page;
	private int size;
	private long total;
	
	public SpecificPage(int page, int size, long total, List<T> content) {
		super();
		this.setContent(content);
		this.setPage(page);
		this.setSize(size);
		this.setTotal(total);
	}

	public SpecificPage() {
		super();
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		if (null == content || content.isEmpty()) {
			return;
		}
		this.content.clear();
		this.content.addAll(content);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * 获取当前对像的条数
	 * @return
	 */
	public int getNumberOfElements() {
		return content.size();
	}
	
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPages() {
		return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
	}

	/**
	 * 返回显示的页数
	 * @return
	 */
	public List<Integer> getShowPages() {
		List<Integer> list = new ArrayList<Integer>();
		for (Integer start=this.getPage() - 2; start<= this.getTotalPages(); start++) {
			if (start > 0) {
				list.add(start);
			}
			if (list.size() >= 5 ) {
				break;
			}
		}
		
		if (list.size() == 1) {
			list.clear();
		} else if (list.size() < 5) {
			list.clear();
			for (Integer start = this.getPage() - 5; start<=this.getTotalPages(); start++) {
				if (start > 0 && !list.contains(start)) {
					list.add(start);
				}
			}
		}
		
		return list;
	}
}
