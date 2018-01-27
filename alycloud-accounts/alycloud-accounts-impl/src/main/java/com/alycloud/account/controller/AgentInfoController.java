package com.alycloud.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IAgentInfoService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.search.AgentInfo4Search;

import io.swagger.annotations.ApiOperation;

/**
 * 代理商信息
 * @author Moyq5
 * @date 2017年10月18日
 */
@RequestMapping(value="/agentInfo")
@RestController
public class AgentInfoController {

	@Autowired
	IAgentInfoService agentInfoService;
	

	/**
	 * 更新代理商信息
	 * @author Moyq5
	 * @date 2017年11月2日
	 */
	@ApiOperation(notes = "调用 /mod方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "更新代理商信息")
	@PostMapping(value = "/mod")
	@SystemControllerLog(description = "更新代理商信息")
	public ResultBean<?> mod(@RequestBody RequestBean<AgentInfo> reqData) throws Exception{
		agentInfoService.mod(reqData.getData());
		return RestBeanGenerator.genSuccessResult();
	}
	
	
	/**
	 * 获取同代理商类型最大的代理商号
	 * @author Moyq5
	 * @date 2017年10月27日
	 */
	@ApiOperation(notes = "调用 /getMaxAgentnoByAgentno方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取最大的代理商号")
	@RequestMapping(value="/getMaxAgentnoByAgentno")
	@SystemControllerLog(description = "获取最大的代理商号")
	public ResultBean<String> getMaxAgentnoByAgentno(@RequestBody RequestBean<String> reqData) throws Exception{
		String maxAgentno = agentInfoService.getMaxAgentnoByAgentno(reqData.getData());
        return RestBeanGenerator.genSuccessResult(maxAgentno);
	}
	
	/**
	 * 添加代理商
	 * @author Moyq5
	 * @date 2017年10月27日
	 */
	@ApiOperation(notes = "调用 /add方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "添加代理商")
	@RequestMapping(value="/add")
	@SystemControllerLog(description = "添加代理商")
	public ResultBean<AgentInfo> add(@RequestBody RequestBean<AgentInfo> reqData) throws Exception{
		AgentInfo agent = agentInfoService.add(reqData.getData());
        return RestBeanGenerator.genSuccessResult(agent);
	}
	
	/**
	 * 根据代理商号获取代理商信息
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@RequestMapping(value="/getByAgentno")
	public ResultBean<AgentInfo> getByAgentno(@RequestBody RequestBean<String> reqData) throws Exception{
		AgentInfo agent = agentInfoService.getByAgentno(reqData.getData());
        return RestBeanGenerator.genSuccessResult(agent);
	}
	
	/**
	 * 获取代理商列表信息
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取代理商列表信息")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "获取代理商列表信息")
	public ResultBean<List<AgentInfo>> listByPage(@RequestBody RequestBean<AgentInfo4Search> reqBody) {
		List<AgentInfo> agentList = agentInfoService.listByPage(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(agentList);
	}

}
