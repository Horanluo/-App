package com.alycloud.account.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IMerchUserService;
import com.alycloud.account.api.IRegistUserService;
import com.alycloud.account.feign.UtilFeign;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.core.utils.Base64Util;
import com.alycloud.core.utils.MD5;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.SmsUtil;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.entity.SmsCodeInfo;
import com.alycloud.modules.vo.RegistSmsCodeVO;
import com.alycloud.modules.vo.RegistUserVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/regist")
@Slf4j
public class RegistController {

	@Value("${platformKey}")
    private String key;
	@Autowired
	private IMerchUserService merchUserService;
	@Autowired
	private IRegistUserService registService;
	@Autowired
	private UtilFeign utilFeign;
	
	@ApiOperation(notes = "调用 /regist方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "账户注册")
	@CrossOrigin
	@PostMapping(value="/user")
	@SystemControllerLog(description = "App项目账户注册")
	public ResultBean<String> regist(@Validated @RequestBody RequestBean<RegistUserVO> reqData){
		//response.setHeader("Access-Control-Allow-Origin", "*");
		if(StrUtil.isEmpty(reqData.getData().getMobile())||StrUtil.isEmpty(reqData.getData().getPassword())
				||StrUtil.isEmpty(reqData.getData().getConfirmPassword())||StrUtil.isEmpty(reqData.getData().getSmsCode())
				||StrUtil.isEmpty(reqData.getData().getVerifyCode())||StrUtil.isEmpty(reqData.getData().getReferrer())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		
        log.info("手机号:"+reqData.getData().getMobile()+",密码:"+reqData.getData().getPassword()+
        		"确认密码:"+reqData.getData().getConfirmPassword()+"短信验证码:"+reqData.getData().getSmsCode()+
        		"短信校验码:"+reqData.getData().getVerifyCode()+"推荐人:"+
        		reqData.getData().getReferrer());
        
        MerchUser merchUser = merchUserService.queryMerchUser(reqData.getData().getMobile());
        if(!StrUtil.isEmpty(merchUser)){
        	return RestBeanGenerator.genErrorResult("账号已存在，请直接登录");
        }
        //二次密码是否匹配
        if(!reqData.getData().getPassword().equals(reqData.getData().getConfirmPassword())){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0005.getErrorCode(), ResCode.API_ERROE_CODE_0005.getErrorMes());
        }
        //密码格式校验
        if(!StrUtil.verifyPwd(reqData.getData().getPassword())){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0011.getErrorCode(), ResCode.API_ERROE_CODE_0011.getErrorMes());
        }
        //短信验证码校验
        Map<String, Boolean> validResult;
		try {
			validResult = SmsUtil.valid( reqData.getData().getMobile(), MD5.getMD5(reqData.getData().getSmsCode(), "GBK"),
					reqData.getData().getVerifyCode());
			if(!validResult.get("smsCode")){
	        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0012.getErrorCode(), ResCode.API_ERROE_CODE_0012.getErrorMes());
	        }
	        if(!validResult.get("smsValidTime")){
	        	return RestBeanGenerator.genErrorResult("短信已失效");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        //校验注册手机号是否已注册
        MerchUser mub = merchUserService.queryMerchUser(reqData.getData().getMobile());
        if(!StrUtil.isEmpty(mub)){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0013.getErrorCode(), ResCode.API_ERROE_CODE_0013.getErrorMes());
        }
        
        //校验推荐人是否存在
        MerchUser refer = merchUserService.queryMerchUser(reqData.getData().getReferrer());
        if(StrUtil.isEmpty(refer)){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0021.getErrorCode(), ResCode.API_ERROE_CODE_0021.getErrorMes());
        }
        
		if(!registService.RegistUser(reqData,refer)){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0014.getErrorCode(), ResCode.API_ERROE_CODE_0014.getErrorMes());
		}
		return RestBeanGenerator.genResult("1", null, "注册成功");
	}
	
	@ApiOperation(notes = "调用 /smsCode方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "发送注册短信验证码")
	@CrossOrigin
	@PostMapping(value="/smsCode")
	@SystemControllerLog(description = "发送注册短信验证码")
	public ResultBean<SmsCodeInfo> smsCode(@Validated @RequestBody RequestBean<RegistSmsCodeVO> reqData) throws Exception{
		//response.setHeader("Access-Control-Allow-Origin", "*");
		
		if(StrUtil.isEmpty(reqData.getData().getMobile())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		
		log.info("请求参数:"+reqData.getData().toString());
		SingleResult<String> singleResult = utilFeign.sendSms(reqData.getData().getMobile());
		if(!singleResult.isSuccess()){
			return RestBeanGenerator.genErrorResult(singleResult.getErrorCode(), singleResult.getErrorHint());
		};
		log.info("短信发送时间:"+System.currentTimeMillis());
		String verifyCode = MD5.getMD5(singleResult.getResult().toString(), "GBK")+"|"+System.currentTimeMillis();
		return RestBeanGenerator.genSuccessResult(new SmsCodeInfo(Base64Util.encode(verifyCode.getBytes())));
	}	
}
