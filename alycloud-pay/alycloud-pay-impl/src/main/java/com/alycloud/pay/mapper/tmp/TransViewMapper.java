package com.alycloud.pay.mapper.tmp;

import java.util.List;

import com.alycloud.modules.entity.TransViewBean;
import com.alycloud.modules.search.TransViewBean4Search;

/**
 * 交易流水(视图)
 * @author Moyq5
 * @date 2017年4月27日
 */
public interface TransViewMapper {

	
	public List<TransViewBean> listByPage(TransViewBean4Search trans4s);
}
