/*
 * 类文件名:  IChannelSubmerchService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月7日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api.channel;

import java.util.List;
import java.util.Map;

import com.alycloud.modules.channel.ChannelSubmerchInfoBean;
import com.alycloud.modules.channel.hfbank.HxtcBillsBean;

/**
 * 根据不同的渠道生成不同的子商户号和费率表
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月7日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IChannelSubmerchService
{
    /**
     * 
     * 插入子商户和渠道费率信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月7日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public int insert(ChannelSubmerchInfoBean entity);
    
    /**
     * 
     * 根据查询条件查询渠道子商户信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月7日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public List<ChannelSubmerchInfoBean> search(ChannelSubmerchInfoBean entity);

    
    /**
     * 
     * 根据商户号更新子商户信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月7日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public int update(ChannelSubmerchInfoBean entity);
    
    /**
     * 根据商户号，渠道子商户号，渠道编码查询通道信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月8日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public ChannelSubmerchInfoBean searchBySubmerchInfo(String merchno,String submerchno, String channleCode,String payType,String payMethod);

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
    public int batchInsertHxtcBills(List<HxtcBillsBean> list);
}
