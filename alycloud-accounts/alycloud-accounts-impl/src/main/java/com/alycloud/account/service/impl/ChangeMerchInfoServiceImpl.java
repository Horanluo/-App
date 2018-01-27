package com.alycloud.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alycloud.account.api.IChangeMerchInfoService;
import com.alycloud.account.api.IMerchService;
import com.alycloud.account.mapper.ChangeMerchAccountMapper;
import com.alycloud.account.mapper.ChangeMerchInfoMapper;
import com.alycloud.account.mapper.MerchInfoMapper;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.core.utils.CommonUtil;
import com.alycloud.modules.entity.ChangeMerchAccount;
import com.alycloud.modules.entity.ChangeMerchInfo;
import com.alycloud.modules.entity.MerchInfo;

/**
 * 待审核商户信息服务实现类
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月21日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@ConfigurationProperties(prefix="hfbank")
public class ChangeMerchInfoServiceImpl implements IChangeMerchInfoService{

	@Autowired 
	private ChangeMerchInfoMapper changeMerchInfoMapper;
	@Autowired 
	private ChangeMerchAccountMapper changeMerchAccountMapper;
	@Autowired
	private IMerchService merchService;
	@Autowired
	private MerchInfoMapper merchInfoMapper;
	@Value("${file-api-path}")
    private String apiUrl;
	
	@Override
	public List<ChangeMerchInfo> getChangeMerchInfoList(ChangeMerchInfo info4s) {
		return changeMerchInfoMapper.list(info4s);
	}
	
	@Override
	public int add(ChangeMerchInfo info) {
		return changeMerchInfoMapper.add(info);
	}
	
	@Override
	public int mod(ChangeMerchInfo info) {
		return changeMerchInfoMapper.mod(info);
	}

	@Override
	//@ServiceLogAnnotation(moduleName="更新待审核的信息，新增手持身份证")
	@Transactional
	public SingleResult<String> updateToAuditInfo(String isIdImg,String base64Img,String imgLocalpath,String merchno) throws Exception {
		SingleResult<String> singleResult = new SingleResult<String>();
		
		MerchInfo merch = merchService.getByMerchno(merchno);
		
		if(2==merch.getRealNameAuthStatus()){
			singleResult.setErrorCode(ResCode.API_ERROE_CODE_0024.getErrorCode());
			singleResult.setErrorHint(ResCode.API_ERROE_CODE_0024.getErrorMes());
			singleResult.setSuccess(false);
			return singleResult;
		}
		
		Map<Object,Object> map = CommonUtil.saveUploadedFiles(base64Img,imgLocalpath+merchno+"/");
		if((boolean)map.get("uploadFlag")){
			if("1".equals(isIdImg)){
				ChangeMerchInfo cmi = changeMerchInfoMapper.getChangeMerchInfo(merchno);
				ChangeMerchInfo changeMerchInfo = new ChangeMerchInfo();
				changeMerchInfo = cmi;
				changeMerchInfo.setImgIdCard3(apiUrl+merchno+"/"+map.get("path"));
				merch.setIdentity3Img(apiUrl+merchno+"/"+map.get("path"));
				if(merchInfoMapper.updateMerchInfo(merch)>0&&changeMerchInfoMapper.updateToAuditInfo(changeMerchInfo)>0){
					singleResult.setErrorHint("上传成功");
					singleResult.setSuccess(true);
					return singleResult;
				}
				singleResult.setSuccess(false);
				return singleResult;
			}else{
				//ChangeMerchAccount cma = changeMerchAccountMapper.getChangeMerchAccount(merchno);
				ChangeMerchAccount changeMerchAccount = new ChangeMerchAccount();
				//changeMerchAccount = cma;
				changeMerchAccount.setMerchId(new Long(merch.getId()));
				changeMerchAccount.setAgentno(merch.getSuperAgent());
				changeMerchAccount.setMerchName(merch.getMerchName());
				changeMerchAccount.setAccountType(merch.getAccountType());
				changeMerchAccount.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				changeMerchAccount.setImgCard1(apiUrl+merchno+"/"+map.get("path"));
				merch.setCard1Img(apiUrl+merchno+"/"+map.get("path"));
				if(merchInfoMapper.updateMerchInfo(merch)>0&&changeMerchAccountMapper.add(changeMerchAccount)>0){
					singleResult.setErrorHint("上传成功");
					singleResult.setSuccess(true);
					return singleResult;
				}
				singleResult.setSuccess(false);
				return singleResult;
			}
		}
		singleResult.setSuccess(false);
		return singleResult;
	}

	@Override
	@ServiceLogAnnotation(moduleName="获取待审核商户信息   查询身份证")
	public ChangeMerchInfo getChangeMerchInfo(String merchno) {
		return changeMerchInfoMapper.getChangeMerchInfo(merchno);
	}
}
