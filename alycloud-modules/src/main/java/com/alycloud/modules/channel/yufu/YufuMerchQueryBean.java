/*
 * 类文件名:  YufuMerchQueryBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu;

import lombok.Data;

/**
 * 商户审核结果查询请求参数对象
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月15日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
public class YufuMerchQueryBean extends AbstractYufuEntity
{
    private static final long serialVersionUID = -1156682435285721717L;
    /**
     * 手机号，必填
     */
    private String phone;
    
    public YufuMerchQueryBean()
    {
        super("MER_QUERY");
    }
}
