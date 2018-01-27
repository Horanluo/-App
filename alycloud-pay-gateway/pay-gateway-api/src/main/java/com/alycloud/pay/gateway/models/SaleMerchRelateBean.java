/*
 * 类文件名:  SaleMerchRelateBean.java
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
 * 分销商关系
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
public class SaleMerchRelateBean implements Serializable
{

    private static final long serialVersionUID = 3752955227925466041L;
    
    private int id;
    private int merchId;
    private int sm1Id;
    private int sm2Id;
    private int sm3Id;
    private String branchno;
    private String agentno;
}
