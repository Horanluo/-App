package com.alycloud.pay.mapper.tmp;

import java.util.List;

import com.alycloud.modules.entity.SystemBankId;
import com.alycloud.modules.search.SystemBankId4Search;


/**
 * 银行总行相关的操作类
 * @author Moyq5
 * @date 2017年9月11日
 */
public interface SystemBankIdMapper {

	
	public List<SystemBankId> listByPage(SystemBankId4Search bank4s);
}
