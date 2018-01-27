package com.alycloud.account.mapper;

import java.util.List;

import com.alycloud.modules.entity.ChangeMerchQualification;
import com.alycloud.modules.search.ChangeMerchQualification4Search;

/**
 * 经营资质变更
 * @author Moyq5
 * @date 2017年9月20日
 */
public interface ChangeMerchQualificationMapper {

	public int add(ChangeMerchQualification qualification);
	
	public int mod(ChangeMerchQualification qualification);
	
	public List<ChangeMerchQualification> listByPage(ChangeMerchQualification4Search qualification4s);

}
