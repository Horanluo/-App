package com.alycloud.account.service.upgrade;

import com.alycloud.modules.entity.MerchUser;

public abstract class Upgrader {

	abstract MerchUser upgrade() throws Exception;

}