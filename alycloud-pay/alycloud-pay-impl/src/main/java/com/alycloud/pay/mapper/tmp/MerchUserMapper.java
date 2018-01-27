package com.alycloud.pay.mapper.tmp;

import java.util.List;

import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.search.MerchUser4Search;


/**
 * 商户操作员
 * @author Moyq5
 * @date 2017年5月23日
 */
public interface MerchUserMapper {
	
	public List<MerchUser> listByPage(MerchUser4Search merchUser4s);

	public MerchUser getById(Integer id);

}
