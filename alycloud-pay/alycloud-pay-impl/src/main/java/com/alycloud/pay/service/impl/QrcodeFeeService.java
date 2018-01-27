package com.alycloud.pay.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.QrcodeScanType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.enums.SysT0RateType;
import com.alycloud.modules.search.QrcodeBranchFee4Search;
import com.alycloud.modules.search.QrcodeChannelFee4Search;
import com.alycloud.modules.search.QrcodeMerchFee4Search;
import com.alycloud.pay.api.IQrcodeMerchFeeService;
import com.alycloud.pay.mapper.QrcodeBranchFeeMapper;
import com.alycloud.pay.mapper.QrcodeChannelFeeMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 二维码交易手续费计算
 * @author Moyq5
 * @date 2017年10月18日
 */
@Slf4j
@Service
public class QrcodeFeeService {

	@Autowired
	private QrcodeChannelFeeMapper qrcodeChannelFeeMapper;
	@Autowired
	private IQrcodeMerchFeeService qrcodeMerchFeeService;
	@Autowired
	private QrcodeBranchFeeMapper qrcodeBranchFeeMapper;
	
	public BigDecimal calMerchFee(final BigDecimal amount, final QrcodePayType payType, final QrcodeScanType scanType,
			final SysSettleType settleType, final BigDecimal dfFee, final SysT0RateType t0RateType,
			final BigDecimal t0MinFee, final String merchno) throws Exception {
		
		QrcodeMerchFee4Search fee4s = new QrcodeMerchFee4Search();
		fee4s.setMerchno(merchno);
		fee4s.setPayType(payType.ordinal());
		fee4s.setScanType(1 << scanType.ordinal());
		fee4s.setSettleType(1 << settleType.ordinal());
//		RequestBean<QrcodeMerchFee4Search> reqData = new RequestBean<QrcodeMerchFee4Search>();
//		reqData.setData(fee4s);
//		BigDecimal rate = merchFeeFeign.getRate(reqData).getData();
		BigDecimal rate = qrcodeMerchFeeService.list(fee4s).get(0).getRate();
		
		BigDecimal t1Rate = null;
		BigDecimal t0Rate = null;
		if (settleType == SysSettleType.T0 && t0RateType == SysT0RateType.INCREMENT) {
			fee4s.setSettleType(1 << SysSettleType.T1.ordinal());
			//reqData.getData().setSettleType(1 << SysSettleType.T1.ordinal());
			t1Rate = qrcodeMerchFeeService.list(fee4s).get(0).getRate();
			//t1Rate = merchFeeFeign.getRate(reqData).getData();
			t0Rate = rate;
		} else if (settleType == SysSettleType.T0) {
			t0Rate = rate;
		} else {
			t1Rate = rate;
		}
		return calFee(amount, settleType, dfFee, t1Rate, t0Rate, t0RateType, t0MinFee);
	}

	public BigDecimal calBranchFee(final BigDecimal amount, final QrcodePayType payType, final QrcodeScanType scanType,
			final SysSettleType settleType, final BigDecimal dfFee, final SysT0RateType t0RateType,
			final BigDecimal t0MinFee, String branchno) throws Exception {
		
		QrcodeBranchFee4Search fee4s = new QrcodeBranchFee4Search();
		fee4s.setBranchno(branchno);
		fee4s.setPayType(payType.ordinal());
		fee4s.setScanType(1 << scanType.ordinal());
		fee4s.setSettleType(1 << settleType.ordinal());
//		RequestBean<QrcodeBranchFee4Search> reqData = new RequestBean<QrcodeBranchFee4Search>();
//		reqData.setData(fee4s);
		BigDecimal rate = qrcodeBranchFeeMapper.list(fee4s).get(0).getRate();
		
		BigDecimal t1Rate = null;
		BigDecimal t0Rate = null;
		if (settleType == SysSettleType.T0 && t0RateType == SysT0RateType.INCREMENT) {
			//reqData.getData().setSettleType(1 << SysSettleType.T1.ordinal());
			fee4s.setSettleType(1 << SysSettleType.T1.ordinal());
			t1Rate = qrcodeBranchFeeMapper.list(fee4s).get(0).getRate();
			t0Rate = rate;
		} else if (settleType == SysSettleType.T0) {
			t0Rate = rate;
		} else {
			t1Rate = rate;
		}
		return calFee(amount, settleType, dfFee, t1Rate, t0Rate, t0RateType, t0MinFee);
	}

