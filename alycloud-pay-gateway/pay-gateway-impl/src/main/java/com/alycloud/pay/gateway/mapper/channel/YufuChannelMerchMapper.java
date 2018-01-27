/*
 * 类文件名:  YufuChannelMerchMapper.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月14日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.mapper.channel;

import org.apache.ibatis.annotations.Param;

import com.alycloud.modules.channel.yufu.YufuChannelMerchBean;

/**
 * 御付渠道商户信息
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月14日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface YufuChannelMerchMapper
{

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月14日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    long insertYufuMerch(YufuChannelMerchBean yufuMerchBean);
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月19日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    YufuChannelMerchBean searchByKey(@Param("platMerchno") String merchno,@Param("phone") String mobile,@Param("state")  Integer state, @Param("branchId") String branchId);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月19日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int upYufuChannelMerch(YufuChannelMerchBean merchBean);

}
