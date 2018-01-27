package com.alycloud.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IAgentRolerService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.AgentRoler;
import com.alycloud.modules.search.AgentRoler4Search;

import io.swagger.annotations.ApiOperation;

/**
 * 代理商操作员
 * @author Moyq5
 * @date 2017年10月27日
 */
@RequestMapping(value="/agentRoler")
@RestController
public class AgentRolerController {

	@Autowired
	IAgentRolerService agentRolerService;
	
	/**
	 * 查询列表信息
	 * @author Moyq5
	 * @date 2017年10月27日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询代理商操作员列表")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "查询代理商操作员列表")
	public ResultBean<List<AgentRoler>> listByPage(@RequestBody RequestBean<AgentRoler4Search> reqBody) {
		List<AgentRoler> agentList = agentRolerService.listByPage(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(agentList);
	}

}
