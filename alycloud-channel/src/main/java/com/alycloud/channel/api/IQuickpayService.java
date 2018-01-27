/*
 * 类文件名:  IQuickpayService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月1日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.api;

import java.util.Map;

import com.alycloud.core.enums.SysChannelType;

/**
 * 快捷支付服务接口
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月1日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IQuickpayService
{
    /**
     * 获取通道类型
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月7日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public String getChannelCode();
    /**
     * 渠道商户注册
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月1日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
//   public Map<String, Object> registerChannelSubmerch(Map<String, String> businessReq);
   public <T,R> T registerChannelSubmerch(R entity);
   
   /**
    * 查询渠道注册商户信息
    * 
    * @author   曾云龙
    * @version  V001Z0001
    * @date     2017年9月15日
    * @see  [相关类/方法]
    * @since  [产品/模块版本]
    */
   public <T,R> T queryChannelSubmerch(R entity);
   
   /**
    * 修改渠道商户信息
    * 
    * @author   曾云龙
    * @version  V001Z0001
    * @date     2017年9月1日
    * @see  [相关类/方法]
    * @since  [产品/模块版本]
    */
   public <T> T updateChannelSubmerch();
   
   /**
    * 支付服务
    *   
    * @author   曾云龙
    * @version  V001Z0001
    * @date     2017年9月1日  
    * @see  [相关类/方法] 
    * @since  [产品/模块版本]
    */
   public <T, R> T pay(R payRequestParams,String orderNo);
   
   /**
    * 支付订单查询
    * 
    * @author   曾云龙
    * @version  V001Z0001
    * @date     2017年9月1日
    * @see  [相关类/方法]
    * @since  [产品/模块版本]
    */
   public void orderQuery();
   
   public void notifys();
   
   /**
    * 对账单查询
    * @author   曾云龙
    * @version  V001Z0001
    * @date     2017年9月1日
    * @see  [相关类/方法]
    * @since  [产品/模块版本]
    */
   public <T, R> T billQuery(R data);
   
   /**
    * 渠道商户注册
    * @author   曾云龙
    * @version  V001Z0001
    * @date     2017年9月1日
    * @see  [相关类/方法]
    * @since  [产品/模块版本]
    */
//  public Map<String, Object> registerChannelSubmerch(Map<String, String> businessReq);
  public <T,R> T registerChannelJFSubmerch(R entity);
}
