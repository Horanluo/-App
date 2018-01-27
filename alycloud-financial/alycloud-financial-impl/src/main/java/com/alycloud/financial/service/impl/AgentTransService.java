package com.alycloud.financial.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.financial.api.IAgentTransService;
import com.alycloud.financial.feign.GradeFeign;
import com.alycloud.financial.feign.GradeOrderFeign;
import com.alycloud.financial.feign.MerchInfoFeign;
import com.alycloud.financial.feign.MerchUserFeign;
import com.alycloud.financial.feign.QrcodeTransFeign;
import com.alycloud.financial.mapper.AgentTransMapper;
import com.alycloud.financial.mapper.FastTransMapper;
import com.alycloud.financial.support.agent.TransBuilder;
import com.alycloud.financial.support.agent.TransBuilderFactory;
import com.alycloud.financial.support.agent.TransExistsException;
import com.alycloud.modules.entity.AgentTrans;
import com.alycloud.modules.entity.FastTrans;
import com.alycloud.modules.entity.GradeOrder;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.entity.QrcodeTrans;
import com.alycloud.modules.enums.AgentTransType;
import com.alycloud.modules.search.AgentTrans4Search;
import com.alycloud.modules.search.FastTrans4Search;
import com.alycloud.modules.search.GradeOrder4Search;
import com.alycloud.modules.search.MerchUser4Search;
import com.alycloud.modules.search.QrcodeTrans4Search;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理商分润Service实现类
 * @author Moyq5
 * @date 2017年11月4日
 */
@Service
@Slf4j
public class AgentTransService implements IAgentTransService {

	@Autowired
	private AgentTransMapper agentTransMapper;
	@Autowired
	private GradeOrderFeign gradeOrderFeign;
	@Autowired
	private QrcodeTransFeign qrcodeTransFeign;
	@Autowired
	private FastTransMapper fastTransMapper;
	@Autowired
	private MerchUserFeign merchUserFeign;
	@Autowired
	private MerchInfoFeign merchInfoFeign;
	@Autowired
	private GradeFeign gradeFeign;
	
	@Override
	public BigDecimal sum(AgentTrans4Search trans4s) {
		return agentTransMapper.sum(trans4s);
	}

	@Override
	public List<AgentTrans> listByGroupByDate(AgentTrans4Search trans4s) {
		return agentTransMapper.listByGroupByDate(trans4s);
	}

	@Override
	public List<AgentTrans> listByPage(AgentTrans4Search trans4s) {
		return agentTransMapper.listByPage(trans4s);
	}

	@Override
	public Integer countRecord(AgentTrans4Search trans4s) {
		return agentTransMapper.countRecord(trans4s);
	}

	@Override
	public synchronized void addByGradeOrderId(Integer id) throws Exception {
		RequestBean<Integer> gradeOrderData = new RequestBean<Integer>();
		gradeOrderData.setData(id);
		GradeOrder gradeOrder = gradeOrderFeign.getById(gradeOrderData).getData();
		if (null == gradeOrder) {
			log.warn("升级订单[{}]不存在", id);
			return;
		}
		
		TransBuilder builder = TransBuilderFactory.getBuilder(gradeOrder);
		List<AgentTrans> transList = null;
		try {
			transList = builder.build();
		} catch (TransExistsException e) {
			log.warn("升级[{}]分润已经存在", gradeOrder.getRefno());
			return;
		}
		
		agentTransMapper.addList(transList);
		
	}

	@Override
	public synchronized void addByQrcodeOrderno(String orderno) throws Exception {
		QrcodeTrans4Search qr4s = new QrcodeTrans4Search();
		qr4s.setOrderno(orderno);
		RequestBean<QrcodeTrans4Search> qrData = new RequestBean<QrcodeTrans4Search>();
		qrData.setData(qr4s);
		List<QrcodeTrans> list = qrcodeTransFeign.listByPage(qrData).getData();
		if (list.size() == 0) {
			log.warn("二维码交易[{}]不存在", orderno);
			return;
		}
		QrcodeTrans qrcodeTrans = list.get(0);
		
		TransBuilder builder = TransBuilderFactory.getBuilder(qrcodeTrans);
		List<AgentTrans> transList = null;
		try {
			transList = builder.build();
		} catch (TransExistsException e) {
			log.warn("二维码交易[{}]分润已经存在", qrcodeTrans.getOrderno());
			return;
		}
		
		agentTransMapper.addList(transList);
		
	}

