/*
 * 类文件名:  ChannelSubmerchServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月7日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain.channel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.modules.channel.ChannelSubmerchInfoBean;
import com.alycloud.modules.channel.hfbank.HxtcBillsBean;
import com.alycloud.pay.gateway.api.channel.IChannelSubmerchService;
import com.alycloud.pay.gateway.mapper.channel.ChannelSubmerchMapper;

/**
 * 渠道子商户实现类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月7日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class ChannelSubmerchServiceImpl implements IChannelSubmerchService
{
    @Autowired
    private ChannelSubmerchMapper channelSubmerchMapper;

    /**
     * 重载方法
     * @param entity
     * @return
     */
    @ServiceLogAnnotation(moduleName="插入渠道子商户信息")
    public int insert(ChannelSubmerchInfoBean entity)
    {
        return channelSubmerchMapper.insert(entity);
    }

    /**
     * 重载方法
     * @param entity
     * @return
     */
    @Override
    public List<ChannelSubmerchInfoBean> search(ChannelSubmerchInfoBean entity)
    {
        return channelSubmerchMapper.search(entity);
    }

    /**
     * 重载方法
     * @param entity
     * @return
     */
    @ServiceLogAnnotation(moduleName="修改渠道子商户信息")
    public int update(ChannelSubmerchInfoBean entity)
    {
        return channelSubmerchMapper.update(entity);
    }
    
    /**
     * 重载方法
     * @param merchno
     * @param submerchno
     * @param channleCode
     * @param payType
     * @param payMethod
     * @return
     */
    @Override
    public ChannelSubmerchInfoBean searchBySubmerchInfo(String merchno, String submerchno, String channelCode,
        String payType, String payMethod)
    {
        ChannelSubmerchInfoBean entity = new ChannelSubmerchInfoBean();
        entity.setMerchno(merchno);
        entity.setChannelName(submerchno);
        entity.setChannelCode(channelCode);
        entity.setPayType(payType);
        entity.setPayMethod(payMethod);
        List<ChannelSubmerchInfoBean> list = channelSubmerchMapper.search(entity);
        if(list != null && list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }
    
    /**
     * 重载方法
     * @param list
     * @return
     */
    @Override
    public int batchInsertHxtcBills(List<HxtcBillsBean> list)
    {
        return channelSubmerchMapper.batchInsertHxtcBills(list);
    }
}
