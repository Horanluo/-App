package com.alycloud.financial.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.utils.CommonUtil;
import com.alycloud.financial.api.IMerchVirtualCardService;
import com.alycloud.financial.feign.MerchInfoFeign;
import com.alycloud.financial.mapper.MerchVirtualCardMapper;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.entity.MerchVirtualTrans;
import com.alycloud.modules.enums.MerchVirtualCardStatus;
import com.alycloud.modules.search.MerchVirtualCard4Search;

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
	private MerchInfoFeign merchInfoFeign;

	@Override
	public synchronized MerchVirtualTrans draw(MerchInfo merch, String amount) throws Exception {
		return new MerchVirtualCardDraw(merch, amount).draw();
	}

	@Override
	public List<MerchVirtualCard> listByPage(MerchVirtualCard4Search card4s) {
		return merchVirtualCardMapper.listByPage(card4s);
	}

	@Override
	public void addAvailAmount(MerchVirtualCard card4AvailAmount) {
		merchVirtualCardMapper.addAvailAmount(card4AvailAmount);
	}

	@Override
	public MerchVirtualCard add4Merch(String merchno) throws Exception {
		RequestBean<String> merchData = new RequestBean<String>();
		merchData.setData(merchno);
		MerchInfo merch = merchInfoFeign.getByMerchno(merchData).getData();
		
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
		//card.setFrozenTime(frozenTime);
		//card.setId(id);
		card.setMerchno(merchno);
		//card.setPasswd(passwd);
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


	private String createCardno(String branchno) throws Exception {
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
