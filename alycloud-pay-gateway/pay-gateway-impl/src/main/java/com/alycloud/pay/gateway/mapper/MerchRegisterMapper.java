/*
 * 类文件名:  MerchProvider.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月22日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.mapper;

import java.util.List;

import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.pay.gateway.models.MerchRelateBean;
import com.alycloud.pay.gateway.models.MerchUserBean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月22日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MerchRegisterMapper
{

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月22日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
//    MerchBean register(BranchBean branch, MerchBean merch, String agentName);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月23日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insertMerchInfo(MerchInfo merch);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月23日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insertMerchRelate(List<MerchRelateBean> relate);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月23日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insertMerchUser(MerchUserBean user);
    
}
