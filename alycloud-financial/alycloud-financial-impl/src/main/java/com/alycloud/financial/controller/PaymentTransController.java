package com.alycloud.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.financial.api.IAgentVirtualTransService;
import com.alycloud.financial.mapper.PaymentTransMapper;
import com.alycloud.modules.entity.AgentVirtualTrans;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.modules.entity.PaymentTrans;

/**
 * 代付流水
 * @author Moyq5
 * @date 2017年10月24日
 */
@RequestMapping(value="/paymentTrans")
@RestController
public class PaymentTransController extends AbstractAgentController {

	@Autowired
	private IAgentVirtualTransService agentVirtualTransService;
	@Autowired
	private PaymentTransMapper paymentTransMapper;
	
	/**
	 * 生成代付流水号
	 * @author Moyq5
	 * @date 2017年10月24日
	 */
	@GetMapping(value = "/genRefno")
	@SystemControllerLog(description = "生成代付流水号")
	public ResultBean<String> genRefno() throws Exception {
		return RestBeanGenerator.genSuccessResult(agentVirtualTransService.genRefno());
	}


	/**
	 * @author Moyq5
	 * @date 2017年10月24日
	 */
	@PostMapping(value = "/add")
	@SystemControllerLog(description = "添加代付流水")
	public ResultBean<PaymentTrans> add(@RequestBody RequestBean<PaymentTrans> requestData) {
		paymentTransMapper.add(requestData.getData());
		return RestBeanGenerator.genSuccessResult(requestData.getData());
	}
	
	/**
	 * @author Horanluo
	 * @date 2018年1月20日
	 */
	@PostMapping(value = "/modifyRecord")
	@SystemControllerLog(description = "更新代理商代付流水信息")
	public Integer modifyRecord(@RequestBody AgentVirtualTrans agentVirtualTrans) {
		return paymentTransMapper.modifyRecord(agentVirtualTrans);
	}

	/**
	 * @author Horanluo
	 * @date 2018年1月25日
	 */
	@PostMapping(value = "/modifyMerchRecord")
	@SystemControllerLog(description = "更新商户代付流水信息")
	public Integer modifyMerchRecord(@RequestBody MerchVirtualTrans merchVirtualTrans) {
		return paymentTransMapper.modifyMerchRecord(merchVirtualTrans);
	}
}
