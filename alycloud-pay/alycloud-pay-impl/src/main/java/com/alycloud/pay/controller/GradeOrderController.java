package com.alycloud.pay.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.GradeOrder;
import com.alycloud.modules.search.GradeOrder4Search;
import com.alycloud.pay.api.IGradeOrderService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 商户升级支付流水
 * @author Moyq5
 * @date 2017年10月30日
 */
@RequestMapping(value="/gradeOrder")
@RestController
@Slf4j
public class GradeOrderController {

	@Autowired
	IGradeOrderService gradeOrderService;
	
	/**
	 * 添加
	 * @author Moyq5
	 * @date 2017年10月30日
	 * 
	 */
	@ApiOperation(notes = "调用 /add方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "添加商户升级流水")
	@PostMapping(value="/add")
	@SystemControllerLog(description = "添加商户升级支付流水")
	public ResultBean<GradeOrder> add(@RequestBody RequestBean<GradeOrder> reqData) throws Exception{
		GradeOrder order = reqData.getData();
		gradeOrderService.add(order);
		return RestBeanGenerator.genSuccessResult(order);
	}
	
	/**
	 * 更新
	 * @author Moyq5
	 * @date 2017年10月30日
	 */
	@ApiOperation(notes = "调用 /mod方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "更新商户升级支付流水")
	@PostMapping(value="/mod")
	@SystemControllerLog(description = "更新商户升级支付流水")
	public ResultBean<?> mod(@RequestBody RequestBean<GradeOrder> reqData) throws Exception{
		gradeOrderService.mod(reqData.getData());
        return RestBeanGenerator.genSuccessResult();
	}
	
	/**
	 * 根据Id获取
	 * @author Moyq5
	 * @date 2017年11月5日
	 */
	@ApiOperation(notes = "调用 /getById方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "根据Id获取商户升级支付流水")
	@PostMapping(value = "/getById")
	@SystemControllerLog(description = "根据Id获取商户升级支付流水")
	public ResultBean<GradeOrder> getById(@RequestBody RequestBean<Integer> reqBody) {
		GradeOrder order = gradeOrderService.getById(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(order);
	}

	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月30日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询商户升级支付流水")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "查询商户升级支付流水")
	public ResultBean<List<GradeOrder>> listByPage(@RequestBody RequestBean<GradeOrder4Search> reqBody) {
		List<GradeOrder> orderList = gradeOrderService.listByPage(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(orderList);
	}

	/**
	 * 订单金额统计
	 * @author Moyq5
	 * @date 2017年10月31日
	 */
	@ApiOperation(notes = "调用 /sumAmount方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "升级订单金额统计")
	@PostMapping(value = "/sumAmount")
	@SystemControllerLog(description = "升级订单金额统计")
	public ResultBean<BigDecimal> sumAmount(@RequestBody RequestBean<GradeOrder4Search> reqBody) {
		BigDecimal amount = gradeOrderService.sumAmount(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(amount);
	}

	/**
	 * 升级支付成功通知
	 * @author Moyq5
	 * @date 2017年10月30日
	 */
	@ApiOperation(notes = "调用 /notify方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "升级支付成功异步通知")
	@PostMapping(value = "/notify")
	//@SystemControllerLog(description = "升级支付成功异步通知")
	public String notify(HttpServletRequest req) throws Exception {
		log.info("升级支付成功异步通知>>>>>>>>>>>>",req);
		gradeOrderService.notifyCallback(req);
		return "0";
	}
	
}
