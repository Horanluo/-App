package com.alycloud.financial.support.agent;

import java.math.BigDecimal;

import com.alycloud.modules.entity.QrcodeTrans;
import com.alycloud.modules.enums.SysTransType;

/**
 * 
 * @author Moyq5
 * @date 2017年11月7日
 */
public class TransBuilder4QrcodeTrans extends TransBuilderBasic {
	
	TransOrder order;
	public TransBuilder4QrcodeTrans(QrcodeTrans qrcodeTrans) {
		super(SysTransType.QRCODE);
		order = new TransOrderImpl(qrcodeTrans);
	}

	@Override
	protected TransOrder getOrder() {
		return order;
	}
	
	private class TransOrderImpl implements TransOrder {

		private QrcodeTrans order;
		public TransOrderImpl(QrcodeTrans order) {
			this.order = order;
		}
		@Override
		public String getMerchno() {
			return order.getMerchno();
		}

		@Override
		public BigDecimal getTotalFee() {
			return order.getTotalFee();
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
			return order.getPayType();
		}

		@Override
		public int getScanType() {
			return order.getScanType();
		}

		@Override
		public int getSettleType() {
			return order.getSettleType();
		}

		@Override
		public BigDecimal getTransAmount() {
			return order.getTransAmount();
		}

		@Override
		public BigDecimal getPaymentFee() {
			return order.getPaymentFee();
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
