package com.alycloud.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alycloud.account.api.IMerchService;
import com.alycloud.account.api.IMerchUserBankService;
import com.alycloud.account.feign.UtilFeign;
import com.alycloud.account.mapper.MerchUserBankMapper;
import com.alycloud.account.mapper.SystemBankMapper;
import com.alycloud.account.mapper.SystemCardBinMapper;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUserBank;
import com.alycloud.modules.entity.SystemBank;
import com.alycloud.modules.entity.SystemCardBin;
import com.alycloud.modules.vo.CardVO;
import com.alycloud.modules.vo.SaveCardVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MerchUserBankServiceImp implements IMerchUserBankService {

	@Autowired
	private MerchUserBankMapper merchUserBankMapper;
	@Autowired
	private SystemBankMapper systemBankMapper;
	@Autowired
	private SystemCardBinMapper systemCardBinMapper;
	@Autowired
	private IMerchService merchService;
	@Autowired
	private UtilFeign utilFeign;
	
	@Override
	@ServiceLogAnnotation(moduleName="查询账户绑卡列表")
	public List<MerchUserBank> queryUserBankList(String loginName) {
		Map<String,String> userBankParams = new HashMap<String,String>();
		userBankParams.put("userId", loginName);
		return merchUserBankMapper.queryUserBankList(userBankParams);
	}

	@Override
	@ServiceLogAnnotation(moduleName="新增绑卡信息")
	public SingleResult<String> addMerchUserBank(RequestBean<CardVO> reqData) {
		MerchUserBank musb = new MerchUserBank();
		SingleResult<String> singleResult = new SingleResult<String>();
		
		MerchInfo merchInfo = merchService.getByMerchno(reqData.getMerchno());
		Map<String,String> params = new HashMap<String,String>();
		params.put("userId", merchInfo.getUserId());
		List<MerchUserBank>  banklist= merchUserBankMapper.queryUserBankList(params);
		for(MerchUserBank mub:banklist){
			if(reqData.getData().getCardNo().equals(mub.getCardNo())){
				singleResult.setErrorHint("银行卡已绑定");
				singleResult.setSuccess(false);
				return singleResult;
			}
		}
		SystemCardBin systemCardBin = systemCardBinMapper.getCardInfo(reqData.getData().getCardNo());
		SystemBank sb = new SystemBank();
		String bankName = systemCardBin.getBankName();
		Integer cardType = systemCardBin.getCardType();
		
		//坑爹的修改
//		if("广东发展银行".equals(bankName)){
//			bankName="广发银行";
//		}
		if((2==cardType)||(3==cardType)){
			if(StringUtils.isEmpty(reqData.getData().getCvv2())||StringUtils.isEmpty(reqData.getData().getExpDate())){
				singleResult.setErrorHint("完善信用卡安全码，有效期信息");
				singleResult.setSuccess(false);
				return singleResult;
			}
		}
		
		JSONObject resJson;
		
		SaveCardVO saveCardVo = new SaveCardVO();
		saveCardVo.setIdNo(merchInfo.getIdentityCard());
		saveCardVo.setCardName(reqData.getData().getCardName());
		saveCardVo.setCardNo(reqData.getData().getCardNo());
		saveCardVo.setPhoneNo(reqData.getData().getPhoneNo());
		
		resJson = utilFeign.certifyCardMsg(saveCardVo);
		
		String code = resJson.getString("ret_code");
		String msg = resJson.getString("ret_msg");
		
		if (!code.equals("0000")) {
			log.warn("实名认证失败：{}", msg);
			singleResult.setErrorCode(code);
			singleResult.setErrorHint(msg);
			singleResult.setSuccess(false);
			return singleResult;
		}
		
		sb.setBankName(bankName);
		
		SystemBank systemBank = systemBankMapper.getSystemBankInfo(sb);
		
//		if("DC".equals(cardType)){
//			musb.setCardType("1");
//		}else if("CC".equals(cardType)){
//			musb.setCardType("2");
//		}else if("SCC".equals(cardType)){
//			musb.setCardType("3");
//		}else if("PC".equals(cardType)){
//			musb.setCardType("4");
//		}
		musb.setUserId(merchInfo.getUserId());
		musb.setCardName(reqData.getData().getCardName());
		musb.setHeadBankNo((null==systemBank)?null:systemBank.getBankId());
		musb.setHeadBankName(bankName);
		musb.setBranchBankNo((null==systemBank)?null:systemBank.getBankCode());
		musb.setCardNo(reqData.getData().getCardNo());
		musb.setCardType(cardType.toString());
		musb.setExpDate(reqData.getData().getExpDate());
		musb.setPhone(reqData.getData().getPhoneNo());
		musb.setCvv2(reqData.getData().getCvv2());
		//musb.setBankNameCode(cardInfoJson.getString("bank"));
		log.info("结算卡信息:"+musb.toString());
		if(1==merchUserBankMapper.addMerchUserBank(musb)){
			singleResult.setErrorHint("绑卡成功");
			singleResult.setSuccess(true);
			return singleResult;
		}
		singleResult.setErrorHint("绑卡失败");
		singleResult.setSuccess(false);
		return singleResult;
	}

	@Override
	@ServiceLogAnnotation(moduleName="编辑绑卡信息")
	public SingleResult<String> modifyMerchUserBank(RequestBean<CardVO> reqData) {
		SingleResult<String> singleResult = new SingleResult<String>();
		MerchInfo merchInfo = merchService.getByMerchno(reqData.getMerchno());
		
		JSONObject resJson;
		
		SaveCardVO saveCardVo = new SaveCardVO();
		saveCardVo.setIdNo(merchInfo.getIdentityCard());
		saveCardVo.setCardName(reqData.getData().getCardName());
		saveCardVo.setCardNo(reqData.getData().getCardNo());
		saveCardVo.setPhoneNo(reqData.getData().getPhoneNo());
		
		resJson = utilFeign.certifyCardMsg(saveCardVo);
		
		String code = resJson.getString("ret_code");
		String msg = resJson.getString("ret_msg");
		
		if (!code.equals("0000")) {
			log.warn("实名认证失败：{}", msg);
			singleResult.setErrorCode(code);
			singleResult.setErrorHint(msg);
			singleResult.setSuccess(false);
			return singleResult;
		}
		
		Map<String,String> param = new HashMap<String,String>();
		param.put("id", reqData.getData().getId());
		param.put("cardNo", reqData.getData().getCardNo());
		if(merchUserBankMapper.modifyMerchUserBank(param)>0){
			singleResult.setSuccess(true);
			singleResult.setErrorHint("修改绑卡成功");
		}else{
			singleResult.setSuccess(false);
			singleResult.setErrorHint("修改绑卡失败");
		}
		return singleResult;
	}
}
