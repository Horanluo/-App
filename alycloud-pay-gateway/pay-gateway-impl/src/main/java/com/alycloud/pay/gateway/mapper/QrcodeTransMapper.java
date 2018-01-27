/*
 * 类文件名:  QrcodeTransMapper.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月11日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.mapper;

import java.util.Map;

import com.alycloud.pay.gateway.models.qrcode.QrcodeTrans;

/**
 * 二维码渠道持久层
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月11日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface QrcodeTransMapper
{

    /**
     * 根据系统订单号获取二维码订单信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月11日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeTrans getByOrderno(String orderno);

    /**
     * 根据参考号统计历史信息 
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月11日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeTrans getOnHisotry(String orderno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月15日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insert(QrcodeTrans trans);


    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateNotifyStatusOnToday(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateNotifyStatusOnHistory(Map<String, Object> param);
    
}
