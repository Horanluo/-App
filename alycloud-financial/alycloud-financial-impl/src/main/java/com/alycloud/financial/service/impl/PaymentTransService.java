package com.alycloud.financial.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.financial.api.IPaymentTransService;
import com.alycloud.financial.mapper.PaymentTransMapper;
import com.alycloud.modules.entity.PaymentTrans;

/**
 * 代付流水Service实现类
 * @author Moyq5
 * @date 2017年10月24日
 */
@Service
public class PaymentTransService implements IPaymentTransService {

	@Autowired
	private PaymentTransMapper paymentTransMapper;
	
	@Override
	public String genRefno() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String date = sdf.format(new Date());
		String refno = paymentTransMapper.genRefno();
		refno = refno.substring(refno.length() - 9);
		String rand = Integer.toString((int)(100 + 900 * Math.random()));
		return "P" + date + refno + rand;
	}

	@Override
	public PaymentTrans add(PaymentTrans trans) {
		paymentTransMapper.add(trans);
		return trans;
	}

}
