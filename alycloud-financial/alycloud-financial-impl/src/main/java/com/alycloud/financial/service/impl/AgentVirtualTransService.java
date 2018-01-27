package com.alycloud.financial.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.financial.api.IAgentVirtualTransService;
import com.alycloud.financial.mapper.AgentVirtualTransMapper;
import com.alycloud.modules.entity.AgentVirtualTrans;

/**
 * 代理商提现流水Service实现类
 * @author Moyq5
 * @date 2017年10月24日
 */
@Service
public class AgentVirtualTransService implements IAgentVirtualTransService {

	@Autowired
	private AgentVirtualTransMapper agentVirtualTransMapper;
	
	/**
	 * 生成提现流水号
	 * @author Moyq5
	 * @date 2017年10月24日
	 */
	public String genRefno() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String date = sdf.format(new Date());
		String refno = agentVirtualTransMapper.genRefno();
		refno = refno.substring(refno.length() - 9);
		String rand = Integer.toString((int)(100 + 900 * Math.random()));
		return "D" + date + refno + rand;
	}

	public AgentVirtualTrans add(AgentVirtualTrans trans) {
		agentVirtualTransMapper.add(trans);
		return trans;
	}

}
