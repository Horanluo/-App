/*
 * 类文件名:  FastChannelInfoFactory.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alycloud.pay.gateway.api.channel.IFastChannelInfo;
import com.alycloud.pay.gateway.api.enums.SysChannelType;
import com.alycloud.pay.gateway.channel.quick.hxtc.HxtcquickFastChannelInfo;
import com.alycloud.pay.gateway.exception.ChannelInfoNotFoundException;

/**
 * 快捷支付渠道信息工厂类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class FastChannelInfoFactory
{
    private static HxtcquickFastChannelInfo hxtcquickFastChannelInfo;
    
    public static IFastChannelInfo getDefChannel() throws ChannelInfoNotFoundException {
        return getChannel(SysChannelType.HXTC) ;
    }
    
    public static IFastChannelInfo getChannel(SysChannelType type) throws ChannelInfoNotFoundException {
        if (type == SysChannelType.PAYECO) {
            return hxtcquickFastChannelInfo;
        } else if (type == SysChannelType.YUFU) {
            return  hxtcquickFastChannelInfo;
        }else if (type == SysChannelType.HXTC) {
            return  hxtcquickFastChannelInfo;
        }
        
        // else if {...
        throw new ChannelInfoNotFoundException("找不到渠道信息["+ type +"]");
    }

    /**
     * 设置 hxtcquickFastChannelInfo
     * @param 对hxtcquickFastChannelInfo进行赋值
     */
    @Autowired
    public void setHxtcquickFastChannelInfo(HxtcquickFastChannelInfo hxtcquickFastChannelInfo)
    {
        FastChannelInfoFactory.hxtcquickFastChannelInfo = hxtcquickFastChannelInfo;
    }
    
    
}
