package com.alycloud.financial.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
import com.alycloud.core.utils.DateFormat;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.financial.api.IAgentTransService;
import com.alycloud.financial.feign.AgentVirtualCardFeign;
import com.alycloud.financial.models.AgentTransOverviewResult;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentTrans;
import com.alycloud.modules.entity.AgentVirtualCard;
import com.alycloud.modules.search.AgentTrans4Search;
import com.alycloud.modules.vo.AmountVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 代理商分润
 * @author Moyq5
 * @date 2017年10月22日
 */
@RestController
@RequestMapping("/agentTrans")
@Api(value = "代理商分润接口")
public class AgentTransController extends AbstractAgentController {
	
	@Autowired
	private IAgentTransService agentTransService;
	@Autowired
	private AgentVirtualCardFeign agentVirtualCardFeign;
	
	/**
	 * 分润概览(需要授权)
	 * @author Moyq5
	 * @date 2017年10月22日
	 */
	@ApiOperation(notes = "调用 /overview方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "分润概览")
	@PostMapping(value = "/overview")
	@SystemControllerLog(description = "分润概览")
	public ResultBean<AgentTransOverviewResult> overview(@RequestBody RequestBean<String> reqBody) throws Exception {
		AgentInfo agent = getAgent(reqBody);
		AgentTransOverviewResult overview = null;
		if(agent == null)
        {
            return RestBeanGenerator.genSuccessResult(overview);
        }
		
		AgentTrans4Search trans4s = new AgentTrans4Search();
		trans4s.setAgentno(agent.getAgentno());
		BigDecimal sumHistory = agentTransService.sum(trans4s);// 累计分润
		
		Calendar cal = Calendar.getInstance();
		trans4s.setStartDate(DateFormat.DATE.format(cal.getTime()));
		trans4s.setEndDate(DateFormat.DATE.format(cal.getTime()));
		BigDecimal sumToday = agentTransService.sum(trans4s);// 今天分润
		
		cal.add(Calendar.DATE, -1);
		trans4s.setStartDate(DateFormat.DATE.format(cal.getTime()));
		trans4s.setEndDate(DateFormat.DATE.format(cal.getTime()));
		BigDecimal sumYesterday = agentTransService.sum(trans4s);// 昨天分润
		
		RequestBean<String> cardFeignData = new RequestBean<String>();
		cardFeignData.setData(agent.getAgentno());
		ResultBean<AgentVirtualCard> cardFeignResult = agentVirtualCardFeign.getByAgentno(cardFeignData);
		AgentVirtualCard card = cardFeignResult.getData();
		
		overview = new AgentTransOverviewResult();
		overview.setDrawAmount(card.getAvailAmount().setScale(2).toPlainString());
		overview.setTodayAmount(sumToday.setScale(2).toPlainString());
		overview.setTotalAmount(sumHistory.setScale(2).toPlainString());
		overview.setYesterdayAmount(sumYesterday.setScale(2).toPlainString());
		
		return RestBeanGenerator.genSuccessResult(overview);
	}

	/**
	 * 分润明细列表(需要授权)
	 * @author Moyq5
	 * @date 2017年10月22日
	 */
	@ApiOperation(notes = "调用 /list4Detail方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "分润明细列表(需要授权)")
	@PostMapping(value = "/list4Detail")
	@SystemControllerLog(description = "分润明细列表(需要授权)")
	public PageResultBean<List<AgentTrans>> list4Detail(@RequestBody PageRequestBean<AgentTrans4Search> reqBody) throws Exception {
		AgentInfo agent = getAgent(reqBody);
		if(agent == null)
		{
		    return RestBeanGenerator.genEmptyPageResult("分润明细数据为空。");
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
	 * 每日分润列表(需要授权)
	 * @author Moyq5
	 * @date 2017年10月22日
	 */
	@ApiOperation(notes = "调用 /list4Day方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "每日分润列表(需要授权)")
	@PostMapping(value = "/list4Day")
	@SystemControllerLog(description = "每日分润列表(需要授权)")
	public PageResultBean<List<Map<String, Object>>> list4Day(@RequestBody PageRequestBean<AgentTrans4Search> reqBody) throws Exception {
		AgentInfo agent = getAgent(reqBody);
		if(agent == null)
        {
            return RestBeanGenerator.genEmptyPageResult("每日分润数据为空。");
        }
		AgentTrans4Search trans4s = reqBody.getData();
		if (null == trans4s) {
			trans4s = new AgentTrans4Search();
		}
		trans4s.setAgentno(agent.getAgentno());
		Page<Map<String, Object>> page = PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());
		List<Map<String, Object>> list = agentTransService.listDetail(trans4s);
		page.clear();page.addAll(list);
		return RestBeanGenerator.genSuccessPageResult(page);
	}
	

	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年11月5日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询分润明细列表")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "查询分润明细列表")
	public ResultBean<List<AgentTrans>> listByPage(@RequestBody RequestBean<AgentTrans4Search> reqBody) throws Exception {
		AgentTrans4Search trans4s = reqBody.getData();
		if (null == trans4s) {
			trans4s = new AgentTrans4Search();
		}
		List<AgentTrans> transList = agentTransService.listByPage(trans4s);
		return RestBeanGenerator.genSuccessResult(transList);
	}
	
	/**
	 * 查询记录数
	 * @author Moyq5
	 * @date 2017年11月7日
	 */
	@ApiOperation(notes = "调用 /count方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询分润明细记录数")
	@PostMapping(value = "/count")
	@SystemControllerLog(description = "查询分润明细记录数")
	public ResultBean<Integer> count(@RequestBody RequestBean<AgentTrans4Search> reqBody) throws Exception {
		AgentTrans4Search trans4s = reqBody.getData();
		if (null == trans4s) {
			trans4s = new AgentTrans4Search();
		}
		Integer transList = agentTransService.countRecord(trans4s);
		return RestBeanGenerator.genSuccessResult(transList);
	}
	
	/**
	 * 统计分润金额(需要授权)
	 * @author Moyq5
	 * @date 2017年10月25日
	 */
	@ApiOperation(notes = "调用 /sumAmount方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "统计分润金额")
	@PostMapping(value = "/sumAmount")
	@SystemControllerLog(description = "统计分润金额")
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
	
	/**
	 * 升级费分润
	 * @author Moyq5
	 * @date 2017年11月4日
	 */
	@ApiOperation(notes = "调用 /addByGradeOrderId方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "升级费分润")
	@PostMapping(value = "/addByGradeOrderId")
	@SystemControllerLog(description = "升级费分润")
	public ResultBean<?> addByGradeOrderId(@RequestBody RequestBean<Integer> reqBody) throws Exception {
		agentTransService.addByGradeOrderId(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}
	
	/**
	 * 二维码交易分润
	 * @author Moyq5
	 * @date 2017年11月6日
	 */
	@ApiOperation(notes = "调用 /addByQrcodeOrderno方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "二维码交易分润")
	@PostMapping(value = "/addByQrcodeOrderno")
	@SystemControllerLog(description = "二维码交易分润")
	public ResultBean<?> addByQrcodeOrderno(@RequestBody RequestBean<String> reqBody) throws Exception {
		agentTransService.addByQrcodeOrderno(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}

	/**
	 * 快捷交易分润
	 * @author Moyq5
	 * @date 2017年11月6日
	 */
	@ApiOperation(notes = "调用 /addByFastOrderno方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "快捷交易分润")
	@PostMapping(value = "/addByFastOrderno")
	@SystemControllerLog(description = "快捷交易分润")
	public ResultBean<?> addByFastOrderno(@RequestBody RequestBean<String> reqBody) throws Exception {
		agentTransService.addByFastOrderno(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}
	
}
