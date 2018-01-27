/*
 * 类文件名:  QrcodeChannelServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;

import com.alycloud.pay.gateway.api.IQrcodeChannelService;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.mapper.QrcodeChannelMapper;
import com.alycloud.pay.gateway.models.qrcode.QrcodeChannelBean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月27日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class QrcodeChannelServiceImpl implements IQrcodeChannelService
{
    @Autowired
    private QrcodeChannelMapper qrcodeChannelMapper;

    /**
     * 根据渠道编码获取渠道信息
     * 
     * @param channelCode
     *            渠道编码
     * @return
     * @throws YzyueException 
     * @throws Exception
     */
    public QrcodeChannelBean getByChannelCode(String channelCode) throws YzyueException
    {
        QrcodeChannelBean channel = qrcodeChannelMapper.getByChannelCode(channelCode);
        if(channel == null){
            throw new YzyueException("56", "找不到二维码渠道信息");
        }
        return channel;
    }
    
}
