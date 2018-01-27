/*
 * 类文件名:  QrcodeOrderMapper.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.mapper;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.Param;

import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;

/**
 * 二维码订单
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月25日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface QrcodeOrderMapper
{

    /**
     * <一句话功能简述>
     * 获取二维码订单的参考号
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月25日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    String genRefno();

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月26日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeOrderBean getByTraceno(@Param("merchno")String merchno, @Param("traceno")String traceno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月26日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insert(QrcodeOrderBean order);

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
    int updateMerchInfo(QrcodeOrderBean order);

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
    int updateChannelInfo(QrcodeOrderBean order);

    /**
     * 更新二维码的状态信息
     * 
     * @param order
     * @return
     * @throws Exception
     */
    int updateStatusInfo(Map<String, Object> param);

    /**
     * 根据订单号获取二维码订单信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月6日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeOrderBean getByOrderno(String orderno);

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
    int updateBankCardType(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月18日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    List<QrcodeOrderBean> listNotPay(Map<String, Object> param);

    /**
     * 更新查询的次数信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月18日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateQueryInfo(Map<String, Object> param);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月24日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeOrderBean getByRefundTraceno(@Param("merchno")String merchno, @Param("traceno")String traceno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月24日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insertRefundOrder(QrcodeOrderBean order);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月24日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateRefundOrderStatusInfo(Map<String, Object> param);
    
}
