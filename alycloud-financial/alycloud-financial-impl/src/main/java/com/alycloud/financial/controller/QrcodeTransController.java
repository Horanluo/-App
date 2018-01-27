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
import com.alycloud.financial.api.IQrcodeTransService;
import com.alycloud.modules.entity.QrcodeTrans;
import com.alycloud.modules.search.QrcodeTrans4Search;
import io.swagger.annotations.ApiOperation;

/**
 * 二维码交易流水
 * @author Moyq5
 * @date 2017年11月7日
 */
@RequestMapping(value="/qrcodeTrans")
@RestController
public class QrcodeTransController {

	@Autowired
	IQrcodeTransService qrcodeTransService;
	
	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年11月7日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询二维码交易列表")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "查询二维码交易列表")
	public ResultBean<List<QrcodeTrans>> listByPage(@RequestBody RequestBean<QrcodeTrans4Search> reqBody) throws Exception {
		QrcodeTrans4Search trans4s = reqBody.getData();
		if (null == trans4s) {
			trans4s = new QrcodeTrans4Search();
		}
		List<QrcodeTrans> transList = qrcodeTransService.listByPage(trans4s);
		return RestBeanGenerator.genSuccessResult(transList);
	}
	
	/**
	 * 根据订单号生成交易流水
	 * @author Moyq5
	 * @date 2017年11月8日
	 */
	@ApiOperation(notes = "调用 /addByOrderno方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "根据二维码订单号生成交易流水")
	@PostMapping(value = "/addByOrderno")
	@SystemControllerLog(description = "根据二维码订单号生成交易流水")
	public ResultBean<?> addByOrderno(@RequestBody RequestBean<String> reqBody) throws Exception {
		qrcodeTransService.addByOrderno(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}

}
