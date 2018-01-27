package com.alycloud.financial.controller;

import java.util.List;

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
import com.alycloud.financial.api.IFastTransService;
import com.alycloud.modules.entity.FastTrans;
import com.alycloud.modules.search.FastTrans4Search;

import io.swagger.annotations.ApiOperation;

/**
 * 快捷交易流水
 * @author Moyq5
 * @date 2017年11月7日
 */
@RequestMapping(value="/fastTrans")
@RestController
public class FastTransController {

	@Autowired
	IFastTransService fastTransService;
	
	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年11月7日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询快捷交易列表")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "查询快捷交易列表")
	public ResultBean<List<FastTrans>> listByPage(@RequestBody RequestBean<FastTrans4Search> reqBody) throws Exception {
		FastTrans4Search trans4s = reqBody.getData();
		if (null == trans4s) {
			trans4s = new FastTrans4Search();
		}
		List<FastTrans> transList = fastTransService.listByPage(trans4s);
		return RestBeanGenerator.genSuccessResult(transList);
	}
	

	/**
	 * 根据订单号生成交易流水
	 * @author Moyq5
	 * @date 2017年11月8日
	 */
	@ApiOperation(notes = "调用 /addByOrderno方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "根据快捷订单号生成交易流水")
	@PostMapping(value = "/addByOrderno")
	@SystemControllerLog(description = "根据快捷订单号生成交易流水")
	public ResultBean<?> addByOrderno(@RequestBody RequestBean<String> reqBody) throws Exception {
		fastTransService.addByOrderno(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}
	
}
