package com.alycloud.pay.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Horanluo
 * @date 2017年11月20日
 */
@Controller
@RequestMapping("/front")
public class FrontNotifyController {
	/**
	 * hxtc支付成功前台通知商户接口
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年12月21日
	 */
	@RequestMapping(value="/hxtcNotify",method=RequestMethod.GET)
	public String hxtcNotify(ModelMap map) throws Exception {
		return "index";
	}
	
	/**
	 * 扫呗支付成功前台通知商户接口
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年12月21日
	 */
	@RequestMapping(value="/saobeiNotify",method=RequestMethod.GET)
	public String saobeiNotify(ModelMap map) throws Exception {
		//log.info("扫呗支前端通知结果:"+params);
		return "index";
	}
}
