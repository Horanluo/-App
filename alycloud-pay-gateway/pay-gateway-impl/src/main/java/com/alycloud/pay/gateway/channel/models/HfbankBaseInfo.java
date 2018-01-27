/*
 * 类文件名:  HfbankBaseInfo.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.channel.models;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
public class HfbankBaseInfo implements Serializable
{
    /**
     * 交易类型
     */
    private String tradeType;
    
    /**
     * 版本
     */
    private String version = "1.3";
    
    /**
     * 代理商编号
     */
    private String mchId;
        
}
