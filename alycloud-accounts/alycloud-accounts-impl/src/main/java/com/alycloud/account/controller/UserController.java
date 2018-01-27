package com.alycloud.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IMerchService;
import com.alycloud.account.api.IMerchUserService;
import com.alycloud.account.mapper.MerchUserMapper;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.MD5;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.SmsUtil;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.enums.MerchUserRank;
import com.alycloud.modules.search.MerchUser4Search;
import com.alycloud.modules.vo.CountInviteeResultVO;
import com.alycloud.modules.vo.ForgetUserPwdVO;
import com.alycloud.modules.vo.ModifySettleCardVO;
import com.alycloud.modules.vo.ModifyUserMobileVO;
import com.alycloud.modules.vo.ModifyUserPwdVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="user")
@Slf4j
public class UserController {

	@Value("${platformKey}")
    private String key;
	
	@Autowired
	private IMerchUserService merchUserService;
	@Autowired
	private MerchUserMapper merchUserMapper;
	@Autowired
	private IMerchService merchService;
	
	@ApiOperation(notes = "调用 /userInfo方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "个人信息查看")
	@PostMapping(value="/info")
	@SystemControllerLog(description = "App项目用户信息查询")
	public ResultBean<MerchUser> userInfo(@Validated @RequestBody RequestBean<String> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getLoginName())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		log.info("请求参数:"+reqData);
        MerchUser merchUser = merchUserService.queryMerchUser(reqData.getLoginName());
		if(StrUtil.isEmpty(merchUser)){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0007.getErrorCode(), 
					ResCode.API_ERROE_CODE_0007.getErrorMes());
		}
		Map<String,String> userParams = new HashMap<String,String>();
		userParams.put("id", merchUser.getReferName());
        merchUser.setReferName(merchUserMapper.queryMerchUser(userParams).getTrueName());
        merchUser.setPassword("");
        switch (merchUser.getUserRank()) {
		case 1:
			merchUser.setUserRankName(MerchUserRank.STAFF_MEMBER.getName());
			break;
		case 2:
			merchUser.setUserRankName(MerchUserRank.STORE_MANAGER.getName());
			break;
		case 3:
			merchUser.setUserRankName(MerchUserRank.THE_BOSS.getName());
			break;
		case 4:
			merchUser.setUserRankName(MerchUserRank.GENERAL_AGENT.getName());
			break;
		case 5:
			merchUser.setUserRankName(MerchUserRank.SENIOR_AGENT.getName());
			break;
		case 6:
			merchUser.setUserRankName(MerchUserRank.THE_PARTNER.getName());
			break;
		default:
			break;
		}
		return RestBeanGenerator.genSuccessResult(merchUser);
	}
	

	/**
	 * 获取用户列表信息
	 * @author Moyq5
	 * @date 2017年10月30日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取用户列表")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "获取用户列表")
	public ResultBean<List<MerchUser>> listByPage(@RequestBody RequestBean<MerchUser4Search> reqBody) {
		List<MerchUser> userList = merchUserService.listByPage(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(userList);
	}

	/**
	 * 更新用户信息
	 * @author Moyq5
	 * @date 2017年10月31日
	 */
	@ApiOperation(notes = "调用 /mod方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "更新用户信息")
	@PostMapping(value = "/mod")
	@SystemControllerLog(description = "更新用户信息")
	public ResultBean<?> mod(@RequestBody RequestBean<MerchUser> reqBody) {
		merchUserService.mod(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}

	/**
	 * 统计记录数
	 * @author Moyq5
	 * @date 2017年11月4日
	 */
	@ApiOperation(notes = "调用 /count方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "统计用户记录数")
	@PostMapping(value = "/count")
	@SystemControllerLog(description = "统计用户记录数")
	public ResultBean<Integer> count(@RequestBody RequestBean<MerchUser4Search> reqBody) {
		Integer count = merchUserService.count(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(count);
	}

	/**
	 * 修改密码
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年11月16日
	 */
	@ApiOperation(notes = "调用 /modifyPwd方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "修改密码")
	@PostMapping(value = "/modifyPwd")
	@SystemControllerLog(description = "修改密码")
	public ResultBean<Integer> modifyPwd(@RequestBody RequestBean<ModifyUserPwdVO> reqData) 
			throws Exception {
		if(StrUtil.isEmpty(reqData.getLoginName())||StrUtil.isEmpty(reqData.getData().getPassword())
				||StrUtil.isEmpty(reqData.getData().getConfirmPassword())||StrUtil.isEmpty(reqData.getData().getSmsCode())
				||StrUtil.isEmpty(reqData.getData().getVerifyCode())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
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
        Map<String,Boolean> validResult = SmsUtil.valid(reqData.getLoginName(), MD5.getMD5(reqData.getData().getSmsCode(), "GBK"),
        		reqData.getData().getVerifyCode());
        if(!validResult.get("smsCode")){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0012.getErrorCode(), ResCode.API_ERROE_CODE_0012.getErrorMes());
        }
        if(!validResult.get("smsValidTime")){
        	return RestBeanGenerator.genErrorResult("短信已失效");
        }
		return merchUserService.modifyUserPwd(reqData.getLoginName(), reqData.getData().getPassword())>0?
				RestBeanGenerator.genSuccessResult(1):RestBeanGenerator.genErrorResult(0, "修改失败");
	}
	
	/**
	 * 修改手机号
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年11月16日
	 */
	@ApiOperation(notes = "调用 /modifyMobile方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "更换登录手机号")
	@PostMapping(value = "/modifyMobile")
	@SystemControllerLog(description = "更换登录手机号")
	public ResultBean<Integer> modifyMobile(@RequestBody RequestBean<ModifyUserMobileVO> reqData) 
			throws Exception {
		if(StrUtil.isEmpty(reqData.getLoginName())||StrUtil.isEmpty(reqData.getData().getNewMobile())
				||StrUtil.isEmpty(reqData.getData().getSmsCode())||StrUtil.isEmpty(reqData.getData().getVerifyCode())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
        //短信验证码校验
		Map<String,Boolean> validResult = SmsUtil.valid(reqData.getData().getNewMobile(), MD5.getMD5(reqData.getData().getSmsCode(), "GBK"),
        		reqData.getData().getVerifyCode());
        if(!validResult.get("smsCode")){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0012.getErrorCode(), ResCode.API_ERROE_CODE_0012.getErrorMes());
        }
        if(!validResult.get("smsValidTime")){
        	return RestBeanGenerator.genErrorResult("短信已失效");
        }
		return merchUserService.modifyUserMobile(reqData.getLoginName(),reqData.getData().getNewMobile())>0?
				RestBeanGenerator.genSuccessResult(1):RestBeanGenerator.genErrorResult(0, "修改失败");
	}
	
	
	/**
	 * 按等级分组统计我的商户数（需要授权）
	 * @author Moyq5
	 * @date 2017年11月17日
	 */
	@ApiOperation(notes = "调用 /countInvitee方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "按等级分组统计我的商户数")
	@PostMapping(value = "/countInvitee")
	@SystemControllerLog(description = "按等级分组统计我的商户数")
	public ResultBean<CountInviteeResultVO> countInvitee(@RequestBody RequestBean<String> reqBody) {
		if(StrUtil.isEmpty(reqBody.getMerchno())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		List<Map<String, Object>> grades = merchUserService.countGradeInviteeByMerchno(reqBody.getMerchno());
		Integer totalCount = merchUserService.countInviteeByMerchno(reqBody.getMerchno());
		CountInviteeResultVO vo = new CountInviteeResultVO();
		vo.setGrades(grades);
		vo.setTotalCount(totalCount);
		return RestBeanGenerator.genSuccessResult(vo);
	}
	
	/**
	 * 忘记密码
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年11月29日
	 */
	@ApiOperation(notes = "调用 /forgetPwd方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "忘记密码")
	@PostMapping(value = "/forgetPwd")
	@SystemControllerLog(description = "忘记密码")
	public ResultBean<Integer> forgetPwd(@RequestBody RequestBean<ForgetUserPwdVO> reqData) 
			throws Exception {
		//二次密码是否匹配
        if(!reqData.getData().getPassword().equals(reqData.getData().getConfirmPassword())){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0005.getErrorCode(), ResCode.API_ERROE_CODE_0005.getErrorMes());
        }
        //密码格式校验
        if(!StrUtil.verifyPwd(reqData.getData().getPassword())){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0011.getErrorCode(), ResCode.API_ERROE_CODE_0011.getErrorMes());
        }
        //短信验证码校验
        Map<String,Boolean> validResult = SmsUtil.valid(reqData.getLoginName(), MD5.getMD5(reqData.getData().getSmsCode(), "GBK"),
        		reqData.getData().getVerifyCode());
        if(!validResult.get("smsCode")){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0012.getErrorCode(), ResCode.API_ERROE_CODE_0012.getErrorMes());
        }
        if(!validResult.get("smsValidTime")){
        	return RestBeanGenerator.genErrorResult("短信已失效");
        }
		return merchUserService.modifyUserPwd(reqData.getLoginName(), reqData.getData().getPassword())>0?
				RestBeanGenerator.genSuccessResult(1):RestBeanGenerator.genErrorResult(0, "修改失败");
	}
	
	/**
	 * 更改结算卡
	 * @author Horanluo
	 * @date 2017年12月18日
	 */
	@ApiOperation(notes = "调用 /modifySettleCard方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "更改结算卡")
	@PostMapping(value = "/modifySettleCard")
	@SystemControllerLog(description = "更改结算卡")
	public ResultBean<?> getByMerchno(@RequestBody RequestBean<ModifySettleCardVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getCardNo())
				||StrUtil.isEmpty(reqData.getData().getSmsCode())||StrUtil.isEmpty(reqData.getData().getVerifyCode())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		//短信验证码校验
        Map<String,Boolean> validResult = SmsUtil.valid(reqData.getLoginName(), MD5.getMD5(reqData.getData().getSmsCode(), "GBK"),
        		reqData.getData().getVerifyCode());
        if(!validResult.get("smsCode")){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0012.getErrorCode(), ResCode.API_ERROE_CODE_0012.getErrorMes());
        }
        if(!validResult.get("smsValidTime")){
        	return RestBeanGenerator.genErrorResult("短信已失效");
        }
        MerchInfo merchInfo = merchService.getByMerchno(reqData.getMerchno());
        merchInfo.setAccountno(reqData.getData().getCardNo());
        merchService.mod(merchInfo);
        return RestBeanGenerator.genResult("1", null, "更换成功");
	}
}
