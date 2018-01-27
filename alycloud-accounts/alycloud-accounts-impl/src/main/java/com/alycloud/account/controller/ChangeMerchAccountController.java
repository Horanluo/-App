package com.alycloud.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IChangeMerchAccountService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.ChangeMerchAccount;

import io.swagger.annotations.ApiOperation;

/**
 * 商户结算账户变更
 * @author Moyq5
 * @date 2017年10月23日
 */
@RequestMapping(value="/changeMerchAccount")
@RestController
public class ChangeMerchAccountController {

	@Autowired
	private IChangeMerchAccountService changeMerchAccountService;
	
	/**
	 * 添加商户结算账户变更记录(需要授权)
	 * @author Moyq5
	 * @date 2017年10月23日
	 */
	@ApiOperation(notes = "调用 /add方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "商户结算账户变更申请")
	@PostMapping(value = "/add")
	@SystemControllerLog(description = "商户结算账户变更申请")
	public ResultBean<?> getByMerchno(@RequestBody RequestBean<ChangeMerchAccount> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getAccountno())
				||StrUtil.isEmpty(reqData.getData().getAccountName())||StrUtil.isEmpty(reqData.getData().getBankno())
				||StrUtil.isEmpty(reqData.getData().getBankName())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		final String merchno = reqData.getMerchno();
		ChangeMerchAccount account = reqData.getData();
		changeMerchAccountService.addByMerchno(merchno, account);
        return RestBeanGenerator.genSuccessResult();
	}
	
}
