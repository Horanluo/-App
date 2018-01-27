/*
 * 类文件名:  FastOrderMapper.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月22日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.mapper;

import java.util.List;
import java.util.Map;

import com.alycloud.pay.gateway.models.quick.FastOrder;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月22日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface FastOrderMapper
{

    /**
     * 插入快捷支付订单
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年8月22日
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
     * 更新快捷支付状态
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月6日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int updateStatusInfo(Map<String, Object> param);
}
