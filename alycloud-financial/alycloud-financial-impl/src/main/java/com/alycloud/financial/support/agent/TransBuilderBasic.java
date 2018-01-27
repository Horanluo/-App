package com.alycloud.financial.support.agent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.AgentTransTransStatus;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.financial.feign.AgentInfoFeign;
import com.alycloud.financial.feign.AgentRelateFeign;
import com.alycloud.financial.feign.AgentTransFeign;
import com.alycloud.financial.feign.BranchInfoFeign;
import com.alycloud.financial.feign.MerchInfoFeign;
import com.alycloud.financial.feign.MerchUserFeign;
import com.alycloud.financial.feign.QrcodeAgentFeeFeign;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentRelate;
import com.alycloud.modules.entity.AgentTrans;
import com.alycloud.modules.entity.BranchInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.enums.AgentTransDivideType;
import com.alycloud.modules.enums.AgentTransType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.enums.SysTransType;
import com.alycloud.modules.search.AgentRelate4Search;
import com.alycloud.modules.search.AgentTrans4Search;
import com.alycloud.modules.search.MerchInfo4Search;
import com.alycloud.modules.search.MerchUser4Search;
import com.alycloud.modules.search.QrcodeAgentFee4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年11月9日
 */
public abstract class TransBuilderBasic implements TransBuilder {

	private AgentTransFeign agentTransFeign;
	private MerchInfoFeign merchInfoFeign;
	private AgentRelateFeign agentRelateFeign;
	private MerchUserFeign merchUserFeign;
	private AgentInfoFeign agentInfoFeign;
	private QrcodeAgentFeeFeign qrcodeAgentFeeFeign;
	private BranchInfoFeign branchInfoFeign;
	
	private TransOrder order;
	private SysTransType transType;
	public TransBuilderBasic(SysTransType transType) {
		this.transType = transType;
		agentTransFeign = SpringContextUtils.getBean(AgentTransFeign.class);
		merchInfoFeign = SpringContextUtils.getBean(MerchInfoFeign.class);
		agentRelateFeign = SpringContextUtils.getBean(AgentRelateFeign.class);
		merchUserFeign = SpringContextUtils.getBean(MerchUserFeign.class);
		agentInfoFeign = SpringContextUtils.getBean(AgentInfoFeign.class);
		qrcodeAgentFeeFeign = SpringContextUtils.getBean(QrcodeAgentFeeFeign.class);
		branchInfoFeign = SpringContextUtils.getBean(BranchInfoFeign.class);
	}
	
	protected abstract TransOrder getOrder();
	
	public List<AgentTrans> build() throws TransException {
		order = getOrder();
		AgentTrans4Search trans4s = new AgentTrans4Search();
		trans4s.setRefno(order.getOrderno());
		trans4s.setTransType(transType.ordinal());
		RequestBean<AgentTrans4Search> transData4s = new RequestBean<AgentTrans4Search>();
		transData4s.setData(trans4s);
		Integer count = agentTransFeign.count(transData4s).getData();
		if (count > 0) {
			throw new TransExistsException(transType.getText() + "交易分润已经存在，订单号: " + order.getOrderno());
		}
		
		RequestBean<String> merchData = new RequestBean<String>();
		merchData.setData(order.getMerchno());
		MerchInfo merch = merchInfoFeign.getByMerchno(merchData).getData();
		
		RequestBean<String> branchData = new RequestBean<String>();
		branchData.setData(merch.getBranchno());
		BranchInfo branch = branchInfoFeign.getByBranchno(branchData).getData();
		
		AgentRelate4Search relate4s = new AgentRelate4Search();
		relate4s.setAgentno(merch.getSuperAgent());
		RequestBean<AgentRelate4Search> relateData4s = new RequestBean<AgentRelate4Search>();
		relateData4s.setData(relate4s);
		List<AgentRelate> relates = agentRelateFeign.listByPage(relateData4s).getData();
		List<AgentTrans> newTransList = new ArrayList<AgentTrans>();
		
		if (relates.size() > 1) {
			AgentRelate relate = relates.get(0);
			relates.remove(0);
			relates.add(relate);
		}
		
		Integer preGradeType = getGradeType(merch);
		Integer curGradeType = null;
		BigDecimal preCostFee = order.getTotalFee();
		for (int i = relates.size() - 1; i >= relates.size() - 2 && i > 0; i--) {// 最多两级分润
			AgentRelate relate = relates.get(i);
			String agentno = relate.getSuperAgentno();
			
			RequestBean<String> agentData = new RequestBean<String>();
			agentData.setData(agentno);
			AgentInfo agent = agentInfoFeign.getByAgentno(agentData).getData();
			
			curGradeType = getGradeType(agent);
			if (curGradeType < preGradeType) {
				break;
			}
			preGradeType = curGradeType;
			
			AgentTrans agentTrans = build4Agent(agent, preCostFee);
			newTransList.add(agentTrans);
			
			preCostFee = agentTrans.getCostFee();
		}
		
		if (relates.size() > 1) {
			preCostFee = getAgentCostFee(relates.get(1).getSuperAgentno());
		} else {
			preCostFee = order.getTotalFee();
		}
		
		AgentTrans agentTrans = build4Branch(branch, preCostFee);
		newTransList.add(agentTrans);
		
		return newTransList;
	}

