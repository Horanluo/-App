package com.alycloud.account.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.alycloud.account.api.IChannelSubMerchInfoService;
import com.alycloud.account.api.IMerchService;
import com.alycloud.account.api.IReportHXFeeService;
import com.alycloud.account.api.IReportYUFeeService;
import com.alycloud.account.mapper.ChannelSubMerchInfoHistoryMapper;
import com.alycloud.account.mapper.ChannelSubMerchInfoMapper;
import com.alycloud.account.mapper.QrcodeMerchFeeMapper;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.QrcodeMerchFee;
import com.alycloud.modules.enums.SysChannelType;

@Service
public class ChannelSubMerchInfoImpl implements IChannelSubMerchInfoService{

	@Autowired
	private ChannelSubMerchInfoMapper channelSubMerchInfoMapper;
	@Value("${ylpay_advance_rate}")
    private BigDecimal ylpay_advance_rate;
	@Autowired
	private IReportYUFeeService reportYUFeeService;
	@Autowired
	private IReportHXFeeService reportHXFeeService;
	@Autowired
	private IMerchService merchService;
	@Autowired
	private ChannelSubMerchInfoHistoryMapper channelHistoryMapper;
	@Resource(name = "transactionManager")
	private PlatformTransactionManager platformTransactionManager;
	@Autowired
	private QrcodeMerchFeeMapper qrcodeMerchFeeMapper;
	
	@Override
	@ServiceLogAnnotation(moduleName="查询商户快捷费率")
	public List<ChannelSubmerchInfo> getChannelMerchInfoList(String merchno, String payType) {
		Map<String,String> queryParams=new HashMap<String,String>();
		queryParams.put("merchno", merchno);
		
		List<ChannelSubmerchInfo> list = new ArrayList<ChannelSubmerchInfo>();
		if("6".equals(payType)){
			//处理  给字段modifyFeeType赋值  标注修改渠道费率的限制  YUFU只能修改T1费率   HXTC只能修改T0费率
			queryParams.put("payType", "QUICKPAY");
			list = channelSubMerchInfoMapper.getChannelMerchInfoList(queryParams);
			List<ChannelSubmerchInfo> newList=new ArrayList<ChannelSubmerchInfo>();
			for(ChannelSubmerchInfo csi:list){
				if("YUFU".equals(csi.getChannelCode())){
					csi.setModifyFeeType(1);
					csi.setChannelName("银联快捷大额A");
				}
				if("HXTC".equals(csi.getChannelCode())){
					csi.setModifyFeeType(0);
					csi.setChannelName("银联快捷大额B");
				}
				csi.setPayType(payType.toString());
				newList.add(csi);
			}
			return newList;
		}else{
			queryParams.put("payType", payType);
			List<QrcodeMerchFee> qrcodeFees = qrcodeMerchFeeMapper.list(queryParams);
			if(qrcodeFees.isEmpty()){
				return list;
			}
			
			ChannelSubmerchInfo channelSubmerchInfo = new ChannelSubmerchInfo();
			for(QrcodeMerchFee qmf:qrcodeFees){
				if(1==qmf.getSettleType()){
					channelSubmerchInfo.setD0payRate(qmf.getRate());
				}
				if(2==qmf.getSettleType()){
					channelSubmerchInfo.setT1payRate(qmf.getRate());
				}
			}
			channelSubmerchInfo.setChannelName("扫码支付A");
			list.add(channelSubmerchInfo);
			return list;
		}
	}

