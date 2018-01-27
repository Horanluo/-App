package com.alycloud.financial.support.agent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.AgentTransTransStatus;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.financial.feign.AgentInfoFeign;
import com.alycloud.financial.feign.AgentTransFeign;
import com.alycloud.financial.feign.BranchInfoFeign;
import com.alycloud.financial.feign.GradeFeign;
import com.alycloud.financial.feign.MerchInfoFeign;
import com.alycloud.financial.feign.MerchUserFeign;
import com.alycloud.financial.feign.QrcodeOrderFeign;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentTrans;
import com.alycloud.modules.entity.BranchInfo;
import com.alycloud.modules.entity.Grade;
import com.alycloud.modules.entity.GradeOrder;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.entity.QrcodeOrder;
import com.alycloud.modules.enums.AgentTransDivideType;
import com.alycloud.modules.enums.AgentTransType;
import com.alycloud.modules.enums.SysTransType;
import com.alycloud.modules.search.AgentTrans4Search;
import com.alycloud.modules.search.MerchInfo4Search;
import com.alycloud.modules.search.MerchUser4Search;
import com.alycloud.modules.search.QrcodeOrder4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年11月6日
 */
public class TransBuilder4GradeOrder implements TransBuilder {
	private AgentTransFeign agentTransFeign;
	private MerchInfoFeign merchInfoFeign;
	private QrcodeOrderFeign qrcodeOrderFeign;
	private BranchInfoFeign branchInfoFeign;
	private AgentInfoFeign agentInfoFeign;
	private MerchUserFeign merchUserFeign;
	private GradeFeign gradeFeign;
	private GradeOrder gradeOrder;
	public TransBuilder4GradeOrder(GradeOrder gradeOrder) {
		this.gradeOrder = gradeOrder;
		agentTransFeign = SpringContextUtils.getBean(AgentTransFeign.class);
		merchInfoFeign = SpringContextUtils.getBean(MerchInfoFeign.class);
		qrcodeOrderFeign = SpringContextUtils.getBean(QrcodeOrderFeign.class);
		branchInfoFeign = SpringContextUtils.getBean(BranchInfoFeign.class);
		agentInfoFeign = SpringContextUtils.getBean(AgentInfoFeign.class);
		merchUserFeign = SpringContextUtils.getBean(MerchUserFeign.class);
		gradeFeign = SpringContextUtils.getBean(GradeFeign.class);
	}
	@Override
	public List<AgentTrans> build() throws TransException {
		String orderno = gradeOrder.getRefno();
		
		AgentTrans4Search trans4s = new AgentTrans4Search();
		trans4s.setDivide(SysTransType.QRCODE.ordinal());
		trans4s.setRefno(orderno);
		RequestBean<AgentTrans4Search> transData = new RequestBean<AgentTrans4Search>();
		transData.setData(trans4s);
		List<AgentTrans> transList = agentTransFeign.listByPage(transData).getData();
		if (transList.size() > 0) {
			throw new TransExistsException("升级订单["+ orderno +"]分润已经存在");
		}
		
		Integer merchId = gradeOrder.getMerchId();
		RequestBean<Integer> merchData = new RequestBean<Integer>();
		merchData.setData(merchId);
		MerchInfo merch = merchInfoFeign.getById(merchData).getData();
		
		QrcodeOrder4Search order4s = new QrcodeOrder4Search();
		order4s.setOrderno(orderno);
		RequestBean<QrcodeOrder4Search> orderData = new RequestBean<QrcodeOrder4Search>();
		orderData.setData(order4s);
		QrcodeOrder qrcodeOrder = qrcodeOrderFeign.listByPage(orderData).getData().get(0);
		
		RequestBean<String> branchData = new RequestBean<String>();
		branchData.setData(merch.getBranchno());
		BranchInfo branch = branchInfoFeign.getByBranchno(branchData).getData();
		
		String superAgentno = merch.getSuperAgent();
		RequestBean<String> agentData = new RequestBean<String>();
		agentData.setData(superAgentno);
		AgentInfo superAgent = agentInfoFeign.getByAgentno(agentData).getData();
		
		transList = new ArrayList<AgentTrans>();
		Integer divide = 0;
		
		if (null != superAgent) {
			String superMobile = superAgent.getMobile();
			MerchInfo4Search merch4s = new MerchInfo4Search();
			merch4s.setMobile(superMobile);
			RequestBean<MerchInfo4Search> merchData4s = new RequestBean<MerchInfo4Search>();
			merchData4s.setData(merch4s);
			MerchInfo superMerch = merchInfoFeign.listByPage(merchData4s).getData().get(0);
			
			Integer superMerchId = superMerch.getId();
			MerchUser4Search user4s = new MerchUser4Search();
			user4s.setMerchId(superMerchId);
			RequestBean<MerchUser4Search> userData4s = new RequestBean<MerchUser4Search>();
			userData4s.setData(user4s);
			MerchUser superUser = merchUserFeign.listByPage(userData4s).getData().get(0);
			
			Integer superGradeType = superUser.getUserRank();
			RequestBean<Integer> gradeData4s = new RequestBean<Integer>();
			gradeData4s.setData(superGradeType);
			Grade superGrade = gradeFeign.getByGradeType(gradeData4s).getData();
			
			divide = superGrade.getPercent();
			
			AgentTrans trans4Agent = buildAgentTrans(merch, superAgent, branch, qrcodeOrder,divide);
			transList.add(trans4Agent);
		}
		
		AgentInfo branchAgent = new AgentInfo();
		branchAgent.setAgentLevel(0);
		branchAgent.setAgentName(branch.getBranchName());
		branchAgent.setAgentno(branch.getBranchno());
		
		AgentTrans trans4Branch = buildAgentTrans(merch, branchAgent, branch, qrcodeOrder,100-divide);
		transList.add(trans4Branch);
		return transList;
	}
	
