/*
 * 类文件名:  ChannelSubmerchMapper.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月7日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.mapper.channel;

import java.util.List;

import com.alycloud.modules.channel.ChannelSubmerchInfoBean;
import com.alycloud.modules.channel.hfbank.HxtcBillsBean;

/**
 * 渠道子商户实现类
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月7日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ChannelSubmerchMapper
{

    /**
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月7日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insert(ChannelSubmerchInfoBean entity);

    /**
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月7日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int update(ChannelSubmerchInfoBean entity);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月7日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    List<ChannelSubmerchInfoBean> search(ChannelSubmerchInfoBean entity);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月29日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int batchInsertHxtcBills(List<HxtcBillsBean> list);
    
}
