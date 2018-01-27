/*
 * 类文件名:  AgentRelateBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AgentRelateBean implements Serializable
{

    private static final long serialVersionUID = 869341401558808426L;
    
    /**
     * 代理商编号
     */
    private String agentno;
    
    /**
     * 上级代理商编号
     */
    private String superAgentno;
    
    /**
     * 上级代理商名称
     */
    private String superAgentName;
    
    /**
     * 代理商级别
     */
    private int agentLevel;
    
}
