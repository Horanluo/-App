package com.alycloud.account.service.channel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import com.alycloud.account.api.IReportHXFeeService;
import com.alycloud.account.feign.ChannelFeign;
import com.alycloud.account.mapper.ChannelSubMerchInfoMapper;
import com.alycloud.account.mapper.MerchInfoMapper;
import com.alycloud.channel.utils.hxtc.HxtcUtils;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.MerchInfo;
import lombok.extern.slf4j.Slf4j;

@Service
@ConfigurationProperties(prefix="hfbank")
@Slf4j
public class ReportHXFeeServiceImp extends AbsDefaultChannelInfo implements IReportHXFeeService{

	@Autowired
	private MerchInfoMapper merchInfoMapper;
	@Autowired
	private ChannelSubMerchInfoMapper channelSubMerchInfoMapper;
	@Autowired
	private ChannelFeign channelFeign;
	
	@Override
	@ServiceLogAnnotation(moduleName="申报汇享渠道商户费率")
	public SingleResult<String> reportHXFee(String merchno, MerchInfo merch4Mod,String channelCode) throws Exception {
		SingleResult<String> singleResult = new SingleResult<String>();
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("merchno", merchno);
		params.put("channelCode", channelCode);
		
		List<ChannelSubmerchInfo> csmiList = channelSubMerchInfoMapper.getChannelMerchInfoList(params);
		ChannelSubmerchInfo newRecord = csmiList.get(csmiList.size()-1);
		//ChannelSubmerchInfo newRecord;
		
		MerchInfo merchInfo = merchInfoMapper.getByMerchno(merchno);
		if(!(null==merch4Mod)){
			merchInfo.setAccountno(merch4Mod.getAccountno());
			merchInfo.setAccountName(merch4Mod.getAccountName());
			merchInfo.setBankno(merch4Mod.getBankno());
			merchInfo.setBankName(merch4Mod.getBankName());
		}
		
		//for(ChannelSubmerchInfo csmi:csmiList){
		BigDecimal t0Rate = newRecord.getD0payRate();
		BigDecimal t1Rate = newRecord.getT1payRate();
		BigDecimal toFee = newRecord.getPayFeeD0();
		
		log.info("t0Rate:"+t0Rate.toPlainString()+"t1Rate:"+t1Rate.toPlainString()+
				"toFee:"+toFee.toPlainString());

		String bankType = merchInfoMapper.getBankByCardno(merchInfo.getAccountno());
        String bankId = merchInfoMapper.getBankIDByBankName(bankType);
        
        Map<String, String> businessReq = HxtcUtils.builderRegisterRequestParams(merchInfo, bankId,newRecord);
        RequestBean<Map<String, String>> feignData = new RequestBean<Map<String, String>>();
        feignData.setData(businessReq);
        Map<String, Object> result = channelFeign.recordHXTCMerchFee(feignData);
        if(result.get("respCode").equals("000000"))
        {
           @SuppressWarnings("unchecked")
           Map<String, String>  data = (Map<String, String>)result.get("data");
           newRecord.setChannelSubmerchno(data.get("third_merchant_code"));
           newRecord.setAduitStatus("1");
           
           channelSubMerchInfoMapper.updateRecord(newRecord);
           singleResult.setErrorCode(result.get("respCode").toString());
           singleResult.setErrorHint(result.get("respMsg").toString());
           singleResult.setSuccess(true);
           
           return singleResult;
        }
        log.warn("商户[{}]入驻汇享天成申请失败：[{}]{}", new Object[]{merchInfo.getMerchno(), result.get("respCode"), result.get("respMsg")});
        singleResult.setErrorCode(result.get("respCode").toString());
        singleResult.setErrorHint(result.get("respMsg").toString());
        singleResult.setSuccess(false);
	//}
        return singleResult;
	}

	@Override
	@ServiceLogAnnotation(moduleName="修改汇享渠道商户费率")
	public SingleResult<String> modifyHXFee(MerchInfo merchInfo, String channelCode,ChannelSubmerchInfo newRecord) throws Exception {
		
		SingleResult<String> singleResult = new SingleResult<String>();
        String bankType = merchInfoMapper.getBankByCardno(merchInfo.getAccountno());
        String bankId = merchInfoMapper.getBankIDByBankName(bankType);
        
        Map<String, String> businessReq = HxtcUtils.builderModifyRequestParams(merchInfo, bankId,newRecord);
        RequestBean<Map<String, String>> feignData = new RequestBean<Map<String, String>>();
        feignData.setData(businessReq);
        Map<String, Object> result = channelFeign.recordHXTCMerchFee(feignData);
        if(result.get("respCode").equals("000000"))
        {
        	@SuppressWarnings("unchecked")
	        Map<String, String>  data = (Map<String, String>)result.get("data");
	        newRecord.setChannelSubmerchno(data.get("third_merchant_code"));
	        newRecord.setAduitStatus("1");
	        newRecord.setRemark("修改费率");
	        
	        singleResult.setErrorCode(result.get("respCode").toString());
			singleResult.setErrorHint(result.get("respMsg").toString());
			singleResult.setSuccess(true);
			
	        channelSubMerchInfoMapper.addMerchFee(newRecord);
	        return singleResult;
        }
        log.warn("商户[{}]入驻御付申请失败：[{}]{}", new Object[]{merchInfo.getMerchno(), result.get("respCode"), 
        		result.get("respMsg")});
        singleResult.setErrorCode(result.get("respCode").toString());
		singleResult.setErrorHint(result.get("respMsg").toString());
		singleResult.setSuccess(false);
		return singleResult;
	}
}