	private AgentTrans buildAgentTrans(MerchInfo merch, AgentInfo agent, BranchInfo branch, 
			QrcodeOrder qrcodeOrder, Integer divide) {
		BigDecimal percent = BigDecimal.valueOf(divide).divide(new BigDecimal("100"));
		BigDecimal transAmount = qrcodeOrder.getAmount();
		BigDecimal totalFee = qrcodeOrder.getMerchFee();
		BigDecimal divideAmount = transAmount.subtract(totalFee);
		BigDecimal totalAgentFee = transAmount.multiply(percent);
		BigDecimal agentFee = divideAmount.multiply(percent);
		
		AgentTrans newTrans = new AgentTrans();
		newTrans.setAgentFee(agentFee);
		newTrans.setAgentLevel(agent.getAgentLevel() + 1);
		newTrans.setAgentName(agent.getAgentName());
		newTrans.setAgentno(agent.getAgentno());
		newTrans.setAmount(qrcodeOrder.getAmount());
		newTrans.setBranchName(branch.getBranchName());
		newTrans.setBranchno(branch.getBranchno());
		newTrans.setChannelFee(qrcodeOrder.getChannelFee());
		//superAgentTrans.setCostFee(costFee);
		newTrans.setDivide(divide);
		newTrans.setDivideType(AgentTransDivideType.SETTLE_AMOUNT.ordinal());
		newTrans.setLiquidDate(DateFormat.DATE.format());
		newTrans.setLiquidStatus(3);
		newTrans.setMerchName(merch.getMerchName());
		newTrans.setMerchno(merch.getMerchno());
		//superAgentTrans.setPayFee(payFee);
		newTrans.setTransType(SysTransType.QRCODE.ordinal());
		newTrans.setRefno(qrcodeOrder.getOrderno());
		newTrans.setSettleDate(null);
		//superAgentTrans.setT0AgentFee(t0AgentFee);
		newTrans.setTotalAgentFee(totalAgentFee);
		newTrans.setTotalFee(qrcodeOrder.getMerchFee());
		newTrans.setTransDate(DateFormat.DATE.format());
		newTrans.setTransTime(DateFormat.TIME.format());
		newTrans.setTransStatus(AgentTransTransStatus.FORCE_SUCCESS.ordinal());
		newTrans.setTypeMemo("升级分润");
		newTrans.setType(AgentTransType.UPGRADE.ordinal());
		return newTrans;
	}
}
