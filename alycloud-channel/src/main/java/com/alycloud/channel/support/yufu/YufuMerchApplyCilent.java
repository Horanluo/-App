/*
 * 类文件名:  YufuMerchApplyCilent.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月14日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.support.yufu;

import org.springframework.stereotype.Service;

import com.alycloud.channel.utils.yufu.YufuMerchApiType;
import com.alycloud.modules.channel.yufu.AbstractYufuResultEntity;
import com.alycloud.modules.channel.yufu.YufuMerchBean;

/**
 * 御付商户申请接口
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月14日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class YufuMerchApplyCilent extends AbstractYufuClient<YufuMerchBean, AbstractYufuResultEntity>
{

    public String getKey()
    {
        return YufuMerchApiType.MERCH_APPLY.name();
    }
    
}
