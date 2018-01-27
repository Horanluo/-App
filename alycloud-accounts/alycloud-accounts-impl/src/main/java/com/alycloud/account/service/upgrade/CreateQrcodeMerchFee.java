package com.alycloud.account.service.upgrade;

import java.util.ArrayList;
import java.util.List;

import com.alycloud.account.api.IGradeFeeService;
import com.alycloud.account.feign.MerchInfoFeign;
import com.alycloud.account.feign.QrcodeMerchFeeFeign;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.SpringContextUtils;
import com.alycloud.modules.entity.GradeFee;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.entity.QrcodeMerchFee;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.search.GradeFee4Search;

/**
 * 
 * @author Moyq5
 * @date 2017年10月29日
 */
public class CreateQrcodeMerchFee extends Decorator {

	private MerchInfoFeign merchInfoFeign;
	private QrcodeMerchFeeFeign qrcodeMerchFeeFeign;
	private IGradeFeeService gradeFeeService;
	public CreateQrcodeMerchFee(Upgrader userUpgrader) {
		super(userUpgrader);
		merchInfoFeign = SpringContextUtils.getBean(MerchInfoFeign.class);
		qrcodeMerchFeeFeign = SpringContextUtils.getBean(QrcodeMerchFeeFeign.class);
		gradeFeeService = SpringContextUtils.getBean(IGradeFeeService.class);
	}

	@Override
	public MerchUser upgrade() throws Exception {
		MerchUser user = super.upgrade();
		createMerchFee(user);
		return user;
	}
	
	private void createMerchFee(MerchUser merchUser) throws Exception {
		RequestBean<Integer> merchFeignData = new RequestBean<Integer>();
		merchFeignData.setData(merchUser.getMerchId());
		MerchInfo merch = merchInfoFeign.getById(merchFeignData).getData();
		
		List<QrcodeMerchFee> newFeeList = //buildMerchFee(merchUser.getUserRank(), merch);// 新的未实施
											buildMerchFee2(merchUser.getUserRank(), merch);// 旧的在用
										  
		
		RequestBean<String> delData = new RequestBean<String>();
		delData.setData(merch.getMerchno());
		qrcodeMerchFeeFeign.delByMerchno(delData);
		
		RequestBean<List<QrcodeMerchFee>> addData = new RequestBean<List<QrcodeMerchFee>>();
		addData.setData(newFeeList);
		qrcodeMerchFeeFeign.addList(addData);
		
	}

	private List<QrcodeMerchFee> buildMerchFee(Integer gradeType, MerchInfo merch) {
		List<QrcodeMerchFee> newFeeList = new ArrayList<QrcodeMerchFee>();
		GradeFee4Search rule4s = new GradeFee4Search();
		rule4s.setGradeType(gradeType);
		List<GradeFee> ruleList = gradeFeeService.list(rule4s);
		for (GradeFee rule: ruleList) {
			QrcodeMerchFee fee = new QrcodeMerchFee();
			fee.setAgentno(merch.getSuperAgent());
			fee.setBranchno(merch.getBranchno());
			fee.setMerchno(merch.getMerchno());
			fee.setFee(rule.getFee());
			fee.setPayType(rule.getPayType());
			fee.setRate(rule.getRate());
			fee.setScanType(rule.getScanType());
			fee.setSettleType(rule.getSettleType());
			newFeeList.add(fee);
		}
		return newFeeList;
	}
	
	private List<QrcodeMerchFee> buildMerchFee2(Integer gradeType, MerchInfo merch) {
		List<QrcodeMerchFee> newFeeList = new ArrayList<QrcodeMerchFee>();
		
		GradeFee4Search rule4s = new GradeFee4Search();
		rule4s.setGradeType(gradeType);
		List<GradeFee> ruleList = gradeFeeService.list(rule4s);
		
		QrcodePayType[] payTypes = QrcodePayType.values();
		
		MerchInfo merch4Mod = new MerchInfo();
		merch4Mod.setId(merch.getId());
		
		boolean isMerchMod = false;
		
		for (GradeFee rule: ruleList) {
			for (QrcodePayType payType: payTypes) {
				if ((rule.getPayType()&(1<<payType.ordinal())) == (1<<payType.ordinal())) {
					if (payType == QrcodePayType.FAST) {
						if ((rule.getSettleType()&(1<<SysSettleType.T0.ordinal())) == (1<<SysSettleType.T0.ordinal())) {
							merch4Mod.setFastpayRateT0(rule.getRate());
							isMerchMod = true;
						}
						if ((rule.getSettleType()&(1<<SysSettleType.T1.ordinal())) == (1<<SysSettleType.T1.ordinal())) {
							merch4Mod.setFastpayRateT1(rule.getRate());
							isMerchMod = true;
						}
						continue;
					}
					QrcodeMerchFee fee = buildMerchFeeItem(merch, rule, payType);
					newFeeList.add(fee);
				}
			}
		}
		
		if (isMerchMod) {
			RequestBean<MerchInfo> merchData = new RequestBean<MerchInfo>();
			merchData.setData(merch4Mod);
			merchInfoFeign.mod(merchData);
		}
		return newFeeList;
	}

	private QrcodeMerchFee buildMerchFeeItem(MerchInfo merch, GradeFee rule, QrcodePayType payType) {
		QrcodeMerchFee fee = new QrcodeMerchFee();
		fee.setAgentno(merch.getSuperAgent());
		fee.setBranchno(merch.getBranchno());
		fee.setMerchno(merch.getMerchno());
		fee.setFee(rule.getFee());
		fee.setPayType(payType.ordinal());
		fee.setRate(rule.getRate());
		fee.setScanType(rule.getScanType());
		fee.setSettleType(rule.getSettleType());
		return fee;
	}

}
