package com.alycloud.account.service.channel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alycloud.account.api.IGradeFeeService;
import com.alycloud.account.api.IMerchService;
import com.alycloud.account.api.IMerchUserService;
import com.alycloud.modules.entity.GradeFee;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.search.GradeFee4Search;

public class AbsDefaultChannelInfo {

	@Autowired
	private IMerchService merchService;
	@Autowired
	private IGradeFeeService gradeFeeService;
	@Autowired
	private IMerchUserService merchUserService;
	/**
	 * 获取平台默认信息
	 * @param merchno
	 * @return
	 */
	protected Map<Object,Object> getDefaultChannelInfo(String merchno,String loginName){
		Map<Object,Object> map = new HashMap<Object,Object>();
		MerchInfo merchInfo = merchService.getByMerchno(merchno);
		
		//获取平台商户等级默认费率
		GradeFee4Search gfs = new GradeFee4Search();
		gfs.setGradeType(merchUserService.queryMerchUser(loginName).getUserRank());
		gfs.setPayType(1<<QrcodePayType.FAST.ordinal());
		List<GradeFee> list = gradeFeeService.list(gfs);
		
		BigDecimal t0Rate=new BigDecimal("0.0");
		BigDecimal t1Rate=new BigDecimal("0.0");
		BigDecimal toFee=new BigDecimal("0.0");
		BigDecimal t1Fee=new BigDecimal("0.0");
		for(GradeFee gf : list){
			if ((gf.getSettleType()&(1<<SysSettleType.T0.ordinal())) == (1<<SysSettleType.T0.ordinal())) {
				t0Rate = gf.getRate().multiply(new BigDecimal("1000"));
				toFee = gf.getFee().multiply(new BigDecimal("100"));
			}
			if ((gf.getSettleType()&(1<<SysSettleType.T1.ordinal())) == (1<<SysSettleType.T1.ordinal())) {
				t1Rate = gf.getRate().multiply(new BigDecimal("1000"));
				t1Fee = gf.getFee();
			}
		}
		map.put("t0Rate", t0Rate);
		map.put("t1Rate", t1Rate);
		map.put("toFee", toFee);
		map.put("t1Fee", t1Fee);
		map.put("merchInfo", merchInfo);
		return map;
	}
}
