/*
 * 类文件名:  IFastOrderService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api;

import java.util.List;
import java.util.Map;

import com.alycloud.modules.channel.ChannelSubmerchInfoBean;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.pay.gateway.api.channel.IFastChannelInfo;
import com.alycloud.pay.gateway.condition.QuickRequestCondition;
import com.alycloud.pay.gateway.models.quick.FastOrder;

/**
 * 快捷支付订单
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IFastOrderService
{

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    FastOrder buildFastOrder(MerchInfo merch, IFastChannelInfo channelInfo,ChannelSubmerchInfoBean channelSubmerchInfo, QuickRequestCondition condition);

    /**
     * 插入快捷支付接口
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insert(FastOrder fastOrder);
    
    /**
     * 根据快捷支付订单号查询订单
     */
    FastOrder searchByOrderno(String orderno);
    
    /**
     * 根据查询条件查询快捷支付订单
     */
    List<FastOrder> search(Map<String, String> params);

    /**
     * 根据商户号和流水号查询快捷支付订单
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月6日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    FastOrder queryOrder(String merchno, String traceno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月6日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateStatusInfo(String orderno, int status, String message);
    
}
