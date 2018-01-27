package com.alycloud.pay.mapper.tmp;

import java.util.List;

import com.alycloud.modules.entity.SystemBank;
import com.alycloud.modules.search.SystemBank4Search;


/**
 * 银行支行相关的操作类
 * @author Moyq5
 * @date 2017年9月11日
 */
public interface SystemBankMapper {

	
	public List<SystemBank> listByPage(SystemBank4Search bank4s);

}
