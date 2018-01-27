package com.alycloud.financial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.financial.mapper.MerchVirtualTransMapper;
import com.alycloud.modules.entity.MerchVirtualTrans;
import io.swagger.annotations.ApiOperation;

/**
 * 商户虚拟账户提现流水
 * @author Horanluo
 * @date 2018年01月23日
 */
@RequestMapping(value="/MerchVirtualTrans")
@RestController
public class MerchVirtualTransController{

	@Autowired
	private MerchVirtualTransMapper merchVirtualTransMapper;
	
	@ApiOperation(notes = "调用 /getMerchVirtualTrans方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取商户提现待出款的订单")
	@PostMapping(value = "/getMerchVirtualTrans")
	@SystemControllerLog(description = "获取商户提现待出款的订单")
	public List<MerchVirtualTrans> getMerchVirtualTrans(@RequestBody MerchVirtualTrans merchVirtualTrans){
		return merchVirtualTransMapper.getResultList(merchVirtualTrans);
	}
	
	@ApiOperation(notes = "调用 /updatePayStatus方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "更新商户提现待出款的订单状态")
	@PostMapping(value = "/updatePayStatus")
	@SystemControllerLog(description = "更新商户提现待出款的订单状态")
	public Integer updatePayStatus(@RequestBody MerchVirtualTrans merchVirtualTrans){
		return merchVirtualTransMapper.updatePayStatus(merchVirtualTrans);
	}
	
	@ApiOperation(notes = "调用 /getBatchList方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取商户批量提现待出款的订单")
	@PostMapping(value = "/getBatchList")
	@SystemControllerLog(description = "获取商户批量提现待出款的订单")
	public List<MerchVirtualTrans> getBatchList(@RequestBody MerchVirtualTrans merchVirtualTrans){
		return merchVirtualTransMapper.getBatchList(merchVirtualTrans);
	}
	
	@ApiOperation(notes = "调用 /getInfoByBatchno方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取代付批次的信息")
	@PostMapping(value = "/getInfoByBatchno")
	@SystemControllerLog(description = "获取代付批次的信息")
	public List<MerchVirtualTrans> getInfoByBatchno(@RequestBody String batchno){
		return merchVirtualTransMapper.getInfoByBatchno(batchno);
	}
}
