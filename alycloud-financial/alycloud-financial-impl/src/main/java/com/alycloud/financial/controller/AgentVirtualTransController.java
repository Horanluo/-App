package com.alycloud.financial.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.PageRequestBean;
import com.alycloud.core.modules.PageResultBean;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.financial.api.IAgentVirtualTransService;
import com.alycloud.financial.mapper.AgentVirtualTransMapper;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentVirtualTrans;
import com.alycloud.modules.search.AgentVirtualTrans4Search;
import com.alycloud.modules.vo.AmountVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.ApiOperation;

/**
 * 代理商虚拟账户提现流水
 * @author Moyq5
 * @date 2017年10月24日
 */
@RequestMapping(value="/agentVirtualTrans")
@RestController
public class AgentVirtualTransController extends AbstractAgentController {

	@Autowired
	private IAgentVirtualTransService agentVirtualTransService;
	@Autowired
	private AgentVirtualTransMapper agentVirtualTransMapper;
	
	/**
	 * 生成提现流水号
	 * @author Moyq5
	 * @date 2017年10月24日
	 */
	@GetMapping(value = "/genRefno")
	@SystemControllerLog(description = "生成提现流水号")
	public ResultBean<String> genRefno() throws Exception {
		return RestBeanGenerator.genSuccessResult(agentVirtualTransService.genRefno());
	}


	/**
	 * @author Moyq5
	 * @date 2017年10月24日
	 */
	@PostMapping(value = "/add")
	@SystemControllerLog(description = "添加提现流水")
	public ResultBean<AgentVirtualTrans> add(@RequestBody RequestBean<AgentVirtualTrans> requestData) {
		agentVirtualTransMapper.add(requestData.getData());
		return RestBeanGenerator.genSuccessResult(requestData.getData());
	}
	

	/**
	 * 每日提现列表(需要授权)
	 * @author Moyq5
	 * @date 2017年10月25日
	 */
	@ApiOperation(notes = "调用 /list4Day方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "每日提现列表")
	@PostMapping(value = "/list4Day")
	@SystemControllerLog(description = "每日提现列表")
	public PageResultBean<List<AgentVirtualTrans>> list4Day(@RequestBody PageRequestBean<AgentVirtualTrans4Search> reqBody) throws Exception {
		AgentInfo agent = getAgent(reqBody);
		if(agent == null)
        {
            return RestBeanGenerator.genEmptyPageResult("每日提现数据为空。");
        }
		AgentVirtualTrans4Search trans4s = reqBody.getData();
		if (null == trans4s) {
			trans4s = new AgentVirtualTrans4Search();
		}
		trans4s.setAgentno(agent.getAgentno());
		Page<AgentVirtualTrans> page = PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());
		agentVirtualTransMapper.listByGroupByDate(trans4s);
		return RestBeanGenerator.genSuccessPageResult(page);
		
	}
	
	/**
	 * 提现明细列表(需要授权)
	 * @author Moyq5
	 * @date 2017年10月25日
	 */
	@ApiOperation(notes = "调用 /list4Detail方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "提现明细列表")
	@PostMapping(value = "/list4Detail")
	@SystemControllerLog(description = "提现明细列表")
	public PageResultBean<List<AgentVirtualTrans>> list4Detail(@RequestBody PageRequestBean<AgentVirtualTrans4Search> reqBody) throws Exception {
		AgentInfo agent = getAgent(reqBody);
		if(agent == null)
        {
            return RestBeanGenerator.genEmptyPageResult("提现明细数据为空。");
        }
		AgentVirtualTrans4Search trans4s = reqBody.getData();
		if (null == trans4s) {
			trans4s = new AgentVirtualTrans4Search();
		}
		trans4s.setAgentno(agent.getAgentno());
		Page<AgentVirtualTrans> page = PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());
		agentVirtualTransMapper.listByPage(trans4s);
		return RestBeanGenerator.genSuccessPageResult(page);
	}
	
	/**
	 * 统计提现金额(需要授权)
	 * @author Moyq5
	 * @date 2017年10月25日
	 */
	@ApiOperation(notes = "调用 /sumAmount方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "统计提现金额")
	@PostMapping(value = "/sumAmount")
	@SystemControllerLog(description = "统计提现金额")
	public ResultBean<AmountVO> sumAmount(@RequestBody RequestBean<AgentVirtualTrans4Search> reqBody) throws Exception {
		AgentInfo agent = getAgent(reqBody);
		AmountVO vo = null;
		if(agent == null)
        {
            return RestBeanGenerator.genSuccessResult(vo);
        }
		AgentVirtualTrans4Search trans4s = reqBody.getData();
		trans4s.setAgentno(agent.getAgentno());
		BigDecimal amount = agentVirtualTransMapper.sum(trans4s);
		vo = new AmountVO();
		vo.setAmount(amount.setScale(2).toPlainString());
		return RestBeanGenerator.genSuccessResult(vo);
	}
	
	@ApiOperation(notes = "调用 /getAgentVirtualTrans方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取分润提现待出款的订单")
	@PostMapping(value = "/getAgentVirtualTrans")
	@SystemControllerLog(description = "获取分润提现待出款的订单")
	public List<AgentVirtualTrans> getAgentVirtualTrans(@RequestBody AgentVirtualTrans agentVirtualTrans){
		AgentVirtualTrans4Search trans4s = new AgentVirtualTrans4Search();
		trans4s.setPayStatus(agentVirtualTrans.getPayStatus());
		
		return agentVirtualTransMapper.listByPage(trans4s);
	}
	
	@ApiOperation(notes = "调用 /modifyRecord方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "修改代付订单信息")
	@PostMapping(value = "/modifyRecord")
	@SystemControllerLog(description = "修改代付订单信息")
	public Integer modifyRecord(@RequestBody AgentVirtualTrans agentVirtualTrans){
		return agentVirtualTransMapper.modifyRecord(agentVirtualTrans);
	}
}
