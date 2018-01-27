package com.alycloud.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import com.alycloud.account.api.IMerchService;
import com.alycloud.account.api.IShareService;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.modules.entity.MerchInfo;
import lombok.extern.slf4j.Slf4j;

@Service
@ConfigurationProperties(prefix="hfbank")
@Slf4j
public class ShareServiceImpl implements IShareService {

	@Autowired
	private IMerchService merchService;
	
	@Override
	@ServiceLogAnnotation(moduleName="分享接口")
	public boolean share(String merchno) {
		MerchInfo merchInfo = merchService.getByMerchno(merchno);
		try {
			merchService.upgradeInviter(merchInfo.getId());
			log.info("分享成功");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("分享失败");
		}
		return false;
	}
}
