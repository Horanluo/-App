package com.alycloud.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.account.api.IMerchService;
import com.alycloud.account.api.IMerchUserBankService;
import com.alycloud.account.api.IMerchUserService;
import com.alycloud.account.mapper.MerchUserMapper;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.MD5;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.entity.MerchUserBank;
import com.alycloud.modules.enums.MerchUserRank;
import com.alycloud.modules.vo.LoginUserVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {

	@Autowired
	private IMerchUserService merchUserService;
	
	@Autowired
	private IMerchService merchService;
	
	@Value("${platformKey}")
    private String key;
	@Autowired
	private IMerchUserBankService merchUserBankService;
	@Autowired
	private MerchUserMapper merchUserMapper;
	
	@ApiOperation(notes = "调用 /login方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "账户登录")
	@PostMapping(value="/login")
	@SystemControllerLog(description = "App项目账户登录")
	public ResultBean<MerchInfo> login(@Validated @RequestBody RequestBean<LoginUserVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getLoginName())||StrUtil.isEmpty(reqData.getData().getPassword())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		
		ResultBean<MerchInfo> resultBean = new ResultBean<MerchInfo>();
		log.info("账号:"+reqData.getLoginName()+",密码:"+reqData.getData().getPassword());
        MerchUser merchUser = merchUserService.queryMerchUser(reqData.getLoginName());
        if(null==merchUser){
        	return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0001.getErrorCode(), ResCode.API_ERROE_CODE_0001.getErrorMes());
        }
        
        switch (merchUser.getStatus()) {
		case 1:
			//查询商户信息
		    log.info("密码密文:"+MD5.getMD5ofStr(reqData.getData().getPassword(), "GBK"));
			if(MD5.getMD5ofStr(reqData.getData().getPassword(), "GBK").equals(merchUser.getPassword())){
				MerchInfo merchInfo = merchService.getByLoginName(merchUser.getLoginName());
				merchInfo.setUserRank(merchUser.getUserRank());
				List<MerchUserBank> userBankList = merchUserBankService.queryUserBankList(merchUser.getLoginName());
				merchInfo.setSumTieCard((null==userBankList||userBankList.size()<1)?0:userBankList.size());
				merchInfo.setIsDisable("0");
				
				if(!StringUtils.isEmpty(merchUser.getReferName())){
					Map<String,String> userParams = new HashMap<String,String>();
					userParams.put("id", merchUser.getReferName());
					merchInfo.setReferName(merchUserMapper.queryMerchUser(userParams).getTrueName());
				}else{
					merchInfo.setReferName("平台注册");
				}
		        switch (merchUser.getUserRank()) {
				case 1:
					merchInfo.setUserRankName(MerchUserRank.STAFF_MEMBER.getName());
					break;
				case 2:
					merchInfo.setUserRankName(MerchUserRank.STORE_MANAGER.getName());
					break;
				case 3:
					merchInfo.setUserRankName(MerchUserRank.THE_BOSS.getName());
					break;
				case 4:
					merchInfo.setUserRankName(MerchUserRank.GENERAL_AGENT.getName());
					break;
				case 5:
					merchInfo.setUserRankName(MerchUserRank.SENIOR_AGENT.getName());
					break;
				case 6:
					merchInfo.setUserRankName(MerchUserRank.THE_PARTNER.getName());
					break;
				default:
					break;
				}
				resultBean = RestBeanGenerator.genSuccessResult(merchInfo);
			}else{
				merchUser.setStatus(merchUser.getErrorCount()>=4 ? 3:1);
				merchUserService.updateErrorCount(merchUser.getId(),merchUser.getErrorCount(),merchUser.getStatus());
				resultBean = RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0002.getErrorCode(), ResCode.API_ERROE_CODE_0002.getErrorMes());
			}
			break;
		case 2:
			//冻结,去激活
			resultBean = RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0004.getErrorCode(), ResCode.API_ERROE_CODE_0004.getErrorMes());
			break;
		case 3:
			//锁定，去解锁
			resultBean = RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0003.getErrorCode(), ResCode.API_ERROE_CODE_0003.getErrorMes());
			break;
		default:
			break;
		}
        return resultBean;
	}
}
