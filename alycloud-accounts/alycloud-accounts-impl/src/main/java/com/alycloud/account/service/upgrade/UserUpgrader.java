package com.alycloud.account.service.upgrade;

import com.alycloud.modules.entity.MerchUser;

public class UserUpgrader extends Upgrader {

	private MerchUser merchUser;
	
	public UserUpgrader(MerchUser merchUser) {
		this.merchUser = merchUser;
	}
	
	@Override
	public MerchUser upgrade() throws Exception {
		return this.merchUser;
	};
}
