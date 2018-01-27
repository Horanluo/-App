/*
 * 类文件名:  IChannelSubmerchInfoService.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年11月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.api;

import com.alycloud.modules.entity.ChannelSubmerchInfo;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年11月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IChannelSubmerchInfoService
{
    /**
     * 根据渠道编号查询该商户是否已经进件,渠道是否有效
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年11月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public ChannelSubmerchInfo searchByChannelCode(String merchno,String channelCode);
    
    /**
     * 渠道进件
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年11月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public int addChannelSubMerchInfo(ChannelSubmerchInfo info);
    
    /**
     * 根据渠道信息
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年11月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public int addChannelSubMerchInfoByGradeInfo(String merchno, String channelCode);
    
    /**
     * 存量客户已经开通支付渠道，数据复制到channel_submerch_info
     */
    public int copytoChannelSubmerchInfo(String merchno, String channelCode)  throws Exception;
}
