package com.alycloud.account.service.upgrade;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Moyq5
 * @date 2017年10月29日
 */
@Slf4j
public class Context {

	private Upgrader upgrader;
	
	public Context(Upgrader upgrader) {
		this.upgrader = upgrader;
	}
	
	public void upgrade() {
		try {
			this.upgrader.upgrade();
		} catch (Exception e) {
			log.error("用户升级失败：", e);
		}
	}
}
