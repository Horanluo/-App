package com.alycloud.account.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.account.api.IChannelSubMerchInfoService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.ReportMark;
import com.alycloud.modules.vo.MerchFeeVO;
import com.alycloud.modules.vo.ModifyMerchFeeVO;
import com.alycloud.modules.vo.ReportMerchFeeVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 费率管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value="/fee")
@Slf4j
public class FeeController {

	@Value("${platformKey}")
    private String key;
	@Value("${page.flag}")
    private boolean pageFlag;
	@Autowired
	private IChannelSubMerchInfoService channelSubMerchInfoService;
	
	@ApiOperation(notes = "调用 /feeList方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取商户费率列表")
	@PostMapping(value="/feeList")
	@SystemControllerLog(description = "App项目商户费率")
	public ResultBean<List<ChannelSubmerchInfo>> feeList(@Validated @RequestBody RequestBean<MerchFeeVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getPayType())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		log.info("请求参数:"+reqData.getData().toString());
		List<ChannelSubmerchInfo> channelMerchInfoList = channelSubMerchInfoService.getChannelMerchInfoList
				(reqData.getMerchno(), reqData.getData().getPayType());
		
		return RestBeanGenerator.genSuccessResult(channelMerchInfoList);
	}
	
	@ApiOperation(notes = "调用 /modifyfee方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "修改商户费率")
	@PostMapping(value="/modifyfee")
	@SystemControllerLog(description = "App项目修改商户费率")
	public ResultBean<String> modifyfee(@Validated @RequestBody RequestBean<ModifyMerchFeeVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getChannelCode())
				||StrUtil.isEmpty(reqData.getData().getModifyRate())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		log.info("请求参数:"+reqData.getData().toString());
		SingleResult<String> singleResult;
		if("YUFU".equals(reqData.getData().getChannelCode())&&reqData.getData().getModifyRate().compareTo(new BigDecimal(0.0038))<0){
			return RestBeanGenerator.genErrorResult("0", "商户使用御付的费率最低不能低于0.38%");
		}
		singleResult = channelSubMerchInfoService.modifyMerchFee(reqData.getMerchno(),reqData.getData().getModifyRate(),
				reqData.getData().getChannelCode());
		return singleResult.isSuccess() ? RestBeanGenerator.genResult("1", null, "修改商户费率成功"):
			RestBeanGenerator.genErrorResult(singleResult.getErrorCode(), singleResult.getErrorHint());
	}
	
	@ApiOperation(notes = "调用 /reportMerchFee方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "上报渠道新入驻商户的费率")
	@PostMapping(value="/reportMerchFee")
	@SystemControllerLog(description = "App项目上报渠道新入驻商户的费率")
	public ResultBean<ReportMark> reportMerchFee(@Validated @RequestBody RequestBean<ReportMerchFeeVO> reqData) throws Exception{
		log.info("请求参数:"+reqData.getData().toString());
		SingleResult<String> singleResult = channelSubMerchInfoService.reportMerchFee(reqData.getMerchno(), null,
				reqData.getData().getChannelCode());
		ReportMark rm = new ReportMark();
		if("YUFU".equals(reqData.getData().getChannelCode())){
			rm.setReportMark("0");
		}
		if("HXTC".equals(reqData.getData().getChannelCode())){
			rm.setReportMark("1");
		}
		if("HXTC_JF".equals(reqData.getData().getChannelCode())){
			rm.setReportMark("1");
		}
		return singleResult.isSuccess() ? RestBeanGenerator.genResult("1", rm, "上报渠道新入驻商户的费率成功"):
			RestBeanGenerator.genErrorResult(singleResult.getErrorCode(), singleResult.getErrorHint());
	}
}
