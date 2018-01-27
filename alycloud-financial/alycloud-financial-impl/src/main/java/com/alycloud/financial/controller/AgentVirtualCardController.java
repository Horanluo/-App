package com.alycloud.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.financial.api.IAgentVirtualCardService;
import com.alycloud.financial.models.DrawResultVo;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentVirtualCard;
import com.alycloud.modules.search.AgentTrans4Search;
import com.alycloud.modules.vo.AmountVO;
import io.swagger.annotations.ApiOperation;

/**
 * 代理商虚拟账户
 * @author Moyq5
 * @date 2017年10月22日
 */
@RequestMapping(value="/agentVirtualCard")
@RestController
public class AgentVirtualCardController extends AbstractAgentController {

	@Autowired
	private IAgentVirtualCardService agentVirtualCardService;
	
	/**
	 * 创建代理商虚拟账户
	 * @author Moyq5
	 * @date 2017年11月6日
	 */
	@ApiOperation(notes = "调用 /add4Agent方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "创建代理商虚拟账户")
	@PostMapping(value = "/add4Agent")
	@SystemControllerLog(description = "创建代理商虚拟账户")
	public ResultBean<AgentVirtualCard> add4Agent(@RequestBody RequestBean<String> reqData) throws Exception{
		AgentVirtualCard card = agentVirtualCardService.add4Agent(reqData.getData());
		return RestBeanGenerator.genSuccessResult(card);
	}
	
	/**
	 * 根据代理商虚拟账户
	 * @author Moyq5
	 * @date 2017年10月22日
	 */
	@RequestMapping(value="/getByAgentno")
	public ResultBean<AgentVirtualCard> getByAgentno(@RequestBody RequestBean<String> reqData) throws Exception{
		AgentVirtualCard card = agentVirtualCardService.getByAgentno(reqData.getData());
        return RestBeanGenerator.genSuccessResult(card);
	}
	

	/**
	 * 提现(需要授权)
	 * @author Moyq5
	 * @date 2017年10月24日
	 */
	@ApiOperation(notes = "调用 /draw方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "分润提现")
	@PostMapping(value = "/draw")
	@SystemControllerLog(description = "分润提现")
	public ResultBean<DrawResultVo> draw(@RequestBody RequestBean<AmountVO> reqData) throws Exception{
		AgentInfo agent = getAgent(reqData);
		DrawResultVo data = null;
		if(agent == null)
        {
            return RestBeanGenerator.genSuccessResult(data);
        }
		String amount = reqData.getData().getAmount();
		SingleResult<String> singleResult = agentVirtualCardService.draw(agent, amount);
		
		if(!singleResult.isSuccess()){
			return RestBeanGenerator.genErrorResult(singleResult.getResult());
		}
		
		return RestBeanGenerator.genResult("1", null, singleResult.getResult());
	}
	
	/**
	 * 根据分润流水充值
	 * @author Moyq5
	 * @date 2017年11月4日
	 */
	@ApiOperation(notes = "调用 /rechargeByAgentTrans方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "根据分润流水充值")
	@PostMapping(value = "/rechargeByAgentTrans")
	@SystemControllerLog(description = "根据分润流水充值")
	public ResultBean<?> rechargeByAgentTrans(@RequestBody RequestBean<AgentTrans4Search> reqData) throws Exception{
		agentVirtualCardService.recharge(reqData.getData());
		return RestBeanGenerator.genSuccessResult();
	}
}
