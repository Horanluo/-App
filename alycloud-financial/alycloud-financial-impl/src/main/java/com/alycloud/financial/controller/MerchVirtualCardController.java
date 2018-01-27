package com.alycloud.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.financial.api.IMerchVirtualCardService;
import com.alycloud.financial.feign.MerchInfoFeign;
import com.alycloud.financial.models.DrawResultVo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.modules.vo.AmountVO;

import io.swagger.annotations.ApiOperation;

/**
 * 商户虚拟账户
 * @author Moyq5
 * @date 2017年11月13日
 */
@RequestMapping(value="/merchVirtualCard")
@RestController
public class MerchVirtualCardController {

	@Autowired
	private IMerchVirtualCardService merchVirtualCardService;
	@Autowired
	private MerchInfoFeign merchInfoFeign;
	

	/**
	 * 创建虚拟账户
	 * @author Moyq5
	 * @date 2017年11月13日
	 */
	@ApiOperation(notes = "调用 /add4Merch方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "创建商户虚拟账户")
	@PostMapping(value = "/add4Merch")
	@SystemControllerLog(description = "创建商户虚拟账户")
	public ResultBean<MerchVirtualCard> add4Merch(@RequestBody RequestBean<String> reqData) throws Exception{
		MerchVirtualCard card = merchVirtualCardService.add4Merch(reqData.getData());
		return RestBeanGenerator.genSuccessResult(card);
	}
	
	
	/**
	 * 提现(需要授权)
	 * @author Moyq5
	 * @date 2017年11月13日
	 */
	@ApiOperation(notes = "调用 /draw方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "商户提现")
	@PostMapping(value = "/draw")
	@SystemControllerLog(description = "商户提现")
	public ResultBean<DrawResultVo> draw(@RequestBody RequestBean<AmountVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getAmount())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		String merchno = reqData.getMerchno();
		RequestBean<String> merchData = new RequestBean<String>();
		merchData.setData(merchno);
		MerchInfo merch = merchInfoFeign.getByMerchno(merchData).getData();
		String amount = reqData.getData().getAmount();
		MerchVirtualTrans trans = merchVirtualCardService.draw(merch, amount);
		
		DrawResultVo data = new DrawResultVo();
		data.setAccountName(trans.getAccountName());
		data.setAccountno(trans.getAccountno());
		data.setMobile(merch.getMobile());
		data.setPayAmount(trans.getAmount().toPlainString());
		data.setTransAmount(trans.getTransAmount().toPlainString());
		data.setTransFee(trans.getTransFee().toPlainString());
		data.setTransTime(trans.getAddTime());
		
		return RestBeanGenerator.genSuccessResult(data);
	}
	
}
