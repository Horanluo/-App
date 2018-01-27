package com.alycloud.financial.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.alycloud.financial.api.IAgentTransService;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentTrans;
import com.alycloud.modules.search.AgentTrans4Search;
import com.alycloud.modules.vo.AmountVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 我的账单
 * @author Moyq5
 * @date 2017年11月19日
 */
@RestController
@RequestMapping("/myBill")
@Api(value = "代理商分润接口")
public class MyBillController extends AbstractAgentController {
	
	@Autowired
	private IAgentTransService agentTransService;
	
	/**
	 * 每日账单列表(需要授权)
	 * @author Moyq5
	 * @date 2017年10月22日
	 */
	@ApiOperation(notes = "调用 /list4Day方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "每日账单列表")
	@PostMapping(value = "/list4Day")
	@SystemControllerLog(description = "每日账单列表")
	public PageResultBean<List<AgentTrans>> list4Day(@RequestBody PageRequestBean<AgentTrans4Search> reqBody) throws Exception {
		AgentInfo agent = getAgent(reqBody);
		if(agent == null)
        {
            return RestBeanGenerator.genEmptyPageResult("每日账单数据为空。");
        }
		AgentTrans4Search trans4s = reqBody.getData();
		if (null == trans4s) {
			trans4s = new AgentTrans4Search();
		}
		trans4s.setAgentno(agent.getAgentno());
		Page<AgentTrans> page = PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());
		agentTransService.listByGroupByDate(trans4s);
		return RestBeanGenerator.genSuccessPageResult(page);
	}
	
	
	/**
	 * 账单明细列表（需要授权）
	 * @author Moyq5
	 * @date 2017年11月5日
	 */
	@ApiOperation(notes = "调用 /list4Detail方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "账单明细列表")
	@PostMapping(value = "/list4Detail")
	@SystemControllerLog(description = "账单明细列表")
	public PageResultBean<List<AgentTrans>> list4Detail(@RequestBody PageRequestBean<AgentTrans4Search> reqBody) throws Exception {
		AgentInfo agent = getAgent(reqBody);
		if(agent == null)
        {
            return RestBeanGenerator.genEmptyPageResult("账单明细数据为空。");
        }
		AgentTrans4Search trans4s = reqBody.getData();
		if (null == trans4s) {
			trans4s = new AgentTrans4Search();
		}
		trans4s.setAgentno(agent.getAgentno());
		Page<AgentTrans> page = PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());
		agentTransService.listByPage(trans4s);
		return RestBeanGenerator.genSuccessPageResult(page);
	}
	
	/**
	 * 统计分润金额(需要授权)
	 * @author Moyq5
	 * @date 2017年10月25日
	 */
	@ApiOperation(notes = "调用 /sumAmount方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "统计账单金额")
	@PostMapping(value = "/sumAmount")
	@SystemControllerLog(description = "统计账单金额")
	public ResultBean<AmountVO> sumAmount(@RequestBody RequestBean<AgentTrans4Search> reqBody) throws Exception {
		AgentInfo agent = getAgent(reqBody);
		AmountVO vo = null;
        if(agent == null)
        {
            return RestBeanGenerator.genSuccessResult(vo);
        }
		AgentTrans4Search trans4s = reqBody.getData();
		trans4s.setAgentno(agent.getAgentno());
		BigDecimal amount = agentTransService.sum(trans4s);
		vo = new AmountVO();
		vo.setAmount(amount.setScale(2).toPlainString());
		return RestBeanGenerator.genSuccessResult(vo);
	}
	
}
