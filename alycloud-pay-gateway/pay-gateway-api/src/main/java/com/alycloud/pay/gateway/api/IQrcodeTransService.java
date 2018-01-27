/*
 * 类文件名:  IQrcodeTransService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月4日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api;

import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeTrans;

/**
 * 二维码交易的业务处理对象
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月4日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IQrcodeTransService
{

    /**
     * 根据系统订单号获取二维码订单信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @throws Exception 
     * @date     2017年8月6日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeTrans getByOrderno(String orderno) throws Exception;

    /**
     * 当天交易表查询不到,则查询历史交易表
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @throws Exception 
     * @date     2017年8月11日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeTrans getOnHisotry(String orderno) throws Exception;

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
     * @date     2017年8月15日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeTrans buildBasicTrans(QrcodeOrderBean order);

    /**
     * 更新当天交易的商户通知状态 
     * @param orderno 订单号
     * @param status 状态(1-无需通知 2-通知成功 3-通知失败)
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateNotifyStatus(String orderno, Integer status);
    
}