	private Integer getGradeType(MerchInfo merch) {
		MerchUser4Search user4s = new MerchUser4Search();
		user4s.setMerchId(merch.getId());
		RequestBean<MerchUser4Search> userData = new RequestBean<MerchUser4Search>();
		userData.setData(user4s);
		MerchUser merchUser = merchUserFeign.listByPage(userData).getData().get(0);
		return merchUser.getUserRank();
	}
	
	private Integer getGradeType(AgentInfo agent) {
		MerchInfo4Search merch4s = new MerchInfo4Search();
		merch4s.setMobile(agent.getMobile());
		RequestBean<MerchInfo4Search> merchData4s = new RequestBean<MerchInfo4Search>();
		merchData4s.setData(merch4s);
		MerchInfo merch = merchInfoFeign.listByPage(merchData4s).getData().get(0);
		return getGradeType(merch);
	}
	
	private AgentTrans build4Agent(AgentInfo agent, BigDecimal preCostFee) {
		BigDecimal costFee = getAgentCostFee(agent.getAgentno());
		BigDecimal totalAgentFee = order.getTotalFee().subtract(costFee);
		BigDecimal agentFee = preCostFee.subtract(costFee);
		if (agentFee.compareTo(BigDecimal.ZERO) == -1) {
			agentFee = BigDecimal.ZERO;
		}
		
		return build(agent, costFee, totalAgentFee, agentFee);
	}
	
	private AgentTrans build4Branch(BranchInfo branch, BigDecimal preCostFee) {
		BigDecimal costFee = order.getBranchFee();
		BigDecimal totalAgentFee = order.getTotalFee().subtract(costFee);
		BigDecimal agentFee = preCostFee.subtract(costFee);
		if (agentFee.compareTo(BigDecimal.ZERO) == -1) {
			agentFee = BigDecimal.ZERO;
		}
		
		AgentInfo agent = new AgentInfo();
		agent.setAgentLevel(0);
		agent.setAgentName(branch.getBranchName());
		agent.setAgentno(branch.getBranchno());
		agentFee = agentFee.add(order.getPaymentFee());
		return build(agent, costFee, totalAgentFee, agentFee);
	}
	
	private BigDecimal getAgentCostFee(String agentno) {
		BigDecimal rate = getAgentRate(agentno);
		BigDecimal costFee = order.getTransAmount().multiply(rate);
		if (order.getSettleType() == SysSettleType.T0.ordinal()) {
			costFee = costFee.add(order.getPaymentFee());
		}
		return costFee;
	}
	
	protected BigDecimal getAgentRate(String agentno) {
		QrcodeAgentFee4Search fee4s = new QrcodeAgentFee4Search();
		fee4s.setAgentno(agentno);
		fee4s.setPayType(order.getPayType());
		fee4s.setScanType(1<<order.getScanType());
		fee4s.setSettleType(1<<order.getSettleType());
		RequestBean<QrcodeAgentFee4Search> feeData4s = new RequestBean<QrcodeAgentFee4Search>();
		feeData4s.setData(fee4s);
		return qrcodeAgentFeeFeign.getRate(feeData4s).getData();
	}
	
	private AgentTrans build(AgentInfo agent, BigDecimal costFee, BigDecimal totalAgentFee, BigDecimal agentFee) {
		AgentTrans newTrans = new AgentTrans();
		newTrans.setAgentFee(agentFee);
		newTrans.setAgentLevel(agent.getAgentLevel() + 1);
		newTrans.setAgentName(agent.getAgentName());
		newTrans.setAgentno(agent.getAgentno());
		newTrans.setAmount(order.getTransAmount());
		newTrans.setBranchName(order.getBranchName());
		newTrans.setBranchno(order.getBranchno());
		newTrans.setChannelFee(order.getChannelFee());
		newTrans.setCostFee(costFee);
		newTrans.setDivide(100);
		newTrans.setDivideType(AgentTransDivideType.FEE.ordinal());
		newTrans.setLiquidDate(DateFormat.DATE.format());
		newTrans.setLiquidStatus(3);
		newTrans.setMerchName(order.getMerchName());
		newTrans.setMerchno(order.getMerchno());
		//superAgentTrans.setPayFee(payFee);
		newTrans.setTransType(transType.ordinal());
		newTrans.setRefno(order.getOrderno());
		newTrans.setSettleDate(null);
		//superAgentTrans.setT0AgentFee(t0AgentFee);
		newTrans.setTotalAgentFee(totalAgentFee);
		newTrans.setTotalFee(order.getTotalFee());
		newTrans.setTransDate(DateFormat.DATE.format());
		newTrans.setTransTime(DateFormat.TIME.format());
		newTrans.setTransStatus(AgentTransTransStatus.FORCE_SUCCESS.ordinal());
		newTrans.setTypeMemo("普通分润");
		newTrans.setType(AgentTransType.COMMON.ordinal());
		return newTrans;
	}
	
}
