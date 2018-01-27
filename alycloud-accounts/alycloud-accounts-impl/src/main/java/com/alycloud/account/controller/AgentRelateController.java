package com.alycloud.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IAgentRelateService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.AgentRelate;
import com.alycloud.modules.search.AgentRelate4Search;

import io.swagger.annotations.ApiOperation;

/**
 * 代理商层级关系
 * @author Moyq5
 * @date 2017年10月27日
 */
@RequestMapping(value="/agentRelate")
@RestController
public class AgentRelateController {

	@Autowired
	IAgentRelateService agentRelateService;
	
	/**
	 * 查询列表信息
	 * @author Moyq5
	 * @date 2017年10月27日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询代理商层级关系列表")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "查询代理商层级关系列表")
	public ResultBean<List<AgentRelate>> listByPage(@RequestBody RequestBean<AgentRelate4Search> reqBody) {
		List<AgentRelate> agentList = agentRelateService.listByPage(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(agentList);
	}
	
	/**
	 * 批量添加
	 * @author Moyq5
	 * @date 2017年10月27日
	 */
	@ApiOperation(notes = "调用 /batchAdd方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "批量添加代理商层级关系")
	@RequestMapping(value="/batchAdd")
	@SystemControllerLog(description = "批量添加代理商层级关系")
	public ResultBean<?> batchAdd(@RequestBody RequestBean<List<AgentRelate>> reqData) throws Exception{
		agentRelateService.batchAdd(reqData.getData());
		return RestBeanGenerator.genSuccessResult();
	}
	

}
