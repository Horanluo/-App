package com.alycloud.pay.mapper.tmp;

import java.math.BigDecimal;

import com.alycloud.modules.entity.MerchUpgrade;
import com.alycloud.modules.search.MerchUpgrade4Search;


/**
 * 商户升级记录
 * @author Moyq5
 * @date 2017年7月14日
 */
public interface MerchUpgradeMapper {
	
	public int add(MerchUpgrade merchUpgrade);

	public MerchUpgrade getByRefno(String refno);
	
	public BigDecimal sumTransAmount(MerchUpgrade4Search upgrade4s);

}
