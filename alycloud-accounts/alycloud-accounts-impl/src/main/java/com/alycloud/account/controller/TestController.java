package com.alycloud.account.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.api.IMerchService;
import com.alycloud.account.mapper.ChannelSubMerchInfoMapper;
import com.alycloud.account.mapper.MerchInfoMapper;
import com.alycloud.account.mapper.MerchVirtualCardMapper;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.modules.channel.yufu.YufuChannelMerchBean;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.enums.SysChannelType;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

	@Autowired
	private IMerchService merchService;
	@Autowired
    private ChannelSubMerchInfoMapper channelSubmerchInfoMapper;
	@Autowired
	private MerchInfoMapper merchInfoMapper;
	@Autowired
	private MerchVirtualCardMapper merchVirtualCardMapper;
	
	private static Map<String,String> map;
	
	@ApiOperation(notes = "调用 /test方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "数据迁移")
	@PostMapping(value="/test")
	@SystemControllerLog(description = "数据迁移")
	public ResultBean<String> test(@Validated @RequestBody RequestBean<String> reqData,
	        HttpServletResponse  response,HttpServletRequest request) throws Exception{
		List<MerchInfo> list = merchInfoMapper.getAllMerch();
//		for(MerchInfo mi:list){
//			copytoChannelSubmerchInfo(mi.getMerchno(),"YUFU");
//			copytoChannelSubmerchInfo(mi.getMerchno(),"HXTC");
//		}
		for(MerchInfo mi:list){
			batchUpdateMerchVirtualCard(mi.getMerchno());
		}
		return null;
	}	
	
	public void batchUpdateMerchVirtualCard(String merchno){
		List<MerchVirtualCard> mvcs = merchVirtualCardMapper.getByMerchno(merchno);
		if(null!=mvcs&&mvcs.size()>0){
			MerchVirtualCard newRecord = mvcs.get(mvcs.size()-1);
			newRecord.setStatus(0);
			newRecord.setChannelType(-1);
			BigDecimal availAmt = new BigDecimal(0.00);
			for(MerchVirtualCard mvc:mvcs){
				availAmt = availAmt.add(mvc.getAvailAmount());
			}
			newRecord.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			newRecord.setAvailAmount(availAmt);
			log.info("新增记录:"+newRecord);
			merchVirtualCardMapper.add(newRecord);
		}
	}
	
	public void copytoChannelSubmerchInfo(String merchno, String channelCode){
		MerchInfo merchInfo = merchService.getByMerchno(merchno);
		
		ChannelSubmerchInfo csmi = new ChannelSubmerchInfo();
        csmi.setBranchno(merchInfo.getBranchno());
        csmi.setAgentno(merchInfo.getSuperAgent());
        csmi.setMerchno(merchInfo.getMerchno());
        csmi.setCreatedate(new Date());
        csmi.setChannelName(SysChannelType.getByCode(channelCode).getText());
        csmi.setChannelCode(channelCode);
        
        csmi.setPayType("QUICKPAY");
        
        if(channelCode.equals(SysChannelType.HXTC.getCode()) && !StringUtils.isEmpty(merchInfo.getHpmercode()))
        {
            csmi.setChannelSubmerchno(merchInfo.getHpmercode());
            csmi.setAduitStatus("1");
            csmi.setPayFeeD0(new BigDecimal("2"));
            csmi.setPayFeeT1(new BigDecimal("1"));
            csmi.setD0payRate(merchInfo.getHxtcfastpayratet0());
            csmi.setT1payRate(merchInfo.getHxtcfastpayratet0());
            csmi.setRemark("存量客户费率");
            csmi.setPayMethod("QUICKPAY");
            channelSubmerchInfoMapper.addChannelSubMerchInfo(csmi);
        }
        if(channelCode.equals(SysChannelType.YUFU.getCode()))
        {
        	map = new HashMap<String,String>();
        	map.put("phone", merchInfo.getMobile());
        	map.put("platMerchno", merchno);
            YufuChannelMerchBean yufuSubmerchInfo = channelSubmerchInfoMapper.searchYufuSubmerchInfo(map);
            if(yufuSubmerchInfo != null)
            {
                csmi.setChannelSubmerchno(yufuSubmerchInfo.getMerchId());
                csmi.setAduitStatus(yufuSubmerchInfo.getState().toString());
                csmi.setPayFeeD0(new BigDecimal("2"));
                csmi.setPayFeeT1(new BigDecimal("1"));
                csmi.setD0payRate(merchInfo.getFastpayRateT0());
                csmi.setT1payRate(merchInfo.getFastpayRateT1());
                csmi.setRemark("存量客户费率");
                csmi.setPayMethod("QUICKPAY");
                csmi.setYufuOneCodeUrl(yufuSubmerchInfo.getOneCodeUrl());
                csmi.setYufuKjKey(yufuSubmerchInfo.getKjKey());
                csmi.setYufuTermId(yufuSubmerchInfo.getTermId());
                channelSubmerchInfoMapper.addChannelSubMerchInfo(csmi);
            }
        }
	}
}
