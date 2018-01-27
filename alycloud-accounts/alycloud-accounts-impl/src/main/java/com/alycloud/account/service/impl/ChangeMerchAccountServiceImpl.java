package com.alycloud.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.account.api.IChangeMerchAccountService;
import com.alycloud.account.feign.MerchInfoFeign;
import com.alycloud.account.mapper.ChangeMerchAccountMapper;
import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.modules.entity.ChangeMerchAccount;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.enums.ChangeMerchAccountAuditStatus;
import com.alycloud.modules.search.ChangeMerchAccount4Search;

/**
 * 商户结算账户变更
 * @author Moyq5
 * @date 2017年10月23日
 */
@Service
public class ChangeMerchAccountServiceImpl implements IChangeMerchAccountService {

	@Autowired
	private ChangeMerchAccountMapper changeMerchAccountMapper;
	@Autowired
	private MerchInfoFeign merchInfoFeign;
	
	@Override
	public void addByMerchno(String merchno, ChangeMerchAccount account) throws Exception {
		RequestBean<String> merchFeignData = new RequestBean<String>();
		merchFeignData.setData(merchno);
		MerchInfo merch = merchInfoFeign.getByMerchno(merchFeignData).getData();
		
		ChangeMerchAccount4Search account4s = new ChangeMerchAccount4Search();
		account4s.setMerchId(new Long(merch.getId()));
		account4s.setPageSize(1);
		List<ChangeMerchAccount> accountList = changeMerchAccountMapper.listByPage(account4s);
		if (accountList.size() > 0) {
			ChangeMerchAccount accountExists = accountList.get(0);
			ChangeMerchAccountAuditStatus status = ChangeMerchAccountAuditStatus.values()[accountExists.getAuditStatus()];
			if (status != ChangeMerchAccountAuditStatus.REJECTIVE
					&& status != ChangeMerchAccountAuditStatus.AUDITED) {
				throw new ApiException(ApiCode.C0010);
			}
		}
		
		account.setMerchId(new Long(merch.getId()));
		account.setAddTime(DateFormat.DATE_TIME.format());
		account.setAuditStatus(ChangeMerchAccountAuditStatus.UNAUDITED.ordinal());
		account.setOriAccountName(merch.getAccountName());
		account.setOriAccountno(merch.getAccountno());
		account.setOriAccountType(merch.getAccountType());
		account.setOriBankName(merch.getBankName());
		account.setOriBankno(merch.getBankno());
		account.setOriImgCard1(merch.getCard1Img());
		account.setOriImgCard2(merch.getCard2Img());
		changeMerchAccountMapper.add(account);
		
	}
}
