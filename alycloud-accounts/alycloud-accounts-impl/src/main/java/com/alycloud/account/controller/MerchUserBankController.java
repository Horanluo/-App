package com.alycloud.account.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.account.mapper.MerchUserBankMapper;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.MerchUserBank;
import com.alycloud.modules.search.MerchUserBank4Search;
import com.alycloud.modules.search.SystemIC;
import io.swagger.annotations.ApiOperation;

/**
 * 商户银行卡列表信息
 * @author Moyq5
 * @date 2017年10月23日
 */
@RequestMapping(value="/merchUserBank")
@RestController
public class MerchUserBankController {

	@Autowired
	private MerchUserBankMapper merchUserBankMapper;
	
	/**
	 * 获取商户银行卡列表信息
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取商户银行卡列表信息")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "获取商户银行卡列表信息")
	public ResultBean<List<MerchUserBank>> listByPage(@RequestBody RequestBean<MerchUserBank4Search> reqBody) {
		MerchUserBank4Search bank4s = reqBody.getData();
		if (null == bank4s) {
			bank4s = new MerchUserBank4Search();
		}
		bank4s.setPage(1);
		bank4s.setPageSize(SystemIC.PAGE_SIZE_NAX);
		List<MerchUserBank> bankList = merchUserBankMapper.listByPage(bank4s);
		return RestBeanGenerator.genSuccessResult(bankList);
	}

}