	public BigDecimal calChannelFee(final BigDecimal amount, final QrcodePayType payType,
			final QrcodeScanType scanType, final SysSettleType settleType, final BigDecimal dfFee,
			final SysT0RateType t0RateType, final BigDecimal t0MinFee, final String channelCode)
			throws Exception {
		QrcodeChannelFee4Search fee4s = new QrcodeChannelFee4Search();
		fee4s.setChannelCode(channelCode);
		fee4s.setPayType(payType.ordinal());
		fee4s.setScanType(1 << scanType.ordinal());
		fee4s.setSettleType(1 << settleType.ordinal());
		RequestBean<QrcodeChannelFee4Search> reqData = new RequestBean<QrcodeChannelFee4Search>();
		reqData.setData(fee4s);
		BigDecimal rate = qrcodeChannelFeeMapper.list(fee4s).get(0).getRate();
				//channelFeeFeign.getRate(reqData).getData();
		
		BigDecimal t1Rate = null;
		BigDecimal t0Rate = null;
		if (settleType == SysSettleType.T0 && t0RateType == SysT0RateType.INCREMENT) {
			//reqData.getData().setSettleType(1 << SysSettleType.T1.ordinal());
			fee4s.setSettleType(1 << SysSettleType.T1.ordinal());
			t1Rate = qrcodeChannelFeeMapper.list(fee4s).get(0).getRate();
					//channelFeeFeign.getRate(reqData).getData();
			t0Rate = rate;
		} else if (settleType == SysSettleType.T0) {
			t0Rate = rate;
		} else {
			t1Rate = rate;
		}
		return calFee(amount, settleType, dfFee, t1Rate, t0Rate, t0RateType, t0MinFee);
	}

	private BigDecimal calFee(BigDecimal amount, SysSettleType settleType, BigDecimal dfFee, BigDecimal t1Rate, BigDecimal t0Rate, SysT0RateType t0RateType,
			BigDecimal t0MinFee) throws ApiException {
		BigDecimal fee = null;
		if (settleType == SysSettleType.T1) {
			verifyRate(t1Rate);
			fee = amount.multiply(t1Rate);
		} else if (settleType == SysSettleType.T0) {
			verifyRate(t0Rate);
			if (t0RateType == SysT0RateType.INCREMENT) {// 增量方式计算
				verifyRate(t1Rate);
				fee = calFeeOnT0IncrementRate(amount, t1Rate, t0Rate, t0MinFee);
			} else {// 全量方式计算
				fee = amount.multiply(t0Rate);
			}
			fee = fee.add(dfFee);
		}
		fee = fee.setScale(2, BigDecimal.ROUND_UP);
		return fee;
	}

	/**
	 * 按T0增加费率计算手续费
	 * @author Moyq5
	 * @date 2017年9月15日
	 * @param amount 交易金额
	 * @param t1Rate T1费率
	 * @param t0Rate T0费率
	 * @param t0MinFee T0增量最小费用
	 * @return
	 */
	private BigDecimal calFeeOnT0IncrementRate(final BigDecimal amount, BigDecimal t1Rate, BigDecimal t0Rate, BigDecimal t0MinFee) {
		BigDecimal t0AddRate = t0Rate.subtract(t1Rate);
		BigDecimal t1Fee = amount.multiply(t1Rate);
		BigDecimal t0AddFee = amount.subtract(t1Fee).multiply(t0AddRate);
		if (t0AddFee.compareTo(t0MinFee) == -1) {
			t0AddFee = t0MinFee;
		}
		return t1Fee.add(t0AddFee);
	}
	
	private void verifyRate(BigDecimal rate) throws ApiException {
		if (rate == null) {
			log.error("费率尚未设置");
			throw new ApiException(ApiCode.C0007);
		}
		if (rate.compareTo(new BigDecimal("0.001")) == -1) {
			log.error("费率设置过低,<0.001");
			throw new ApiException(ApiCode.C0007);
		}
		if (rate.compareTo(new BigDecimal("0.1")) == 1) {
			log.error("费率设置过低,>0.1");
			throw new ApiException(ApiCode.C0007);
			
		}
	}
	
}