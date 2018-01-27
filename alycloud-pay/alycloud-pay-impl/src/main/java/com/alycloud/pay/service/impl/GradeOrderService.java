package com.alycloud.pay.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.channel.esyto.EsytoApiFactory;
import com.alycloud.channel.esyto.EsytoApiType;
import com.alycloud.channel.esyto.bean.NotifyCallbackResult;
import com.alycloud.channel.esyto.bean.Result;
import com.alycloud.channel.esyto.enums.OrderStatus;
import com.alycloud.channel.esyto.enums.RespCode;
import com.alycloud.channel.esyto.support.Config;
import com.alycloud.channel.esyto.support.client.NotifyCallback;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.entity.GradeOrder;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.enums.GradeOrderStatus;
import com.alycloud.modules.enums.SystemParamCode;
import com.alycloud.modules.search.GradeOrder4Search;
import com.alycloud.pay.api.IGradeOrderService;
import com.alycloud.pay.feign.AgentTransFeign;
import com.alycloud.pay.feign.MerchInfoFeign;
import com.alycloud.pay.feign.SystemParamFeign;
import com.alycloud.pay.mapper.GradeOrderMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 商户升级支付流水
 * @author Moyq5
 * @date 2017年10月30日
 */
@Service
@Slf4j
public class GradeOrderService implements IGradeOrderService {

	@Autowired
	private GradeOrderMapper gradeOrderMapper;
	@Autowired
	private MerchInfoFeign merchInfoFeign;
	@Autowired
	private SystemParamFeign systemParamFeign;
	@Autowired
	private AgentTransFeign agentTransFeign;
	
	@Override
	public void add(GradeOrder order) {
		gradeOrderMapper.add(order);
	}

	@Override
	public void mod(GradeOrder order) {
		gradeOrderMapper.mod(order);
	}

	@Override
	public GradeOrder getById(Integer id) {
		return gradeOrderMapper.getById(id);
	}

	@Override
	public List<GradeOrder> listByPage(GradeOrder4Search order4s) {
		return gradeOrderMapper.listByPage(order4s);
	}

	@Override
	public BigDecimal sumAmount(GradeOrder4Search order4s) {
		return gradeOrderMapper.sumAmount(order4s);
	}

	@Override
	public void notifyCallback(HttpServletRequest req) {
		//final String merchId = req.getParameter("payerMerchId");
		
		Config config = getConfig4Payee();
		NotifyCallback client = (NotifyCallback)EsytoApiFactory.getClient(EsytoApiType.NOTIFY_CALLBACK);
		client.setConfig(config);
		Result<NotifyCallbackResult> apiResult = client.execute(req);
		if (apiResult.getCode() != RespCode.SUCCESS) {
			log.warn("升级支付通知失败：{}", apiResult.getMsg());
			return;
		}
		NotifyCallbackResult apiResultData = apiResult.getData();
		String orderno = apiResultData.getOrderNo();
		OrderStatus status = apiResultData.getStatus();
		if (status != OrderStatus.SUCCESS) {
			log.warn("升级支付通知,订单未支付成功：{}", status);
			return;
		}
		paySuccess(orderno);
	}
	
	@Override
	public void paySuccess(String orderno) {
		
		GradeOrder4Search order4s = new GradeOrder4Search();
		order4s.setRefno(orderno);
		List<GradeOrder> list = gradeOrderMapper.listByPage(order4s);
		GradeOrder order = list.get(0);
		
		GradeOrder order4Mod = new GradeOrder();
		order4Mod.setId(order.getId());
		order4Mod.setStatus(GradeOrderStatus.SUCCESS.ordinal());
		gradeOrderMapper.mod(order4Mod);
		
		RequestBean<Integer> merchFeignData = new RequestBean<Integer>();
		merchFeignData.setData(order.getId());
		merchInfoFeign.upgradeByGradeOrderId(merchFeignData);
		
		RequestBean<Integer> agentTransData = new RequestBean<Integer>();
		agentTransData.setData(order.getId());
		agentTransFeign.addByGradeOrderId(agentTransData);
	}

	private Config getConfig4Payee() {
		
		RequestBean<String> pmData = new RequestBean<String>();
		pmData.setData(SystemParamCode.SYSTEM_PAYEE_MERCHNO.name().toLowerCase());
		String merchno = systemParamFeign.getByCode(pmData).getData().getValue();
		
		RequestBean<String> merchData = new RequestBean<String>();
		merchData.setData(merchno);
		MerchInfo merch = merchInfoFeign.getByMerchno(merchData).getData();
		
		Config config = new Config() {
			private Config config = EsytoApiFactory.getConfig();
			@Override
			public String getServerPath() {
				return config.getServerPath();
			}
			
			@Override
			public String getMerchNo() {
				return merchno;
			}
			
			@Override
			public String getKey() {
				return merch.getMerchKey();
			}
		};
		return config;
	}

}
