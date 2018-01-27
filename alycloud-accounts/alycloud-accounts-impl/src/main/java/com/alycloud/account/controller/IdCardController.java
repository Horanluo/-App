package com.alycloud.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.account.api.IChangeMerchInfoService;
import com.alycloud.account.api.IMerchService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.vo.IdentifyAuthenVO;
import com.alycloud.modules.vo.LivingIdentifyVO;
import com.alycloud.modules.vo.UploadVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 身份实名认证接口
 * @author 罗根恒
 * @date 2017年10月24日
 */
@RequestMapping(value="/identity")
@RestController
@Slf4j
public class IdCardController {

	@Value("${sign.key}")
    private String SIGN_KEY;
	@Autowired
	private IMerchService merchService;
	@Autowired
	private IChangeMerchInfoService changeMerchInfoService;
	@Value("${img.localpath}")
    private String imgLocalpath;
	
	/**
	 * 实名验证接口
	 * @author 罗根恒
	 * @date 2017年10月24日
	 */
//	@ApiOperation(notes = "调用 /idCardVerify方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "身份实名验证")
//	@PostMapping(value="/idCardVerify")
//	@SystemControllerLog(description = "App项目实名验证")
//	public ResultBean<JSONObject> idCardVerify(@RequestBody RequestBean<IdCardVerifyVO> reqData) throws Exception{
//		JSONObject resJson = new JSONObject();
//		log.info("请求参数:"+reqData.getData().toString());
//        resJson = identifyAuthenService.idCardVerify(reqData.getData().getVerifyType(), reqData.getData().getIdNo(), 
//        		reqData.getData().getIdName(), reqData.getData().getExtensionInfo());
//        log.info(JSONObject.parseObject(resJson.get("result").toString()).get("success").toString());
//        if("false".equals(JSONObject.parseObject(resJson.get("result").toString()).get("success"))){
//        	return RestBeanGenerator.genErrorResult(ResCode.ID_CARD_IDENTIFY_FAIL.getErrorCode(), 
//        			JSONObject.parseObject(resJson.get("result").toString()).get("message").toString());
//        }else{
//        	return RestBeanGenerator.genSuccessResult(JSONObject.parseObject(resJson.get("data").toString()));
//        }
//	}
	
	@ApiOperation(notes = "调用 /uploadFile方法",produces = MediaType.APPLICATION_JSON_VALUE, value = "上传手持证件照")
	@PostMapping(value="/uploadFile")
	//@SystemControllerLog(description = "App项目上传手持证件照")
	public ResultBean<String> uploadFile(@RequestBody RequestBean<UploadVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getBase64Img())
				||StrUtil.isEmpty(reqData.getData().getIsIdImg())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		log.info("上传手持证件照"+reqData.getMerchno()+","+reqData.getData().getBase64Img()+","+reqData.getData().getIsIdImg());
		SingleResult<String> singleResult = changeMerchInfoService.updateToAuditInfo(reqData.getData().getIsIdImg(), reqData.getData().getBase64Img(),
				imgLocalpath, reqData.getMerchno());
		return singleResult.isSuccess()?RestBeanGenerator.genResult("1", null, singleResult.getErrorHint()):RestBeanGenerator.genErrorResult(singleResult.getErrorCode(), singleResult.getErrorHint());
	}
	
	/**
	 * 保存身份认证信息接口
	 * @author 罗根恒
	 * @date 2017年10月24日
	 */
	@ApiOperation(notes = "调用 /IdentifyAuthen方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "身份认证")
	@PostMapping(value="/IdentifyAuthen")
	@SystemControllerLog(description = "App项目保存身份验证身份信息")
	public ResultBean<String> IdentifyAuthen(@RequestBody RequestBean<IdentifyAuthenVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getIdNo())
				||StrUtil.isEmpty(reqData.getData().getIdName())||StrUtil.isEmpty(reqData.getData().getIdValiTime())
				||StrUtil.isEmpty(reqData.getData().getFrontUrl())||StrUtil.isEmpty(reqData.getData().getBackUrl())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		log.info("请求参数:"+reqData.getData().toString());
		SingleResult<String> singleResult = merchService.addChangeMerchInfo(reqData);
		if(singleResult.isSuccess()){
			return RestBeanGenerator.genResult("1", null, singleResult.getErrorHint());
		}
		return RestBeanGenerator.genErrorResult(singleResult.getErrorCode(), singleResult.getErrorHint());
	}
	
	/**
	 * 保存活体识别信息接口
	 * @author 罗根恒
	 * @date 2017年10月24日
	 */
	@ApiOperation(notes = "调用 /livingIdentify方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "活体识别")
	@PostMapping(value="/livingIdentify")
	@SystemControllerLog(description = "App项目保存活体识别信息")
	public ResultBean<String> livingIdentify(@RequestBody RequestBean<LivingIdentifyVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getLivingPhotoUrl())
				||StrUtil.isEmpty(reqData.getData().getVideoUrl())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		log.info("请求参数:"+reqData.getData().toString());
		SingleResult<String> singleResult = merchService.updateChangeMerchInfo(reqData);
		if(singleResult.isSuccess()){
			return RestBeanGenerator.genResult("1", null, "活体识别成功");
		}
		return RestBeanGenerator.genErrorResult(singleResult.getErrorCode(), singleResult.getErrorHint());
	}
}
