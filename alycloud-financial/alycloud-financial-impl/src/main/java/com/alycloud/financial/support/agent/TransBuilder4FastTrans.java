package com.alycloud.financial.support.agent;

import java.math.BigDecimal;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.financial.feign.AgentInfoFeign;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.FastTrans;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.QrcodeScanType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.enums.SysTransType;

/**
 * 
 * @author Moyq5
 * @date 2017年11月7日
 */
public class TransBuilder4FastTrans extends TransBuilderBasic {
	
	private AgentInfoFeign agentInfoFeign;
	
	TransOrder order;
	public TransBuilder4FastTrans(FastTrans fastTrans) {
		super(SysTransType.FAST);
		order = new TransOrderImpl(fastTrans);
		agentInfoFeign = SpringContextUtils.getBean(AgentInfoFeign.class);
	}

	@Override
	protected TransOrder getOrder() {
		return order;
	}

	@Override
	protected BigDecimal getAgentRate(String agentno) {
		RequestBean<String> agentData = new RequestBean<String>();
		agentData.setData(agentno);
		AgentInfo agent = agentInfoFeign.getByAgentno(agentData).getData();
		if (order.getSettleType() == SysSettleType.T0.ordinal()) {
			return agent.getFastpayRateT0();
		} else if (order.getSettleType() == SysSettleType.T1.ordinal()) {
			return agent.getFastpayRateT1();
		}
		return null;
	}
	
	private class TransOrderImpl implements TransOrder {

		private FastTrans order;
		public TransOrderImpl(FastTrans order) {
			this.order = order;
		}
		@Override
		public String getMerchno() {
			return order.getMerchno();
		}

		@Override
		public BigDecimal getTotalFee() {
			return order.getMerchFee();
		}

		@Override
		public String getOrderno() {
			return order.getOrderno();
		}

		@Override
		
		public BigDecimal getBranchFee() {
			return order.getBranchFee();
		}

		@Override
		public Integer getPayType() {
			return QrcodePayType.FAST.ordinal();
		}

		@Override
		public int getScanType() {
			return QrcodeScanType.FAST.ordinal();
		}

		@Override
		public int getSettleType() {
			return order.getSettleType();
		}

		@Override
		public BigDecimal getTransAmount() {
			return order.getAmount();
		}

		@Override
		public BigDecimal getPaymentFee() {
			return order.getFee();
		}

		@Override
		public String getBranchName() {
			return order.getBranchName();
		}

		@Override
		public String getBranchno() {
			return order.getBranchno();
		}

		@Override
		public BigDecimal getChannelFee() {
			return order.getChannelFee();
		}

		@Override
		public String getMerchName() {
			return order.getMerchName();
		}
		
	}

}
