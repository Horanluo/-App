package com.alycloud.schedules.yufu.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.schedules.feign.FastOrderFeign;
import com.alycloud.schedules.feign.QrcodeOrderFeign;

import lombok.extern.slf4j.Slf4j;

/**
 * 支付状态查询
 * @author Moyq5
 * @date 2017年11月12日
 */
@Component
@Slf4j
public class OrderPayStatusCheckTask {

	@Autowired
	private FastOrderFeign fastOrderFeign;
	@Autowired
	private QrcodeOrderFeign qrcodeOrderFeign;
	
	@Scheduled(cron="0 0/1 0-23 * * ?")
	public void process() throws Exception {
		log.debug("开始查询订单付款状态");
		fastOrderFeign.checkPayStatus(new RequestBean<String>());
		qrcodeOrderFeign.checkPayStatus(new RequestBean<String>());
	}
	
}
