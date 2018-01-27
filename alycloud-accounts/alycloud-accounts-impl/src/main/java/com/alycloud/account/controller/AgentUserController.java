package com.alycloud.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IAgentUserService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.AgentUser;

import io.swagger.annotations.ApiOperation;

/**
 * 代理商操作员信息
 * @author Moyq5
 * @date 2017年10月27日
 */
@RequestMapping(value="/agentUser")
@RestController
public class AgentUserController {

	@Autowired
	IAgentUserService agentUserService;
	
	/**
	 * 添加代理商操作员
	 * @author Moyq5
	 * @date 2017年10月27日
	 */
	@ApiOperation(notes = "调用 /add方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "添加代理商操作员")
	@RequestMapping(value="/add")
	@SystemControllerLog(description = "添加代理商操作员")
	public ResultBean<AgentUser> add(@RequestBody RequestBean<AgentUser> reqData) throws Exception{
		AgentUser user = agentUserService.add(reqData.getData());
        return RestBeanGenerator.genSuccessResult(user);
	}
	
}
