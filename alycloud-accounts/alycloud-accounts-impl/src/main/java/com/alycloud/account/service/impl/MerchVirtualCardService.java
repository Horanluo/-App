package com.alycloud.account.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alycloud.account.api.IMerchVirtualCardService;
import com.alycloud.account.mapper.MerchInfoMapper;
import com.alycloud.account.mapper.MerchVirtualCardMapper;
import com.alycloud.core.utils.CommonUtil;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.enums.MerchVirtualCardStatus;

/**
 * 商户虚拟账户Service实现类
 * @author Moyq5
 * @date 2017年11月13日
 */
@Service
public class MerchVirtualCardService implements IMerchVirtualCardService {

	@Autowired
	private MerchVirtualCardMapper merchVirtualCardMapper;
	@Autowired
	private MerchInfoMapper merchInfoMapper;
	@Override
	public MerchVirtualCard add4Merch(String merchno){
		MerchInfo merch = merchInfoMapper.getByMerchno(merchno);
		
		MerchVirtualCard card = new MerchVirtualCard();
		card.setAccountno(merch.getAccountno());
		card.setAccountName(merch.getAccountName());
		card.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		card.setAvailAmount(new BigDecimal(0));
		card.setBankName(merch.getBankName());
		card.setBankno(merch.getBankno());
		card.setBizType(4);// 二维码
		card.setBranchno(merch.getBranchno());
		card.setCardno(createCardno(merch.getBranchno()));
		card.setMerchno(merchno);
		card.setPayType(1); // T+0
		card.setRateCode("1001");
		card.setStatus(MerchVirtualCardStatus.NORMAL.ordinal());
		card.setTransitAmount(new BigDecimal(0));
		card.setValidDate("2099-12-31");
		card.setPayKey(CommonUtil.mkRandomStr(32));
		card.setChannelType(-1);

		merchVirtualCardMapper.add(card);
		return card;
	}
	
	private String createCardno(String branchno){
		StringBuffer cardno = new StringBuffer();
		cardno.append(branchno);
		String str = merchVirtualCardMapper.getMaxCardo();
		if (StringUtils.isEmpty(str)) {
			str = "00000001";
		} else {
			int num = Integer.parseInt(str);
			str = "0000000" + (++num);
			str = str.substring(str.length() - 8);
		}
		cardno.append(str);
		return cardno.toString();
	}
}