	@Override
	public synchronized void addByFastOrderno(String orderno) throws Exception {
		FastTrans4Search ft4s = new FastTrans4Search();
		ft4s.setOrderno(orderno);
		List<FastTrans> list = fastTransMapper.listByPage(ft4s);
		if (list.size() == 0) {
			log.warn("快捷交易[{}]不存在", orderno);
			return;
		}
		FastTrans fastTrans = list.get(0);
		
		TransBuilder builder = TransBuilderFactory.getBuilder(fastTrans);
		List<AgentTrans> transList = null;
		try {
			transList = builder.build();
		} catch (TransExistsException e) {
			log.warn("快捷交易[{}]分润已经存在", fastTrans.getOrderno());
			return;
		}
		
		agentTransMapper.addList(transList);
		
	}

	
	@Override
	public List<Map<String, Object>> listDetail(AgentTrans4Search trans4s) {
		List<AgentTrans> transList = agentTransMapper.listByPage(trans4s);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Integer> merchMap = new HashMap<String, Integer>();
		Map<Integer, MerchUser> userMap = new HashMap<Integer, MerchUser>();
		Map<Integer, String> gradeMap = new HashMap<Integer, String>();
		for (AgentTrans trans: transList) {
			Map<String, Object> map = new HashMap<String, Object>();
			list.add(map);
			map.put("refno", trans.getRefno());
			//map.put("transDate", trans.getTransDate());
			map.put("transTime", trans.getTransDate()+" "+trans.getTransTime());
			map.put("agentFee", trans.getAgentFee());
			map.put("type", trans.getType());
			Integer merchId = null;
			if (trans.getType() == AgentTransType.COMMON.ordinal()) {
				if (null == (merchId = merchMap.get(trans.getMerchno()))) {
					RequestBean<String> merchData = new RequestBean<String>();
					merchData.setData(trans.getMerchno());
					merchId = merchInfoFeign.getByMerchno(merchData).getData().getId();
					merchMap.put(trans.getMerchno(), merchId);
				}
			} else if (trans.getType() == AgentTransType.UPGRADE.ordinal()) {
				GradeOrder4Search order4s = new GradeOrder4Search();
				order4s.setRefno(trans.getRefno());
				RequestBean<GradeOrder4Search> orderData4s = new RequestBean<GradeOrder4Search>();
				orderData4s.setData(order4s);
				GradeOrder gradeOrder = gradeOrderFeign.listByPage(orderData4s).getData().get(0);
				merchId = gradeOrder.getMerchId();
			} else {
				log.warn("未知分润类型：{}", trans.getType());
			}
			
			MerchUser merchUser = null;
			if (null == (merchUser = userMap.get(merchId))) {
				MerchUser4Search user4s = new MerchUser4Search();
				user4s.setMerchId(merchId);
				RequestBean<MerchUser4Search> userData4s = new RequestBean<MerchUser4Search>();
				userData4s.setData(user4s);
				merchUser = merchUserFeign.listByPage(userData4s).getData().get(0);
				userMap.put(merchId, merchUser);
			}
			
			String gradeName = null;
			if (null == (gradeName = gradeMap.get(merchUser.getUserRank()))) {
				RequestBean<Integer> gradeData = new RequestBean<Integer>();
				gradeData.setData(merchUser.getUserRank());
				gradeName = gradeFeign.getByGradeType(gradeData).getData().getGradeName();
				gradeMap.put(merchUser.getUserRank(), gradeName);
			}
			
			String a = merchUser.getTrueName();
			String trueName = a.substring(0, 1) + (a.substring(1).replaceAll("\\S", "*"));
			String b = merchUser.getLoginName();
			String loginName = b.substring(0, 3) + (b.substring(3, b.length() - 4).replaceAll("\\d", "*")) + (b.substring(b.length() - 4));
			
			map.put("trueName", trueName);
			map.put("loginName", loginName);
			map.put("gradeName", gradeName);
		}
		return list;
	}

}
