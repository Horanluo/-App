package com.alycloud.account.mapper;

import java.util.List;
import com.alycloud.modules.entity.ChangeMerchAccount;
import com.alycloud.modules.entity.ChangeMerchInfo;
import com.alycloud.modules.search.ChangeMerchAccount4Search;

/**
 * 商户结算账户变更
 * @author Moyq5
 * @date 2017年9月21日
 */
public interface ChangeMerchAccountMapper {

	public int add(ChangeMerchAccount account);
	
	public int mod(ChangeMerchAccount account);
	
	public List<ChangeMerchAccount> listByPage(ChangeMerchAccount4Search account4s);
	
	/**
	 * 查询待审核的信息，新增银行卡照片
	 * @param merchno
	 * @return
	 * @author Horanluo
	 */
	List<ChangeMerchAccount> getChangeMerchAccount(String merchno);
	
	/**
	 * 更新待审核的信息，新增银行卡照片
	 * @param cma
	 * @return
	 */
	int updateToAuditInfo(ChangeMerchAccount cma);
	
	/**
	 * 获取待审核商户信息   查询身份证
	 * @param merchno
	 * @return
	 * @author Horanluo
	 */
	public List<ChangeMerchInfo> getChangeMerchInfoList(String merchno);
}
