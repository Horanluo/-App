package com.alycloud.channel.yufu.qrcode.bean;

/**
 * 查询（包括消费、撤销、退款）接口－请求参数
 * @author Moyq5
 * @date 2017年8月29日
 */
public class OrderQueryData extends AbstractData {

	/**
	 * 商户订单号或平台方流水号，长30，非必填，<br>
	 */
	private String orderNo;
	/**
	 * 终端编码，长200，非必填，<br>
	 */
	private String termNo;
	/**
	 * 商户请求交易的流水号，长32，非必填，<br>
	 */
	private String orgReqId;
	/**
	 * 平台方唯一交易请求流水号，长20，非必填，<br>
	 */
	private String orgTransId;
	/**
	 * 操作员编号，长30，非必填，<br>
	 */
	private String operatorId;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTermNo() {
		return termNo;
	}
	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}
	public String getOrgReqId() {
		return orgReqId;
	}
	public void setOrgReqId(String orgReqId) {
		this.orgReqId = orgReqId;
	}
	public String getOrgTransId() {
		return orgTransId;
	}
	public void setOrgTransId(String orgTransId) {
		this.orgTransId = orgTransId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	@Override
	public String toString() {
		return "OrderQueryData [orderNo=" + orderNo + ", termNo=" + termNo + ", orgReqId=" + orgReqId + ", orgTransId="
				+ orgTransId + ", operatorId=" + operatorId + ", toString()=" + super.toString() + "]";
	}
}
