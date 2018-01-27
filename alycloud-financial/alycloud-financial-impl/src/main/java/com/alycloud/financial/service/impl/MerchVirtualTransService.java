package com.alycloud.financial.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.financial.api.IMerchVirtualTransService;
import com.alycloud.financial.mapper.MerchVirtualTransMapper;
import com.alycloud.modules.entity.MerchVirtualTrans;

/**
 * 商户提现流水Service实现类
 * @author Moyq5
 * @date 2017年11月13日
 */
@Service
public class MerchVirtualTransService implements IMerchVirtualTransService {

	@Autowired
	private MerchVirtualTransMapper merchVirtualTransMapper;
	
	@Override
	public String genRefno() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String date = sdf.format(new Date());
		String refno = merchVirtualTransMapper.genRefno();
		refno = refno.substring(refno.length() - 9);
		String rand = Integer.toString((int)(100 + 900 * Math.random()));
		return "D" + date + refno + rand;
	}

	@Override
	public MerchVirtualTrans add(MerchVirtualTrans trans) {
		merchVirtualTransMapper.add(trans);
		return trans;
	}

}
