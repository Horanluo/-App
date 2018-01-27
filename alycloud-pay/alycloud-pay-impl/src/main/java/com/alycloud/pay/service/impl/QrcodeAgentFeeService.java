package com.alycloud.pay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.modules.entity.QrcodeAgentFee;
import com.alycloud.modules.search.QrcodeAgentFee4Search;
import com.alycloud.pay.api.IQrcodeAgentFeeService;
import com.alycloud.pay.mapper.QrcodeAgentFeeMapper;

/**
 * 代理商扫码费率Service
 * @author Moyq5
 * @date 2017年10月29日
 */
@Service
public class QrcodeAgentFeeService implements IQrcodeAgentFeeService {

	@Autowired
	private QrcodeAgentFeeMapper qrcodeAgentFeeMapper;
	
	@Override
	public void delByAgentno(String agentno) {
		qrcodeAgentFeeMapper.delByAgentno(agentno);
	}

	@Override
	public void batchAdd(List<QrcodeAgentFee> data) {
		qrcodeAgentFeeMapper.batchAdd(data);
	}

	@Override
	public List<QrcodeAgentFee> list(QrcodeAgentFee4Search fee4s) {
		return qrcodeAgentFeeMapper.listByPage(fee4s);
	}

}
