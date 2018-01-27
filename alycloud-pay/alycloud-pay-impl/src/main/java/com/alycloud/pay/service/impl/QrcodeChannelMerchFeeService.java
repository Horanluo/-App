package com.alycloud.pay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.modules.entity.QrcodeChannelMerchFee;
import com.alycloud.modules.search.QrcodeChannelMerchFee4Search;
import com.alycloud.pay.api.IQrcodeChannelMerchFeeService;
import com.alycloud.pay.mapper.QrcodeChannelMerchFeeMapper;

/**
 * 渠道商户扫码费率Service
 * @author Moyq5
 * @date 2017年11月16日
 */
@Service
public class QrcodeChannelMerchFeeService implements IQrcodeChannelMerchFeeService {

	@Autowired
	private QrcodeChannelMerchFeeMapper qrcodeChannelMerchFeeMapper;

	@Override
	public List<QrcodeChannelMerchFee> list(QrcodeChannelMerchFee4Search fee4s) {
		return qrcodeChannelMerchFeeMapper.list(fee4s);
	}
	
}
