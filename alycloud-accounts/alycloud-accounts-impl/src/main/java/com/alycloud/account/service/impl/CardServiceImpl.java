package com.alycloud.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import com.alibaba.fastjson.JSONObject;
import com.alycloud.account.api.ICardService;
import com.alycloud.account.api.IMerchService;
import com.alycloud.account.feign.UtilFeign;
import com.alycloud.account.mapper.ChangeMerchAccountMapper;
import com.alycloud.account.mapper.MerchAuditMapper;
import com.alycloud.account.mapper.MerchInfoMapper;
import com.alycloud.account.mapper.MerchJudgMapper;
import com.alycloud.account.mapper.MerchVirtualCardMapper;
import com.alycloud.account.mapper.SystemBankMapper;
import com.alycloud.account.mapper.SystemCardBinMapper;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.ChangeMerchAccount;
import com.alycloud.modules.entity.MerchAudit;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchJudg;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.entity.SystemBank;
import com.alycloud.modules.entity.SystemCardBin;
import com.alycloud.modules.search.MerchVirtualCard4Search;
import com.alycloud.modules.vo.SaveCardVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 银行卡服务接口
 * @author Administrator
 *
 */
@Slf4j
@Service
public class CardServiceImpl implements ICardService {

	@Autowired
	private IMerchService merchService;
	@Autowired
	private ChangeMerchAccountMapper changeMerchAccountMapper;
	@Autowired
	private MerchInfoMapper merchInfoMapper;
	@Autowired
	private SystemBankMapper systemBankMapper;
	@Autowired
	private MerchVirtualCardMapper merchVirtualCardMapper;
	@Autowired
	private SystemCardBinMapper systemCardBinMapper;
//	@Value("${card.authKey}")
//    private String cardAuthKey;
//	@Value("${card.authService}")
//    private String cardAuthService;
	@Resource(name = "transactionManager")
	private PlatformTransactionManager platformTransactionManager;
	@Autowired
	private UtilFeign utilFeign;
	@Autowired
	private MerchAuditMapper merchAuditMapper;
	@Autowired
	private MerchJudgMapper merchJudgMapper;
//	@Override
//	@ServiceLogAnnotation(moduleName="银行卡四要素验证")
//	public JSONObject certifyCardMsg(String id, String name, String card, 
//			String mobile, String authKey,String authService) throws ServiceException {
//		try {
//			name = URLEncoder.encode(name, "UTF-8");
//			StringBuffer url = new StringBuffer();
//			url.append(authService);// 汇联
//			url.append("&id_no=" + id);
//			url.append("&id_name=" + name);
//			url.append("&card_no=" + card);
//			url.append("&mobile_no=" + mobile);
//			url.append("&auth_key=" + authKey);
//			return sendMsg(url.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ServiceException("0004", "实名认证卡号异常");
//		}
//		
//	}
//	
//	private JSONObject sendMsg(String urlStr) throws Exception {
//		log.debug("实名认证：{}", urlStr);
//
//		URL url = new URL(urlStr);
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				con.getInputStream(), "UTF-8"));
//		StringBuffer sb = new StringBuffer();
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			sb.append(line);
//		}
//		br.close();
//		
//		log.debug("认证结果：\r\n{}", sb.toString());
//		JSONObject obj = JSONObject.parseObject(sb.toString());
//		return obj;
//	}

	@Override
	@ServiceLogAnnotation(moduleName="银行卡认证")
	public SingleResult<String> addChangeMerchAccount(RequestBean<SaveCardVO> reqData) throws ApiException{
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//		TransactionStatus status = platformTransactionManager.getTransaction(def);
		
		SingleResult<String> singleResult = new SingleResult<String>();
		
		MerchInfo merch = merchService.getByMerchno(reqData.getMerchno());
		MerchInfo merch4Mod = merch;
		
//		if(!StrUtil.isEmpty(changeMerchAccountMapper.getChangeMerchAccount(reqData.getMerchno()))){
//			merch4Mod.setRealNameAuthStatus(2);
//			merchInfoMapper.updateMerchInfo(merch4Mod);
//			singleResult.setErrorCode(ResCode.API_ERROE_CODE_0024.getErrorCode());
//			singleResult.setErrorHint(ResCode.API_ERROE_CODE_0024.getErrorMes());
//			singleResult.setSuccess(true);
//			return singleResult;
//		}
		
		JSONObject resJson;
		resJson = utilFeign.certifyCardMsg(reqData.getData());
		
		String code = resJson.getString("ret_code");
		String msg = resJson.getString("ret_msg");
		
		if (!code.equals("0000")) {
			log.warn("实名认证失败：{}", msg);
			singleResult.setErrorCode(code);
			singleResult.setErrorHint(msg);
			singleResult.setSuccess(false);
			return singleResult;
		}
		
		String cardName = reqData.getData().getCardName();
		String cardNo = reqData.getData().getCardNo();
		
//			String cardInfo = utilFeign.getCardDetail(cardNo);
//			JSONObject cardInfoJson = JSONObject.parseObject(cardInfo);
//			if(!cardInfoJson.getBoolean("validated")){
//				throw new ServiceException("0003", "卡号信息认证失败");
//			}
		SystemCardBin systemCardBin = systemCardBinMapper.getCardInfo(cardNo);
		
		SystemBank sb = new SystemBank();
		sb.setBankName(systemCardBin.getBankName());
		
		SystemBank systemBank = systemBankMapper.getSystemBankInfo(sb);
		
		merch4Mod.setAccountName(cardName);
		merch4Mod.setAccountno(cardNo);
		merch4Mod.setBankno((null==systemBank)?null:systemBank.getBankId());
		merch4Mod.setBankName(systemCardBin.getBankName());
		merch4Mod.setRealNameAuthStatus(2);
		
		ChangeMerchAccount changeMerchAccount = changeMerchAccountMapper.getChangeMerchAccount(reqData.getMerchno()).get(0);
		changeMerchAccount.setAccountName(cardName);
		changeMerchAccount.setAccountno(cardNo);
		changeMerchAccount.setAccountType(merch.getAccountType());
		changeMerchAccount.setBankName(merch4Mod.getBankName());
		changeMerchAccount.setBankno(merch4Mod.getBankno());
		changeMerchAccount.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		changeMerchAccount.setAuditStatus(com.alycloud.modules.enums.ChangeMerchAccountAuditStatus.AUDITING.ordinal());
		
		//更新虚拟账户记录
		MerchVirtualCard4Search mvcSearch = new MerchVirtualCard4Search();
		mvcSearch.setMerchno(reqData.getMerchno());
		List<MerchVirtualCard> list = merchVirtualCardMapper.listByPage(mvcSearch);
		
		MerchVirtualCard mvc = list.get(0);
		mvc.setAccountName(cardName);
		mvc.setAccountno(cardNo);
		mvc.setBankno(merch4Mod.getBankno());
		mvc.setBankName(merch4Mod.getBankName());
		
		//新增初审，终审记录
		MerchAudit merchAudit = new MerchAudit();
		merchAudit.setMerchId(merch.getId().toString());
		merchAudit.setAuditStatus(1);
		merchAudit.setAuditAgentno(merch.getAuditAgentno());
		merchAudit.setLoginName(merch.getUserId());
		merchAudit.setTrueName(merch.getLegalName());
		
		MerchJudg merchJudg = new MerchJudg();
		merchJudg.setMerchId(merch.getId().toString());
		merchJudg.setAuditStatus(1);
		merchJudg.setLoginName(merch.getUserId());
		merchJudg.setTrueName(merch.getLegalName());
		
		if(merchInfoMapper.updateMerchInfo(merch4Mod)>0&&changeMerchAccountMapper.updateToAuditInfo(changeMerchAccount)>0
				&&merchVirtualCardMapper.modifyRecord(mvc)>0&&merchAuditMapper.insert(merchAudit)>0&&merchJudgMapper.insert(merchJudg)>0){
			singleResult.setErrorHint("银行卡认证成功");
			singleResult.setSuccess(true);
			return singleResult;
		}
		singleResult.setErrorCode(ResCode.API_ERROE_CODE_0016.getErrorCode());
		singleResult.setErrorHint(ResCode.API_ERROE_CODE_0016.getErrorMes());
		singleResult.setSuccess(false);
		return singleResult;
	}
}
