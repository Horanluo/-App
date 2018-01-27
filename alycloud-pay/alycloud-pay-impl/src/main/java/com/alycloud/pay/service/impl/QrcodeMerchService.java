package com.alycloud.pay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.modules.entity.QrcodeMerch;
import com.alycloud.modules.search.QrcodeMerch4Search;
import com.alycloud.modules.search.QrcodeMerch4Search4Pay;
import com.alycloud.pay.api.IQrcodeMerchService;
import com.alycloud.pay.mapper.QrcodeMerchMapper;

/**
 * 渠道商户
 * @author Moyq5
 * @date 2017年11月11日
 */
@Service
public class QrcodeMerchService implements IQrcodeMerchService {

	@Autowired
	private QrcodeMerchMapper qrcodeMerchMapper;

	@Override
	public List<QrcodeMerch> listByPage(QrcodeMerch4Search merch4s) {
		return qrcodeMerchMapper.listByPage(merch4s);
	}

	@Override
	public List<QrcodeMerch> list4Pay(QrcodeMerch4Search4Pay merch4s) {
		return qrcodeMerchMapper.list4Pay(merch4s);
	}
	
}
