package com.alycloud.pay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.modules.entity.QrcodeMerchFee;
import com.alycloud.modules.search.QrcodeMerchFee4Search;
import com.alycloud.pay.api.IQrcodeMerchFeeService;
import com.alycloud.pay.mapper.QrcodeMerchFeeMapper;

/**
 * 商户扫码费率Service
 * @author Moyq5
 * @date 2017年10月29日
 */
@Service
public class QrcodeMerchFeeService implements IQrcodeMerchFeeService {

	@Autowired
	private QrcodeMerchFeeMapper qrcodeMerchFeeMapper;
	
	@Override
	public void delByMerchno(String merchno) {
		qrcodeMerchFeeMapper.delByMerchno(merchno);
	}

	@Override
	public void addList(List<QrcodeMerchFee> data) {
		qrcodeMerchFeeMapper.addList(data);
	}

	@Override
	public List<QrcodeMerchFee> list(QrcodeMerchFee4Search data) {
		return qrcodeMerchFeeMapper.list(data);
	}

}