	@Override
	@ServiceLogAnnotation(moduleName="修改渠道商户费率")
	public SingleResult<String> modifyMerchFee(String merchno,BigDecimal modifyRate,String channelCode) throws ApiException{
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = platformTransactionManager.getTransaction(def);
		
		MerchInfo merchInfo=merchService.getByMerchno(merchno);
		SingleResult<String> singleResult = new SingleResult<String>();
		
		if(channelCode.equals(SysChannelType.YUFU.getCode())){
			Map<String,String> params = new HashMap<String,String>();
			params.put("merchno", merchno);
			params.put("channelCode", channelCode);
			params.put("auditStatus", "1");
			List<ChannelSubmerchInfo> csmiList = channelSubMerchInfoMapper.getChannelMerchInfoList(params);
			ChannelSubmerchInfo oldRecord = csmiList.get(csmiList.size()-1);
			channelHistoryMapper.addChannelInfoHistory(oldRecord);
			
			//修改之前更新老记录到历史表
			ChannelSubmerchInfo newRecord = oldRecord;
			channelSubMerchInfoMapper.deleteRecord(oldRecord.getId());
			
			try {
				return reportYUFeeService.modifyYUFee(merchInfo, channelCode,newRecord,modifyRate);
			} catch (Exception e) {
				e.printStackTrace();
				platformTransactionManager.rollback(status);
				throw new ApiException(ApiCode.C9999);
			}finally{
				platformTransactionManager.commit(status);
			}
		}
		if(channelCode.equals(SysChannelType.HXTC.getCode())){
			Map<String,String> params = new HashMap<String,String>();
			params.put("merchno", merchno);
			params.put("channelCode", channelCode);
			params.put("auditStatus", "1");
			
			List<ChannelSubmerchInfo> csmiList = channelSubMerchInfoMapper.getChannelMerchInfoList(params);
			ChannelSubmerchInfo oldRecord = csmiList.get(csmiList.size()-1);
			channelHistoryMapper.addChannelInfoHistory(oldRecord);
			
			//修改之前更新老记录到历史表
			ChannelSubmerchInfo newRecord = oldRecord;
			newRecord.setD0payRate(modifyRate);
			channelSubMerchInfoMapper.deleteRecord(oldRecord.getId());
			
			merchInfo = merchService.getByMerchno(merchno);
			try {
				return reportHXFeeService.modifyHXFee(merchInfo, channelCode,newRecord);
			} catch (Exception e) {
				e.printStackTrace();
				platformTransactionManager.rollback(status);
				throw new ApiException(ApiCode.C9999);
			}finally{
				platformTransactionManager.commit(status);
			}
		}
		if(channelCode.equals(SysChannelType.HXTC_JF.getCode())){
			Map<String,String> params = new HashMap<String,String>();
			params.put("merchno", merchno);
			params.put("channelCode", channelCode);
			params.put("auditStatus", "1");
			
			List<ChannelSubmerchInfo> csmiList = channelSubMerchInfoMapper.getChannelMerchInfoList(params);
			ChannelSubmerchInfo oldRecord = csmiList.get(csmiList.size()-1);
			channelHistoryMapper.addChannelInfoHistory(oldRecord);
			
			//修改之前更新老记录到历史表
			ChannelSubmerchInfo newRecord = oldRecord;
			newRecord.setD0payRate(modifyRate);
			channelSubMerchInfoMapper.deleteRecord(oldRecord.getId());
			
			merchInfo = merchService.getByMerchno(merchno);
			try {
				return reportHXFeeService.modifyHXFee(merchInfo, channelCode,newRecord);
			} catch (Exception e) {
				e.printStackTrace();
				platformTransactionManager.rollback(status);
				throw new ApiException(ApiCode.C9999);
			}finally{
				platformTransactionManager.commit(status);
			}
		}
		return singleResult;
	}
	
	@Override
	@ServiceLogAnnotation(moduleName="上报渠道商户费率")
	public SingleResult<String> reportMerchFee(String merchno, MerchInfo merch4Mod,String channelCode) throws Exception {
		SingleResult<String> singleResult = new SingleResult<String>();
		
		if(channelCode.equals(SysChannelType.YUFU.getCode())){
			return reportYUFeeService.reportYUFee(merchno, merch4Mod,channelCode);
		}
		if(channelCode.equals(SysChannelType.HXTC.getCode())){
			return reportHXFeeService.reportHXFee(merchno, merch4Mod,channelCode);
		}
		if(channelCode.equals(SysChannelType.HXTC_JF.getCode())){
			return reportHXFeeService.reportHXFee(merchno, merch4Mod,channelCode);
		}
		return singleResult;
	}

	@Override
	public List<ChannelSubmerchInfo> getChannelMerchInfoList(ChannelSubmerchInfo csmi) {
		Map<String,String> queryParams=new HashMap<String,String>();
		queryParams.put("channelCode", csmi.getChannelCode());
		queryParams.put("aduitStatus", csmi.getAduitStatus());
		return channelSubMerchInfoMapper.getChannelMerchInfoList(queryParams);
	}

	@Override
	@ServiceLogAnnotation(moduleName="更新渠道信息")
	public int updateRecord(ChannelSubmerchInfo csmi) {
		return channelSubMerchInfoMapper.updateRecord(csmi);
	}
}
