package com.alycloud.account.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.account.api.ICardService;
import com.alycloud.account.api.IMerchUserBankService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.PageRequestBean;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.MerchUserBank;
import com.alycloud.modules.vo.CardVO;
import com.alycloud.modules.vo.SaveCardVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 银行卡管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value="card")
@Slf4j
public class CardController {

	@Value("${platformKey}")
    private String key;
	@Value("${card.authKey}")
    private String cardAuthKey;
	@Value("${card.authService}")
    private String cardAuthService;
	@Autowired
	private ICardService cardService;
	@Autowired
	private IMerchUserBankService merchUserBankService;
//	@Autowired
//	private UtilFeign utilFeign;
	
	Logger logger = LoggerFactory.getLogger(CardController.class);
	
	@ApiOperation(notes = "调用 /cardList方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "绑定银行卡列表")
	@PostMapping(value="/list")
	@SystemControllerLog(description = "App项目绑定银行卡列表")
	public ResultBean<List<MerchUserBank>> cardList(@Validated @RequestBody PageRequestBean<String> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getLoginName())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		Page<MerchUserBank> page =PageHelper.startPage(reqData.getPageNum(), reqData.getPageSize());
		List<MerchUserBank> userBankList = merchUserBankService.queryUserBankList(reqData.getLoginName());
		if(StrUtil.isEmpty(userBankList) || userBankList.size()<1){
			return RestBeanGenerator.genResult("99", userBankList, null);
		}
		return RestBeanGenerator.genSuccessPageResult(page);
	}
	
	@ApiOperation(notes = "调用 /cardAdd方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "绑定银行卡")
	@PostMapping(value="/add")
	@SystemControllerLog(description = "App项目新增银行卡")
	@Transactional
	public ResultBean<String> cardAdd(@Validated @RequestBody RequestBean<CardVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getCardNo())
				||StrUtil.isEmpty(reqData.getData().getCardName())||StrUtil.isEmpty(reqData.getData().getCardNo())
				||StrUtil.isEmpty(reqData.getData().getPhoneNo())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		log.info("请求参数:"+reqData.getData().toString());
//		String cardInfo = utilFeign.getCardDetail(reqData.getData().getCardNo());
//		JSONObject cardInfoJson = JSONObject.parseObject(cardInfo);
//		if(!cardInfoJson.getBoolean("validated")){
//			return RestBeanGenerator.genErrorResult(JSONObject.parseObject(cardInfoJson.getJSONArray("messages").get(0).
//					toString()).getString("errorCodes"));
//		}
		SingleResult<String> singleResult;
		if(StrUtil.isEmpty(reqData.getData().getId())){
			singleResult = merchUserBankService.addMerchUserBank(reqData);
		}else{
			singleResult = merchUserBankService.modifyMerchUserBank(reqData);
		}
		if(!singleResult.isSuccess()){
			return RestBeanGenerator.genErrorResult(singleResult.getErrorHint());
		}
		return RestBeanGenerator.genResult("1", null, singleResult.getErrorHint());
	}
	
	@ApiOperation(notes = "调用 /bankCardCertify方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "结算卡提交待认证")
	@PostMapping(value="/bankCardCertify")
	@SystemControllerLog(description = "App项目结算卡提交待认证")
	public ResultBean<String> bankCardCertify(@Validated @RequestBody RequestBean<SaveCardVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getCardNo())
				||StrUtil.isEmpty(reqData.getData().getCardName())||StrUtil.isEmpty(reqData.getData().getIdNo())
				||StrUtil.isEmpty(reqData.getData().getPhoneNo())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		log.info("请求参数:"+reqData.getData().toString());
		SingleResult<String> singleResult = cardService.addChangeMerchAccount(reqData);
		
        if(singleResult.isSuccess()){
        	return RestBeanGenerator.genResult("1", null,singleResult.getErrorHint());
        }
        return RestBeanGenerator.genErrorResult(singleResult.getErrorCode(), singleResult.getErrorHint());
	}
}
