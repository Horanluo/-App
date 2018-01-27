/*
 * 类文件名:  ChannelSubmerchInfoServiceImpl.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年11月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alycloud.core.modules.RequestBean;
import com.alycloud.modules.channel.yufu.YufuChannelMerchBean;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.pay.api.IChannelSubmerchInfoService;
import com.alycloud.pay.feign.MerchInfoFeign;
import com.alycloud.pay.mapper.ChannelSubmerchInfoMapper;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年11月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class ChannelSubmerchInfoServiceImpl implements IChannelSubmerchInfoService
{
    @Autowired
    private ChannelSubmerchInfoMapper channelSubmerchInfoMapper;
    
    @Autowired
    private MerchInfoFeign merchInfoFeign;
    
    public ChannelSubmerchInfo searchByChannelCode(String merchno, String channelCode)
    {
    	Map<String,String> channelParams = new HashMap<String,String>();
    	channelParams.put("merchno", merchno);
    	channelParams.put("channelCode", channelCode);
        ChannelSubmerchInfo info = channelSubmerchInfoMapper.searchFastByChannelCode(channelParams);
        return info;
    }
    
    @Override
    public int copytoChannelSubmerchInfo(String merchno, String channelCode) throws Exception
    {
        RequestBean<String> reqData = new RequestBean<String>();
        reqData.setMerchno(merchno);
        MerchInfo merchInfo = merchInfoFeign.getByMerchno(reqData).getData();
        if(merchInfo == null){
            throw new Exception("该商户不存在。");
        }
//        Map<String,String> channelParams = new HashMap<String,String>();
//        channelParams.put("merchno", merchno);
//        channelParams.put("channelType", new Integer(SysChannelType.getByCode(channelCode).ordinal()).toString());
//        ChannelSubmerchInfo gradeFee = channelSubmerchInfoMapper.searchFastByGradeInfo(channelParams);
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
            return channelSubmerchInfoMapper.addChannelSubMerchInfo(csmi);
        }else if(channelCode.equals(SysChannelType.YUFU.getCode()))
        {
            YufuChannelMerchBean yufuSubmerchInfo = channelSubmerchInfoMapper.searchYufuSubmerchInfo(merchno, merchInfo.getMobile());
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
                return channelSubmerchInfoMapper.addChannelSubMerchInfo(csmi);
            }
        }
        return 0;
        
        
    }
    
    /**
     * 重载方法
     * @param info
     * @return
     */
    @Override
    public int addChannelSubMerchInfo(ChannelSubmerchInfo info)
    {
        return channelSubmerchInfoMapper.addChannelSubMerchInfo(info);
    }
    
    public int addChannelSubMerchInfoByGradeInfo(String merchno, String channelCode)
    {
        RequestBean<String> reqData = new RequestBean<String>();
        reqData.setMerchno(merchno);
        MerchInfo merchInfo = merchInfoFeign.getByMerchno(reqData).getData();
        if(merchInfo == null){
            return 0;
        }
        Map<String,String> channelParams = new HashMap<String,String>();
    	channelParams.put("merchno", merchno);
    	channelParams.put("channelType", new Integer(SysChannelType.getByCode(channelCode).ordinal()).toString());
        ChannelSubmerchInfo gradeFee = channelSubmerchInfoMapper.searchFastByGradeInfo(channelParams);
        ChannelSubmerchInfo csmi = new ChannelSubmerchInfo();
        csmi.setBranchno(merchInfo.getBranchno());
        csmi.setAgentno(merchInfo.getSuperAgent());
        csmi.setMerchno(merchInfo.getMerchno());
        csmi.setCreatedate(new Date());
        csmi.setChannelName(SysChannelType.getByCode(channelCode).getText());
        csmi.setChannelCode(channelCode);
        csmi.setChannelSubmerchno("");
        csmi.setPayType("QUICKPAY");
        csmi.setAduitStatus("");
        csmi.setPayFeeD0(gradeFee.getPayFeeD0());
        csmi.setPayFeeT1(gradeFee.getPayFeeD0());
        csmi.setD0payRate(gradeFee.getD0payRate());
        csmi.setT1payRate(gradeFee.getD0payRate());
        csmi.setRemark("默认等级费率");
        csmi.setPayMethod("QUICKPAY");
        
        return channelSubmerchInfoMapper.addChannelSubMerchInfo(csmi);
    }
}
