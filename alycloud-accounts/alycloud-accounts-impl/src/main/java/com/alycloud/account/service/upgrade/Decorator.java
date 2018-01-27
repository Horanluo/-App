package com.alycloud.account.service.upgrade;

import com.alycloud.modules.entity.MerchUser;

/**
 * 
 * @author Moyq5
 * @date 2017年10月27日
 */
public abstract class Decorator extends Upgrader {

	private Upgrader userUpgrader;

	public Decorator(Upgrader userUpgrader) {
		this.userUpgrader = userUpgrader;
	}

	@Override
	public MerchUser upgrade() throws Exception {
		return this.userUpgrader.upgrade();
	}

}
