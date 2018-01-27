/*
 * 类文件名:  IQrcodeOrderService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api;

import java.util.List;

import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.pay.gateway.dto.QrcodeOrderDTO;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.models.BranchBean;
import com.alycloud.pay.gateway.models.agent.AgentBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
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
public interface IQrcodeOrderService
{
    String genRefno() throws Exception;

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月25日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeOrderBean buildByParam(QrcodeOrderDTO qrcodeOrderDTO, String orderno, int payTypePassive ,MerchInfo merch);
    
    /**
     * 创建二维码订单信息
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月25日
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
     * @date     2017年7月25日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateStatusInfo(String orderno, int status, String message);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月25日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateChannelInfo(QrcodeOrderBean order);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月25日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    void setMerchInfo(QrcodeOrderBean order, BranchBean branch, AgentBean agent, MerchInfo merch) throws Exception;

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月25日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateMerchInfo(QrcodeOrderBean order);

    /**
     * 设置渠道信息
     * @param order 订单信息
     * @param merch 渠道商户信息
     * @param barCode 渠道跳转地址
     * @throws com.alycloud.pay.gateway.exception.YzyueException 
     * @throws YzyueException
     * @throws Exception
     */
    void setChannelInfo(QrcodeOrderBean order, QrcodeMerchBean merch, String barCode) throws com.alycloud.pay.gateway.exception.YzyueException, Exception;

    /**
     * 
     * 根据商户上送的参数查询订单信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @throws YzyueException 
     * @date     2017年8月4日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    QrcodeOrderBean queryOrder(String merchno, String traceno) throws YzyueException;

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
    int updateBankCardType(String orderno, String bankCardType);

    /**
     * 查询最近minute (20)分钟内尚未支付的订单信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月18日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    List<QrcodeOrderBean> listNotPay(int minute);

    /**
     * 更新查询的信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月18日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateQueryInfo(String orderno);

    /**
     * 添加退款记录
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月24日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insertRefundOrder(QrcodeOrderBean order);

    /**
     * 更新退款状态
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月24日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateRefundOrderStatusInfo(String orderno,String channelRefundNo, int status, String message);
}
