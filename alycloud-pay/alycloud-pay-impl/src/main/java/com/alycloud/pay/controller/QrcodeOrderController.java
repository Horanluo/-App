/*
 * 类文件名:  MerchProviderController.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.search.QrcodeOrder4Search;
import com.alycloud.modules.vo.QrcodePayDataVO;
import com.alycloud.modules.vo.QrcodePayResultVO;
import com.alycloud.pay.api.IQrcodeOrderService;
import com.alycloud.pay.support.channel.api.ChannelApi;
import com.alycloud.pay.support.channel.api.ChannelQrcodeOrderResult;
import com.alycloud.pay.support.channel.api.impl.PinganApi;
import com.alycloud.pay.support.channel.api.impl.SaoBeiQrcodeApi;
import com.alycloud.pay.support.channel.api.impl.YufuQrcodeApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 二维码交易订单
 * @author Moyq5
 * @date 2017年10月16日
 */
@RestController
@RequestMapping("/qrcodeOrder")
@Api(value = "二维码交易接口")
public class QrcodeOrderController {
	
	@Autowired
	private IQrcodeOrderService qrcodeOrderService;
	@Autowired
	private YufuQrcodeApi yufuQucodeApi;
	@Autowired
	private PinganApi pinganApi;
	@Autowired
	private SaoBeiQrcodeApi saoBeiQrcodeApi;
	
	/**
	 * 二维码支付
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /qrcodePay方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "扫码付")
	@PostMapping(value = "/qrcodePay")
	@SystemControllerLog(description = "扫码付")
	public ResultBean<QrcodePayResultVO> qrcodePay(@Validated @RequestBody RequestBean<QrcodePayDataVO> reqBody) throws Exception {
		String merchno = reqBody.getMerchno();
		QrcodePayDataVO data = reqBody.getData();
		if (StringUtils.isEmpty(merchno)) {
			merchno = data.getMerchno();
		}
		if (null == data.getIsOffical()) {
			data.setIsOffical(false);
		}
		
		QrcodeOrder order = qrcodeOrderService.buildOrder(merchno, data);
		ChannelApi api;
		if("YUFU_QRCODE".equals(order.getChannelCode())){
			api = yufuQucodeApi;
		}else if("PINGAN".equals(order.getChannelCode())){
			api = pinganApi;
		}else if("SAO_BEI".equals(order.getChannelCode())){
			api = saoBeiQrcodeApi;
		}else{
			api = saoBeiQrcodeApi;
		}
		ChannelQrcodeOrderResult apiResult = data.getIsOffical() ? api.officalPay(): api.qrcodePay(order);
		
		
		QrcodePayResultVO resultVo = new QrcodePayResultVO();
		resultVo.setQrcodeUrl(apiResult.getQrcode());
		resultVo.setOrderno(order.getOrderno());
		return RestBeanGenerator.genSuccessResult(resultVo);
	}
	
	/**
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /add方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "添加二维码订单")
	@PostMapping(value = "/add")
	@SystemControllerLog(description = "添加二维码订单")
	public ResultBean<QrcodeOrder> add(@RequestBody RequestBean<QrcodeOrder> requestData) {
		qrcodeOrderService.add(requestData.getData());
		return RestBeanGenerator.genSuccessResult(requestData.getData());
	}

	/**
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "分页查询二维码订单")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "分页查询二维码订单")
	public ResultBean<List<QrcodeOrder>> listByPage(@RequestBody RequestBean<QrcodeOrder4Search> requestData) {
		QrcodeOrder4Search order4s = requestData.getData();
		if (null == order4s) {
			order4s = new QrcodeOrder4Search();
		}
		List<QrcodeOrder> list = qrcodeOrderService.listByPage(order4s);
		return RestBeanGenerator.genSuccessResult(list);
	}

	
	/**
	 * @author Moyq5
	 * @throws Exception 
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /genRefno方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "生成二维码订单号")
	@GetMapping(value = "/genRefno")
	@SystemControllerLog(description = "生成二维码订单号")
	public ResultBean<String> genRefno() throws Exception {
		return RestBeanGenerator.genSuccessResult(qrcodeOrderService.genRefno());
	}

	/**
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /mod方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "更新二维码订单")
	@PostMapping(value = "/mod")
	@SystemControllerLog(description = "更新二维码订单")
	public ResultBean<?> mod(@RequestBody RequestBean<QrcodeOrder> requestData) {
		qrcodeOrderService.mod(requestData.getData());
		return RestBeanGenerator.genSuccessResult();
	}

	/**
	 * 支付成功处理
	 * @author Moyq5
	 * @date 2017年11月9日
	 */
	@ApiOperation(notes = "调用 /paySuccess方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "二维码支付成功处理")
	@PostMapping(value = "/paySuccess")
	@SystemControllerLog(description = "二维码支付成功处理")
	public ResultBean<?> paySuccess(@RequestBody RequestBean<String> requestData) {
		qrcodeOrderService.paySuccess(requestData.getData());
		return RestBeanGenerator.genSuccessResult();
	}


	/**
	 * 检查支付状态
	 * @author Moyq5
	 * @throws Exception 
	 * @date 2017年11月12日
	 */
	@ApiOperation(notes = "调用 /checkPayStatus方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "检查二维码交易支付状态")
	@PostMapping(value = "/checkPayStatus")
	public ResultBean<?> checkPayStatus(@RequestBody RequestBean<String> requestData) throws Exception {
		qrcodeOrderService.checkPayStatus(requestData.getData());
		return RestBeanGenerator.genSuccessResult();
	}
	
}
