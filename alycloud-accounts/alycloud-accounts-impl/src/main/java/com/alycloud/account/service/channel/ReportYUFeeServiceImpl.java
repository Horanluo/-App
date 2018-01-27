package com.alycloud.account.service.channel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alycloud.account.api.IReportYUFeeService;
import com.alycloud.account.feign.ChannelFeign;
import com.alycloud.account.mapper.ChannelSubMerchInfoMapper;
import com.alycloud.channel.yufu.merch.bean.MerchApplyResult;
import com.alycloud.channel.yufu.merch.bean.MerchQueryData;
import com.alycloud.channel.yufu.merch.bean.MerchQueryResult;
import com.alycloud.channel.yufu.merch.enums.MerchState;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.vo.ReportMerchFeeVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportYUFeeServiceImpl extends AbsDefaultChannelInfo implements IReportYUFeeService{

	@Autowired
	private ChannelSubMerchInfoMapper channelSubMerchInfoMapper;
	@Autowired
	private ChannelFeign channelFeign;
	/**
	 * 申报商户费率
	 * @param merchno
	 * @param channelCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public SingleResult<String> reportYUFee(String merchno, MerchInfo merch4Mod,String channelCode) throws Exception{
		
		SingleResult<String> singleResult = new SingleResult<String>();
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("merchno", merchno);
		params.put("channelCode", channelCode);
		
		List<ChannelSubmerchInfo> csmiList = channelSubMerchInfoMapper.getChannelMerchInfoList(params);
		ChannelSubmerchInfo newRecord = csmiList.get(csmiList.size()-1);
		
		RequestBean<ReportMerchFeeVO> feignData = new RequestBean<ReportMerchFeeVO>();
		ReportMerchFeeVO rmfVO=new ReportMerchFeeVO();
		rmfVO.setCsmi(newRecord);
		rmfVO.setMerchInfo(merch4Mod);
		feignData.setData(rmfVO);
		
		MerchApplyResult result = channelFeign.recordYUFUFee(feignData);
		log.warn("返回参数：", result.toString());
		//重构代码   不保存历史记录   直接更新当前记录
		if (!result.getResultCode().equals("0000")) {
			log.warn("商户[{}]入驻御付申请失败：[{}]{}", new Object[]{newRecord.getMerchno(), result.getResultCode(), result.getResultMessage()});
			
			MerchQueryData data = new MerchQueryData();
			data.setPhone(merch4Mod.getMobile());
			data.setSerialNo("" + new Date().getTime() + (int)(Math.random() * 999));
			MerchQueryResult queryResult = channelFeign.queryAuditResult(data);
			if(!StrUtil.isEmpty(queryResult)&&"0000".equals(queryResult.getResultCode())&&MerchState.PASS.equals(queryResult.getState())){
				log.warn("已入驻商户[{}]申请失败：[{}]{}", new Object[]{merch4Mod.getMerchno(), result.getResultCode(), result.getResultMessage()});
				newRecord.setAduitStatus("1");
				newRecord.setChannelSubmerchno(queryResult.getMerchId());
				newRecord.setRemark("审核通过");
				newRecord.setYufuOneCodeUrl(queryResult.getOneCodeUrl());
				newRecord.setYufuKjKey(queryResult.getKjKey());
				newRecord.setYufuTermId(queryResult.getTermId());
				channelSubMerchInfoMapper.updateRecord(newRecord);
				
				newRecord.setRemark(queryResult.getResultMessage());
				singleResult.setErrorCode(queryResult.getResultCode());
				singleResult.setErrorHint(queryResult.getResultMessage());
				singleResult.setSuccess(true);
				return singleResult;
			}
			
			newRecord.setRemark(result.getResultMessage());
			singleResult.setErrorCode(result.getResultCode());
			singleResult.setErrorHint(result.getResultMessage());
			singleResult.setSuccess(false);
			return singleResult;
		}
		newRecord.setAduitStatus("0");
		newRecord.setRemark("上报费率");
		log.info(newRecord.toString());
		channelSubMerchInfoMapper.updateRecord(newRecord);
		
		singleResult.setErrorCode(result.getResultCode());
		singleResult.setErrorHint(result.getResultMessage());
		singleResult.setSuccess(true);
		return singleResult;
	}
	
	/**
	 * 修改上报 渠道费率 拼接渠道信息字段
	 * @param merchInfo
	 * @param newRecord
	 * @return
	 */
//	private ChannelSubmerchInfo buildModifyChannelSubmerchInfo(MerchInfo merchInfo,ChannelSubmerchInfo newRecord) {
//		
//		ChannelSubmerchInfo csmi = newRecord;
//		csmi.setD0payRate(merchInfo.getFastpayRateT0());
//		csmi.setT1payRate(merchInfo.getFastpayRateT1());
//		log.info(csmi.toString());
//		return csmi;
//	}
	
	/**
	 * 第一次上报 渠道费率 拼接渠道信息字段
	 * @param merchInfo
	 * @param data
	 * @param channelCode
	 * @param t1Fee
	 * @return
	 */
	@Override
	@ServiceLogAnnotation(moduleName="修改渠道商户费率")
	public SingleResult<String> modifyYUFee(MerchInfo merchInfo, String channelCode,ChannelSubmerchInfo newRecord,BigDecimal modifyRate) 
			throws Exception {
		SingleResult<String> singleResult = new SingleResult<String>();
		
		newRecord.setD0payRate(modifyRate.add(newRecord.getD0payRate()).subtract(newRecord.getT1payRate()));
		newRecord.setT1payRate(modifyRate);
		newRecord.setAduitStatus("0");
		newRecord.setRemark("修改费率");
		
		RequestBean<ReportMerchFeeVO> feignData = new RequestBean<ReportMerchFeeVO>();
		ReportMerchFeeVO rmfVO=new ReportMerchFeeVO();
		rmfVO.setCsmi(newRecord);
		rmfVO.setMerchInfo(null);
		feignData.setData(rmfVO);
		
		MerchApplyResult result = channelFeign.recordYUFUFee(feignData);
		log.warn("返回参数：", result.toString());
		ChannelSubmerchInfo csmi = newRecord;//buildModifyChannelSubmerchInfo(merchInfo,newRecord);
		if (!result.getResultCode().equals("0000")) {
			log.warn("商户[{}]入驻御付申请失败：[{}]{}", new Object[]{merchInfo.getMerchno(), result.getResultCode(), result.getResultMessage()});
			csmi.setAduitStatus("3");// 异常
			csmi.setRemark(result.getResultMessage());
			
			MerchQueryData data = new MerchQueryData();
			data.setPhone(merchInfo.getMobile());
			data.setSerialNo("" + new Date().getTime() + (int)(Math.random() * 999));
			MerchQueryResult queryResult = channelFeign.queryAuditResult(data);
			if("0000".equals(queryResult.getResultCode())&&!StrUtil.isEmpty(queryResult)&&MerchState.PASS.equals(queryResult.getState())){
				log.warn("已入驻商户[{}]申请失败：[{}]{}", new Object[]{merchInfo.getMerchno(), result.getResultCode(), result.getResultMessage()});
				newRecord.setAduitStatus("1");
				newRecord.setChannelSubmerchno(queryResult.getMerchId());
				newRecord.setRemark("审核通过");
				newRecord.setYufuOneCodeUrl(queryResult.getOneCodeUrl());
				newRecord.setYufuKjKey(queryResult.getKjKey());
				newRecord.setYufuTermId(queryResult.getTermId());
				channelSubMerchInfoMapper.addMerchFee(newRecord);
				
				newRecord.setRemark(queryResult.getResultMessage());
				singleResult.setErrorCode(queryResult.getResultCode());
				singleResult.setErrorHint(queryResult.getResultMessage());
				singleResult.setSuccess(true);
				return singleResult;
			}
			
			singleResult.setErrorCode(result.getResultCode());
			singleResult.setErrorHint(result.getResultMessage());
			singleResult.setSuccess(false);
			return singleResult;
		}
		log.info(csmi.toString());
		channelSubMerchInfoMapper.addMerchFee(csmi);
		
		singleResult.setErrorCode(result.getResultCode());
		singleResult.setErrorHint(result.getResultMessage());
		singleResult.setSuccess(true);
		return singleResult;
	}
}
